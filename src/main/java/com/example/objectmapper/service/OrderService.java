package com.example.objectmapper.service;

import com.example.objectmapper.model.Order;
import com.example.objectmapper.model.Product;
import com.example.objectmapper.repository.OrderRepository;
import com.example.objectmapper.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository or;

    @Autowired
    private ProductRepository pr;

    public List<Order> getAllOrders() {
        return or.findAll();
    }

    public Optional<Order> getOrderById(Long id) {
        return or.findById(id);
    }

    public Order createOrder(Order o) {
        BigDecimal totalPrice = o.getProducts().stream().map(Product::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
        o.setTotalPrice(totalPrice);
        o.setOrderDate(LocalDate.now());
        return or.save(o);
    }

    public Order updateOrder(Long id, Order od) {
        return or.findById(id).map(order -> {order.setShippingAddress(od.getShippingAddress());
            order.setOrderStatus(od.getOrderStatus());
            order.setProducts(od.getProducts());

            BigDecimal totalPrice = order.getProducts().stream()
                    .map(Product::getPrice)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            order.setTotalPrice(totalPrice);

            return or.save(order);
        }).orElseThrow(() -> new RuntimeException("Order doesnt exist"));
    }

    public void deleteOrder(Long id) {
        or.deleteById(id);
    }
}
