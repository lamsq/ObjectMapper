package com.example.objectmapper.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;
    private String firstName;
    private String lastName;
    private String email;
    private String contactNumber;
}
