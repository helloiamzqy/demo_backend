package com.example.demo.api;

import com.example.demo.dto.OrderCreateDTO;
import com.example.demo.service.OrderService;
import com.example.demo.vo.OrderVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(description = "OrderResource")
@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @ApiOperation(value = "Create a order for one merchandise", notes = "Success return order id.")
    @PostMapping("")
    @PreAuthorize("hasAuthority('USER')")
    public String createOrder(@Validated @RequestBody OrderCreateDTO createDTO) {
        return orderService.createOrder(createDTO);
    }


    @ApiOperation("Get current user all orders")
    @GetMapping("")
    public List<OrderVO> list() {
        return orderService.findAll();
    }
}
