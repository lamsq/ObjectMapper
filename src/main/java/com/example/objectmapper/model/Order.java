package com.example.objectmapper.model;

import com.example.objectmapper.repository.ProductRepository;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

@Setter
@Getter
@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    @ManyToOne
    private Customer customer;
    @ManyToMany
    private List<Product> products;
    private LocalDate orderDate;
    private String shippingAddress;
    private BigDecimal totalPrice;
    private String orderStatus;
}
