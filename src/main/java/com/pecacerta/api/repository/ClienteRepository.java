package com.pecacerta.api.repository;

import com.pecacerta.api.modell.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

//[cite: 12]
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findByCpf(String cpf);
    boolean existsByCpf(String cpf);
}