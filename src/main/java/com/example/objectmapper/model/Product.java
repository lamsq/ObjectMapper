package com.example.objectmapper.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import lombok.*;

@Setter
@Getter
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    private String name;
    private String description;
    private BigDecimal price;
    private int quantityInStock;
}

