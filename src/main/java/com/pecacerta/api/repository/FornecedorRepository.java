package com.pecacerta.api.repository;

import com.pecacerta.api.modell.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

//[cite: 12]
public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {
    Optional<Fornecedor> findByCnpj(String cnpj);
    boolean existsByCnpj(String cnpj);
}