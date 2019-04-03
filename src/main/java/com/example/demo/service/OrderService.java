package com.example.demo.service;

import com.example.demo.dto.OrderCreateDTO;
import com.example.demo.vo.OrderVO;

import java.util.List;

public interface OrderService {

    String createOrder(OrderCreateDTO createDTO);

    List<OrderVO> findAll();

}
