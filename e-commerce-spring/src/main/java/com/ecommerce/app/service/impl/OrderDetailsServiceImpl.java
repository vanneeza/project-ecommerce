package com.ecommerce.app.service.impl;

import com.ecommerce.app.entity.OrderDetails;
import com.ecommerce.app.repository.OrderDetailsRepository;
import com.ecommerce.app.repository.OrderRepository;
import com.ecommerce.app.service.OrderDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailsServiceImpl implements OrderDetailsService {
    OrderDetailsRepository orderDetailsRepository;

    @Autowired
    public OrderDetailsServiceImpl(OrderDetailsRepository orderDetailsRepository) {
        this.orderDetailsRepository = orderDetailsRepository;
    }

    @Override
    public OrderDetails save(OrderDetails orderDetails) {
        return orderDetailsRepository.save(orderDetails);
    }
}
