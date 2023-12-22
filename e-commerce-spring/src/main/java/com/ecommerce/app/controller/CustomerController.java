package com.ecommerce.app.controller;

import com.ecommerce.app.entity.Customer;
import com.ecommerce.app.service.CustomerService;
import com.ecommerce.app.utils.constant.Constant;
import com.ecommerce.app.utils.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(Constant.CUSTOMER_PATH)
public class CustomerController {
    CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<Response<Customer>> save(@RequestBody Customer customer) {
        Customer saveCustomer = customerService.save(customer);
        String message = String.format(Constant.SUCCESS_INSERT, "customer");
        return Response.response(message, HttpStatus.CREATED.value(), "CREATED", saveCustomer);
    }

    @GetMapping
    public ResponseEntity<Response<List<Customer>>> getAll() {
        List<Customer> getAllCustomer = customerService.getAll();
        String message = String.format(Constant.SUCCESS_GET_ALL, "customer");
        return Response.response(message, HttpStatus.OK.value(), "OK", getAllCustomer);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<Response<Optional<Customer>>> getById(@PathVariable String customerId) {
        Optional<Customer> getByIdCustomer = customerService.getById(customerId);
        String message = String.format(Constant.SUCCESS_GET_BY_ID, "customer");
        return Response.response(message, HttpStatus.OK.value(), "OK", getByIdCustomer);
    }

    @PutMapping
    public ResponseEntity<Response<Customer>> update(@RequestBody Customer customer) {
        Customer updateCustomer = customerService.save(customer);
        String message = String.format(Constant.SUCCESS_INSERT, "customer");

        return Response.response(message, HttpStatus.CREATED.value(), "CREATED", updateCustomer);
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<Response<Optional<Customer>>> deleted(@PathVariable String customerId) {
        Optional<Customer> getByIdCustomer = customerService.getById(customerId);
        customerService.delete(customerId);
        String message = String.format(Constant.SUCCESS_DELETE, "customer");

        return Response.response(message, HttpStatus.OK.value(), "OK", getByIdCustomer);
    }
}
