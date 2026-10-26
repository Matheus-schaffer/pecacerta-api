package com.pecacerta.api.repository;

import com.pecacerta.api.modell.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

//[cite: 12]
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {
    Optional<Veiculo> findByMarcaIgnoreCaseAndModeloIgnoreCase(String marca, String modelo);
}