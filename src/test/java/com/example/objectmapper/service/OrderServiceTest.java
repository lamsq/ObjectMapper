package com.example.objectmapper.service;

import com.example.objectmapper.model.Order;
import com.example.objectmapper.model.Product;
import com.example.objectmapper.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.*;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class OrderServiceTest {

    @Mock
    private OrderRepository or;

    @Mock
    private ProductService productService;  // Mocking ProductService if needed for calculation

    @InjectMocks
    private OrderService orderService;

    @Test
    public void testCreateOrder() {
        Product p = new Product();
        p.setPrice(BigDecimal.valueOf(100));

        Order o = new Order();
        o.setProducts(List.of(p));
        o.setShippingAddress("Test address");

        when(or.save(any(Order.class))).thenReturn(o);

        Order createdOrder = orderService.createOrder(o);
        assertNotNull(createdOrder);
        assertEquals("Test address", createdOrder.getShippingAddress());
        assertEquals(BigDecimal.valueOf(100), createdOrder.getTotalPrice());
    }

    @Test
    public void testGetOrderById() {
        Product product = new Product();
        product.setPrice(BigDecimal.valueOf(100));

        Order o = new Order();
        o.setOrderId(1L);
        o.setProducts(List.of(product));
        o.setShippingAddress("Test address");

        when(or.findById(1L)).thenReturn(Optional.of(o));
        Optional<Order> foundOrder = orderService.getOrderById(1L);

        assertTrue(foundOrder.isPresent());
        assertEquals("Test address", foundOrder.get().getShippingAddress());
    }

    @Test
    public void testUpdateOrder() {
        Product p = new Product();
        p.setPrice(BigDecimal.valueOf(100));

        Order o = new Order();
        o.setOrderId(1L);
        o.setProducts(List.of(p));
        o.setShippingAddress("Address");

        Order update = new Order();
        update.setOrderId(1L);
        update.setProducts(List.of(p));
        update.setShippingAddress("New address");

        when(or.findById(1L)).thenReturn(Optional.of(o));
        when(or.save(any(Order.class))).thenReturn(update);
        Order result = orderService.updateOrder(1L, update);

        assertNotNull(result);
        assertEquals("New address", result.getShippingAddress());
    }

    @Test
    public void testDeleteOrder() {
        Order o = new Order();
        o.setOrderId(1L);
        doNothing().when(or).deleteById(1L);
        orderService.deleteOrder(1L);

    }
}
