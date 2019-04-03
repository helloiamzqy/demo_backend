package com.example.demo.helper;

import com.example.demo.dto.OrderCreateDTO;
import com.example.demo.entity.Merchandise;
import com.example.demo.entity.Order;
import com.example.demo.exception.NotFoundException;
import com.example.demo.repository.MerchandiseRepository;
import com.example.demo.repository.OrderRepository;
import com.example.zklock.core.ZkLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class OrderHelper {

    @Autowired
    private MerchandiseRepository merchandiseRepository;
    @Autowired
    private OrderRepository orderRepository;

    @ZkLock(value = "merchandise", key = "merchandiseId")
    @Transactional
    public Order createOrderAndDecrementInventory(OrderCreateDTO dto, String userId) {
        Optional<Merchandise> merchandiseOptional = merchandiseRepository.findById(dto.getMerchandiseId());
        if(!merchandiseOptional.isPresent()) {
            throw new NotFoundException("Target merchandise not exist.");
        }
        Merchandise merchandise = merchandiseOptional.get();
        if(merchandise.getInventory() < dto.getQuantity()) {
            throw new NotFoundException("Target merchandise inventory not enough.");
        }
        merchandise.setInventory(merchandise.getInventory() - dto.getQuantity());
        Order order = buildOrder(merchandise, dto.getQuantity(), userId);

        merchandiseRepository.save(merchandise);
        orderRepository.save(order);

        return order;
    }

    private Order buildOrder(Merchandise merchandise, Integer quantity, String userId) {
        Order order = new Order();
        order.setMerchandise(merchandise);
        order.setMerchandiseName(merchandise.getName());
        order.setQuantity(quantity);
        order.setPrice(merchandise.getPrice() * quantity);
        order.setUserId(userId);
        return order;
    }
}
