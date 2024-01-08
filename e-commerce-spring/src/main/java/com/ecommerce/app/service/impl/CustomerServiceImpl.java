package com.ecommerce.app.service.impl;

import com.ecommerce.app.config.ApplicationConfig;
import com.ecommerce.app.config.JwtService;
import com.ecommerce.app.entity.AuthenticationRequest;
import com.ecommerce.app.entity.AuthenticationResponse;
import com.ecommerce.app.entity.Customer;
import com.ecommerce.app.repository.CustomerRepository;
import com.ecommerce.app.service.CustomerService;
import com.ecommerce.app.utils.constant.Role;
import com.ecommerce.app.utils.response.CustomerAuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final  PasswordEncoder passwordEncoder;
    private final  JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public CustomerServiceImpl(CustomerRepository customerRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(), request.getPassword()
                )
        );

        var user = customerRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + request.getEmail()));

        var jwtToken = jwtService.generateToken(user);
        jwtService.decodeToken(jwtToken);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }



    @Override
    public CustomerAuthResponse save(Customer customer) {
        var passwordEncrypt = passwordEncoder.encode(customer.getPassword());
        customer.setPassword(passwordEncrypt);
        customer.setRole(Role.USER);

        Customer saveCustomer = customerRepository.save(customer);

        CustomerAuthResponse customerAuthResponse = new CustomerAuthResponse();
        customerAuthResponse.setId(saveCustomer.getId());
        customerAuthResponse.setAddress(saveCustomer.getAddress());
        customerAuthResponse.setName(saveCustomer.getName());
        customerAuthResponse.setEmail(saveCustomer.getEmail());
        customerAuthResponse.setPassword(saveCustomer.getPassword());
        customerAuthResponse.setBirthDate(saveCustomer.getBirthDate());
        customerAuthResponse.setPhoneNumber(saveCustomer.getPhoneNumber());

        var jwtToken = jwtService.generateToken(customer);
        customerAuthResponse.setToken(jwtToken);

        return customerAuthResponse;
    }

    @Override
    public Optional<Customer> getById(String customerId) {
        return customerRepository.findById(customerId);
    }

    @Override
    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer update(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public void delete(String customerId) {
        customerRepository.deleteById(customerId);
    }
}
