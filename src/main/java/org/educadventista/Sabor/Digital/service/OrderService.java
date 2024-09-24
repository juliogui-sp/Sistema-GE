package org.educadventista.Sabor.Digital.service;

import org.educadventista.Sabor.Digital.model.Dish;
import org.educadventista.Sabor.Digital.model.Order;
import org.educadventista.Sabor.Digital.model.OrderStatus;
import org.educadventista.Sabor.Digital.repository.DishRepository;
import org.educadventista.Sabor.Digital.repository.OrderRepository;
import org.educadventista.Sabor.Digital.repository.OrderStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderStatusRepository orderStatusRepository;

    @Autowired
    private DishRepository dishRepository;

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Order findById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    public List<Order> findOrdersByStatus(String statusName) {
        return orderRepository.findByStatus_Name(statusName);
    }

    public Order create(Order order) {
        // Verifique se o status está ausente e defina como "Criado" (statusId = 1)
        if (order.getStatus() == null) {
            OrderStatus defaultStatus = orderStatusRepository.findById(1L)
                    .orElseThrow(() -> new RuntimeException("Default status not found"));
            order.setStatus(defaultStatus);
        }

        // Carrega o Dish pelo ID
        Dish dish = dishRepository.findById(order.getDish().getId())
                .orElseThrow(() -> new RuntimeException("Dish not found"));

        // Preenche o orderName com o nome do Dish
        order.setOrderName(dish.getName());

        return orderRepository.save(order);
    }

    public Order update(Long id, Order updatedOrder) {
        Order existingOrder = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        if (updatedOrder.getStatus() != null) {
            existingOrder.setStatus(updatedOrder.getStatus());
        }
        if (updatedOrder.getOrderName() != null) {
            existingOrder.setOrderName(updatedOrder.getOrderName());
        }
        if (updatedOrder.getCustomerName() != null) {
            existingOrder.setCustomerName(updatedOrder.getCustomerName());
        }
        if (updatedOrder.getPhone() != null) {
            existingOrder.setPhone(updatedOrder.getPhone());
        }
        if (updatedOrder.getAddress() != null) {
            existingOrder.setAddress(updatedOrder.getAddress());
        }
        if (updatedOrder.getDish() != null) {
            existingOrder.setDish(updatedOrder.getDish());
        }

        return orderRepository.save(existingOrder);
    }

    public void delete(Long id) {
        orderRepository.deleteById(id);
    }

    public Order updateStatusById(Long orderId, Long statusId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        OrderStatus status = orderStatusRepository.findById(statusId)
                .orElseThrow(() -> new RuntimeException("Status not found"));

        order.setStatus(status);

        // Preservar os outros campos
        order.setOrderName(order.getOrderName());
        order.setCustomerName(order.getCustomerName());
        order.setPhone(order.getPhone());
        order.setAddress(order.getAddress());
        order.setDish(order.getDish());

        return orderRepository.save(order);
    }
}
