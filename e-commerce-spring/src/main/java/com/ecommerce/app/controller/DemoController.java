package com.ecommerce.app.controller;

import com.ecommerce.app.entity.AuthenticationRequest;
import com.ecommerce.app.entity.AuthenticationResponse;
import com.ecommerce.app.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/demo")
public class DemoController {
    CustomerService customerService;

    @Autowired
    public DemoController(CustomerService customerService) {
        this.customerService = customerService;
    }
    @GetMapping
    public ResponseEntity<String> sayDemo(){
        return ResponseEntity.ok("Hello Demo! You Are Secured");
    }

    @PostMapping(value = "/authenticate", consumes = "application/json")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(customerService.authenticate(request));
    }
}
