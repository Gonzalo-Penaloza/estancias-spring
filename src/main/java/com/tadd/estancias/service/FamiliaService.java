package com.tadd.estancias.service;

import org.springframework.stereotype.Service;

import com.tadd.estancias.model.Familia;
import com.tadd.estancias.repository.FamiliaRepository;

/**
 *
 * @author Taddeu's
 */
@Service
public class FamiliaService extends GenericServiceImpl<Familia>{

	public FamiliaService(FamiliaRepository repository) {
		super(repository);
	}
    
}
