package org.educadventista.Sabor.Digital.repository;

import org.educadventista.Sabor.Digital.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByStatus(String status);
}
