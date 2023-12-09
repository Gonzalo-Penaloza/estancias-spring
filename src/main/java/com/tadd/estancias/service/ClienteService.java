package com.tadd.estancias.service;

import org.springframework.stereotype.Service;

import com.tadd.estancias.model.Cliente;
import com.tadd.estancias.repository.ClienteRepository;

/**
 *
 * @author Taddeu's
 */
@Service
public class ClienteService extends GenericServiceImpl<Cliente> {

	public ClienteService(ClienteRepository repository) {
		super(repository);
		// TODO Auto-generated constructor stub
	}
    
}
