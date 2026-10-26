package com.pecacerta.api.repository;

import com.pecacerta.api.modell.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

//[cite: 12]
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    boolean existsByNomeIgnoreCase(String nome);
}