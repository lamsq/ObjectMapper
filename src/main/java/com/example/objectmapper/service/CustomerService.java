package com.example.objectmapper.service;

import com.example.objectmapper.model.Customer;
import com.example.objectmapper.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository cr;

    public List<Customer> getAllCustomers() {
        return cr.findAll();
    }

    public Optional<Customer> getCustomerById(Long id) {
        return cr.findById(id);
    }

    public Customer createCustomer(Customer customer) {
        return cr.save(customer);
    }

    public Customer updateCustomer(Long id, Customer c) {
        return cr.findById(id).map(customer -> {customer.setFirstName(c.getFirstName());
            customer.setLastName(c.getLastName());
            customer.setEmail(c.getEmail());
            customer.setContactNumber(c.getContactNumber());
            return cr.save(customer);
        }).orElseThrow(() -> new RuntimeException("Customer doesnt exist"));
    }

    public void deleteCustomer(Long id) {
        cr.deleteById(id);
    }
}
