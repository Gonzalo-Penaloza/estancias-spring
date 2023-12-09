package com.tadd.estancias.service;

import org.springframework.stereotype.Service;

import com.tadd.estancias.model.Estancia;
import com.tadd.estancias.repository.EstanciaRepository;

/**
 *
 * @author Taddeu's
 */
@Service
public class EstanciaService extends GenericServiceImpl<Estancia> {

	public EstanciaService(EstanciaRepository repository) {
		super(repository);
		// TODO Auto-generated constructor stub
	}
    
}
