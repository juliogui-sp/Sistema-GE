package org.educadventista.Sabor.Digital.service;

import org.educadventista.Sabor.Digital.model.Dish;
import org.educadventista.Sabor.Digital.model.Order;
import org.educadventista.Sabor.Digital.repository.DishRepository;
import org.educadventista.Sabor.Digital.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private DishRepository dishRepository;

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public List<Order> findOrdersByStatus(String status) {
        return orderRepository.findByStatus(status);
    }

    public Order findById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
    }

    public Order update(Long id, Order orderDetails) {
        Order existingOrder = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        // Atualize apenas os campos que foram fornecidos
        if (orderDetails.getStatus() != null) {
            existingOrder.setStatus(orderDetails.getStatus());
        }
        // Adicione outras propriedades que você deseja preservar ou atualizar

        return orderRepository.save(existingOrder);
    }

    public void delete(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new RuntimeException("Order not found");
        }
        orderRepository.deleteById(id);
    }

    public Order updateStatus(Long id, String status) {
        Order order = findById(id);
        order.setStatus(status);
        return orderRepository.save(order);
    }

    public Order create(Order order) {
        // Carrega o Dish pelo ID
        Dish dish = dishRepository.findById(order.getDish().getId())
                .orElseThrow(() -> new RuntimeException("Dish not found"));

        // Preenche o orderName com o nome do Dish
        order.setOrderName(dish.getName());

        // Define o status como "Criada" se não for especificado
        if (order.getStatus() == null || order.getStatus().isEmpty()) {
            order.setStatus("Criado");
        }

        return orderRepository.save(order);
    }
}
