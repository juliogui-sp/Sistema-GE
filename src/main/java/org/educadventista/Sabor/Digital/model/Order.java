package org.educadventista.Sabor.Digital.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "status_id", referencedColumnName = "id")
    private OrderStatus status;

    private String orderName;

    @Column(name = "customer_name")
    private String customerName;

    private String phone;

    private String address;

    @ManyToOne
    @JoinColumn(name = "dish_id")
    private Dish dish;

    @PrePersist
    @PreUpdate
    public void updateOrderName() {
        if (this.dish != null && this.dish.getName() != null) {
            this.orderName = this.dish.getName();
        }
    }

}
