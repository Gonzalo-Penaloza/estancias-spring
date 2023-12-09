package com.tadd.estancias.service;

import org.springframework.stereotype.Service;

import com.tadd.estancias.model.Comentario;
import com.tadd.estancias.repository.ComentarioRepository;

/**
 *
 * @author Taddeu's
 */
@Service
public class ComentarioService extends GenericServiceImpl<Comentario>{

	public ComentarioService(ComentarioRepository repository) {
		super(repository);
	}
    
}
