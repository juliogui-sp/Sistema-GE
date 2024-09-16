package org.educadventista.Sabor.Digital.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.educadventista.Sabor.Digital.model.Dish;

public interface DishRepository extends JpaRepository<Dish, Long> {
}