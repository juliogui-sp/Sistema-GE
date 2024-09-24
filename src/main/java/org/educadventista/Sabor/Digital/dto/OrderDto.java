package org.educadventista.Sabor.Digital.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.educadventista.Sabor.Digital.model.Order;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class OrderDto {
    private Long id;
    private String status;
    private String orderName;
    private String customerName;
    private String phone;
    private String address;

    public OrderDto(Order order, Long remainingTime) {
        this.id = order.getId();
        this.status = order.getStatus() != null ? order.getStatus().getName() : null;
        this.orderName = order.getOrderName();
        this.customerName = order.getCustomerName();
        this.phone = order.getPhone();
        this.address = order.getAddress();
    }
}
