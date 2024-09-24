package org.educadventista.Sabor.Digital.service;

import org.educadventista.Sabor.Digital.model.OrderStatus;
import org.educadventista.Sabor.Digital.repository.OrderStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderStatusService {

    @Autowired
    private OrderStatusRepository orderStatusRepository;

    public List<OrderStatus> getAllOrderStatuses() {
        return orderStatusRepository.findAll();
    }
}