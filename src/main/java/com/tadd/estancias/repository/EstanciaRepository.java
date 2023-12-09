package com.tadd.estancias.repository;

import com.tadd.estancias.model.Estancia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Taddeu's
 */
@Repository
public interface EstanciaRepository extends JpaRepository<Estancia, String> {
    
}
