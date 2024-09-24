package org.educadventista.Sabor.Digital.repository;

import org.educadventista.Sabor.Digital.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    // Métodos personalizados podem ser adicionados aqui
}