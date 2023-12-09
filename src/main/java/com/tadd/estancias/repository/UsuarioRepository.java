package com.tadd.estancias.repository;

import com.tadd.estancias.model.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Taddeu's
 */

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String>{
    
    public Optional<Usuario> findByEmail(String email);
    
}
