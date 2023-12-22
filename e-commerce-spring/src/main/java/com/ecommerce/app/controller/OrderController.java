package com.ecommerce.app.controller;

import com.ecommerce.app.entity.Order;
import com.ecommerce.app.service.OrderService;
import com.ecommerce.app.utils.constant.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(Constant.ORDER_PATH)
public class OrderController {
    OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public Order save(@RequestBody Order order){
     
        return orderService.save(order);
    }

    @GetMapping
    public List<Order> getAll(){
        return orderService.getAll();
    }

    @GetMapping("/{orderId}")
    public Optional<Order> getById(@PathVariable String orderId){
        return orderService.getById(orderId);
    }

    @PutMapping
    public Order update(@RequestBody Order order){
        return orderService.save(order);
    }

    @DeleteMapping("/{orderId}")
    public void deleted(@PathVariable String orderId){
        orderService.delete(orderId);
    }
}
