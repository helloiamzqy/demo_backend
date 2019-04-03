package com.example.demo.service.impl;

import com.example.demo.DemoApplicationContext;
import com.example.demo.dto.OrderCreateDTO;
import com.example.demo.entity.Merchandise;
import com.example.demo.entity.Order;
import com.example.demo.entity.SysUser;
import com.example.demo.exception.ForbiddenException;
import com.example.demo.helper.OrderHelper;
import com.example.demo.repository.OrderRepository;
import com.example.demo.service.OrderService;
import com.example.demo.vo.OrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderHelper orderHelper;

    @Override
    public String createOrder(OrderCreateDTO dto) {
        Optional<SysUser> currentUser = DemoApplicationContext.getCurrentUser();
        if (!currentUser.isPresent()) {
            throw new ForbiddenException("Not login.");
        }
        String userId = currentUser.get().getId();
        Order order = orderHelper.createOrderAndDecrementInventory(dto, userId);
        return order.getId();
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderVO> findAll() {
        Optional<SysUser> currentUser = DemoApplicationContext.getCurrentUser();
        if (!currentUser.isPresent()) {
            throw new ForbiddenException("Not login.");
        }
        List<Order> orders = orderRepository.findByUserId(currentUser.get().getId());
        return orders.stream().map(this::toVO).collect(Collectors.toList());
    }

    private OrderVO toVO(Order entity) {
        OrderVO vo = new OrderVO();
        Merchandise merchandise = entity.getMerchandise();
        vo.setId(entity.getId());
        vo.setUserId(entity.getId());
        vo.setMerchandiseId(ofNullable(merchandise).map(Merchandise::getId).orElse(""));
        vo.setMerchandiseName(ofNullable(merchandise).map(Merchandise::getName).orElse(entity.getMerchandiseName()));
        vo.setQuantity(entity.getQuantity());
        return vo;
    }
}
