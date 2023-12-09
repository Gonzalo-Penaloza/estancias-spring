package com.tadd.estancias.service;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author Taddeu's
 */
public interface GenericService<T> {
    void create(T entidad);
    
    Optional<T> getById(String id);
    
    void update(T entidad);
    
    void delete(String id);
    
    List<T> findAll();
}
