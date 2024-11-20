package com.example.objectmapper.service;

import com.example.objectmapper.model.Product;
import com.example.objectmapper.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ProductServiceTest {

    @Mock
    private ProductRepository pr;

    @InjectMocks
    private ProductService ps;

    @Test
    public void testCreateProduct() {
        Product p = new Product();
        p.setName("Test product");
        p.setDescription("Test Description");
        p.setPrice(BigDecimal.valueOf(100));
        p.setQuantityInStock(10);

        when(pr.save(any(Product.class))).thenReturn(p);
        Product create = ps.createProduct(p);

        assertNotNull(create);
        assertEquals("Test product", create.getName());
    }

    @Test
    public void testGetProductById() {
        Product p = new Product();
        p.setProductId(1L);
        p.setName("Test Product");
        p.setDescription("Test Description");
        p.setPrice(BigDecimal.valueOf(100));
        p.setQuantityInStock(10);

        when(pr.findById(1L)).thenReturn(Optional.of(p));

        Optional<Product> found = ps.getProductById(1L);

        assertTrue(found.isPresent());
        assertEquals("Test Product", found.get().getName());
    }

    @Test
    public void testUpdateProduct() {
        Product p = new Product();
        p.setProductId(1L);
        p.setName("Old Product");
        p.setDescription("Old Description");
        p.setPrice(BigDecimal.valueOf(100));
        p.setQuantityInStock(5);

        Product update = new Product();
        update.setProductId(1L);
        update.setName("Updated product");
        update.setDescription("Updated description");
        update.setPrice(BigDecimal.valueOf(120));
        update.setQuantityInStock(10);

        when(pr.findById(1L)).thenReturn(Optional.of(p));
        when(pr.save(any(Product.class))).thenReturn(update);
        Product result = ps.updateProduct(1L, update);

        assertNotNull(result);
        assertEquals("Updated product", result.getName());
    }

    @Test
    public void testDeleteProduct() {
        Product p = new Product();
        p.setProductId(1L);
        p.setName("Test product");
        doNothing().when(pr).deleteById(1L);
        ps.deleteProduct(1L);
    }
}
