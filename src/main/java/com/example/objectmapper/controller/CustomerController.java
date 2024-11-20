package com.example.objectmapper.controller;

import com.example.objectmapper.model.Customer;
import com.example.objectmapper.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService cs;

    @GetMapping
    public List<Customer> getAllCustomers() {
        return cs.getAllCustomers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        return cs.getCustomerById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer c) {
        Customer cc = cs.createCustomer(c);
        return ResponseEntity.ok(cc);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer cd) {
        Customer uc = cs.updateCustomer(id, cd);
        return ResponseEntity.ok(uc);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        cs.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }
}
