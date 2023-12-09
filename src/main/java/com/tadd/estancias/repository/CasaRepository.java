package com.tadd.estancias.repository;

import com.tadd.estancias.model.Casa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Taddeu's
 */
@Repository
public interface CasaRepository extends JpaRepository<Casa, String>{
    
}
