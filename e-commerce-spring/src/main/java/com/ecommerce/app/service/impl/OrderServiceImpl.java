package com.ecommerce.app.service.impl;

import com.ecommerce.app.entity.Customer;
import com.ecommerce.app.entity.Order;
import com.ecommerce.app.entity.OrderDetails;
import com.ecommerce.app.entity.Product;
import com.ecommerce.app.repository.OrderRepository;
import com.ecommerce.app.service.CustomerService;
import com.ecommerce.app.service.OrderDetailsService;
import com.ecommerce.app.service.OrderService;
import com.ecommerce.app.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    OrderRepository orderRepository;
    OrderDetailsService orderDetailsService;
    CustomerService customerService;
    ProductService productService;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, OrderDetailsService orderDetailsService, CustomerService customerService, ProductService productService) {
        this.orderRepository = orderRepository;
        this.orderDetailsService = orderDetailsService;
        this.customerService = customerService;
        this.productService = productService;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Order save(Order order) {
        Optional<Customer> customer = customerService.getById(order.getCustomer().getId());
        customer.ifPresent(order::setCustomer);

        Date date = new Date(System.currentTimeMillis());
        order.setCreatedAt(date);
        Order saveOrder = orderRepository.save(order);

        int totalPrice = 0;
        for (OrderDetails orderDetails : order.getOrderDetails()){
           Optional<Product> product = productService.getById(orderDetails.getProduct().getId());
           product.ifPresent(orderDetails::setProduct);

           int subTotal = product.get().getPrice() * orderDetails.getQuantity();
           orderDetails.setSubTotal(subTotal);

           totalPrice += subTotal;
           order.setTotalTransaction(totalPrice);

           orderDetails.setOrder(order);
           orderDetailsService.save(orderDetails);
        }
        return saveOrder;
    }

    @Override
    public Optional<Order> getById(String orderId) {
        return orderRepository.findById(orderId);
    }

    @Override
    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order update(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Optional<Order> delete(String id) {
        Optional<Order> order = orderRepository.findById(id);
        orderRepository.deleteById(id);
        return order;
    }
}
