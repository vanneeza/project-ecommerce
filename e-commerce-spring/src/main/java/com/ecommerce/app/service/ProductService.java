package com.ecommerce.app.service;

import com.ecommerce.app.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Product save(Product product);
    Optional<Product> getById(String productId);
    List<Product> getAll();
    Product update(Product product);
    void delete(String id);
}
