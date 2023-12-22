package com.ecommerce.app.service;


import com.ecommerce.app.entity.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    Order save(Order Order);
    Optional<Order> getById(String OrderId);
    List<Order> getAll();
    Order update(Order Order);
    Optional<Order> delete(String id);
}
