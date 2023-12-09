package com.tadd.estancias.service;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public class GenericServiceImpl<T> implements GenericService<T> {

    protected final JpaRepository<T, String> repository;

    public GenericServiceImpl(JpaRepository<T, String> repository) {
        this.repository = repository;
    }

    @Override
    public void create(T entidad) {
        repository.save(entidad);
    }

    @Override
    public Optional<T> getById(String id) {
        return repository.findById(id);
    }
    
    @Override
    public void update(T entidad) {
        repository.save(entidad);
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id); }

    @Override
    public List<T> findAll() {
        return repository.findAll();
    }

}
