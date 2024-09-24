package org.educadventista.Sabor.Digital.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.educadventista.Sabor.Digital.model.Order;
import org.educadventista.Sabor.Digital.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "http://127.0.0.1:4200")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.findAll();
    }

    @GetMapping("/api/{id}")
    public Order getOrderById(@PathVariable Long id) {
        return orderService.findById(id);
    }

    @GetMapping("/api/status/{status}")
    public List<Order> getOrdersByStatus(@PathVariable String status) {
        return orderService.findOrdersByStatus(status);
    }

    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        return orderService.create(order);
    }

    @PutMapping("/api/{id}")
    public Order updateOrder(@PathVariable Long id, @RequestBody Order order) {
        return orderService.update(id, order);
    }

    @DeleteMapping("/api/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/api/{id}/status")
    public Order updateOrderStatus(@PathVariable Long orderId, @RequestParam Long statusId) {
        return orderService.updateStatusById(orderId, statusId);
    }
}
