package org.educadventista.Sabor.Digital.dto;

public class DishDTO {
    private Long id;
    private String name;
    private double price;
    private String description;

    public DishDTO(Long id, String name, double price, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
    }

    // Getters and setters
}
