package com.example.objectmapper.controller;

import com.example.objectmapper.model.Order;
import com.example.objectmapper.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService os;

    @GetMapping
    public List<Order> getAllOrders() {
        return os.getAllOrders();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        return os.getOrderById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order o) {
        Order createdOrder = os.createOrder(o);
        return ResponseEntity.ok(createdOrder);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable Long id, @RequestBody Order o) {
        Order uo = os.updateOrder(id, o);
        return ResponseEntity.ok(uo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        os.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
}
