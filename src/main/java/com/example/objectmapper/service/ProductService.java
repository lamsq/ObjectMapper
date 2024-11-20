package com.example.objectmapper.service;

import com.example.objectmapper.model.Product;
import com.example.objectmapper.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository pr;

    public List<Product> getAllProducts() {
        return pr.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return pr.findById(id);
    }

    public Product createProduct(Product p) {
        return pr.save(p);
    }

    public Product updateProduct(Long id, Product p) {
        return pr.findById(id).map(pro -> {pro.setName(p.getName());
            pro.setDescription(p.getDescription());
            pro.setPrice(p.getPrice());
            pro.setQuantityInStock(p.getQuantityInStock());
            return pr.save(pro);
        }).orElseThrow(() -> new RuntimeException("Product doesnt exist"));
    }

    public void deleteProduct(Long id) {
        pr.deleteById(id);
    }
}
