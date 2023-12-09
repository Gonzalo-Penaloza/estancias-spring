package com.tadd.estancias.repository;

import com.tadd.estancias.model.Familia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Taddeu's
 */
@Repository
public interface FamiliaRepository extends JpaRepository<Familia, String>{
    
}
