package com.ecommerce.app.service;

import com.ecommerce.app.entity.AuthenticationRequest;
import com.ecommerce.app.entity.AuthenticationResponse;
import com.ecommerce.app.entity.Customer;
import com.ecommerce.app.utils.response.CustomerAuthResponse;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    AuthenticationResponse authenticate(AuthenticationRequest request);

    CustomerAuthResponse save(Customer customer);
     Optional<Customer> getById(String customerId);
     List<Customer> getAll();
     Customer update(Customer customer);
     void delete(String id);

}
