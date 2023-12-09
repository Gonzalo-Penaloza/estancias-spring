package com.tadd.estancias.service;

import java.util.List;
import java.util.Optional;

import com.tadd.estancias.model.Usuario;

public interface UsuarioService {
	public void save(Usuario usuario);
	
	public void save(String alias, String email, String clave, String clave2, String imagen) throws Exception;
	
	public Optional<Usuario> getById(String id);
	
	public Optional<Usuario> getByEmail(String email);
	
	public void update(Usuario usuario);
	
	public void delete(String id);
	
	public List<Usuario> findAll();
}
