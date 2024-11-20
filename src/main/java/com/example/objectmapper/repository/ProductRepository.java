package com.example.objectmapper.repository;

import com.example.objectmapper.model.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
