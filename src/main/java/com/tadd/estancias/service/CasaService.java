package com.tadd.estancias.service;

import org.springframework.stereotype.Service;

import com.tadd.estancias.model.Casa;
import com.tadd.estancias.repository.CasaRepository;

/**
 *
 * @author Taddeu's
 */
@Service
public class CasaService extends GenericServiceImpl<Casa> {

	public CasaService(CasaRepository repository) {
		super(repository);
	}
    
}
