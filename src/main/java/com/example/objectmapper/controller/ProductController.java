package com.example.objectmapper.controller;

import com.example.objectmapper.model.Product;
import com.example.objectmapper.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService ps;

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping
    public List<Product> getAllProducts() {
        return ps.getAllProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return ps.getProductById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product p) {
        Product cp = ps.createProduct(p);
        return ResponseEntity.ok(cp);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product p) {
        Product up = ps.updateProduct(id, p);
        return ResponseEntity.ok(up);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        ps.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
