package com.tadd.estancias.repository;

import com.tadd.estancias.model.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Taddeu's
 */
@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, String>{
    
}
