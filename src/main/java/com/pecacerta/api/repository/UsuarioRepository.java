package com.pecacerta.api.repository;

import com.pecacerta.api.modell.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

//[cite: 11]
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
    boolean existsByEmail(String email);
}