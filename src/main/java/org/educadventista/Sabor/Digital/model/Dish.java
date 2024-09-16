package org.educadventista.Sabor.Digital.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "dishes")
@Data
public class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private Double price;

    private String urlImage;
}