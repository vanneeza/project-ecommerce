package com.ecommerce.app.service;

import com.ecommerce.app.entity.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
     Customer save(Customer customer);
     Optional<Customer> getById(String customerId);
     List<Customer> getAll();
     Customer update(Customer customer);
     void delete(String id);

}
