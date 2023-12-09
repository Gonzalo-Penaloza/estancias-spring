package com.tadd.estancias.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tadd.estancias.enums.Rol;
import com.tadd.estancias.model.Cliente;
import com.tadd.estancias.model.Usuario;
import com.tadd.estancias.service.ClienteService;
import com.tadd.estancias.service.UsuarioServiceImpl;

@Controller
@RequestMapping("/cliente")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private UsuarioServiceImpl usuarioServiceImpl;

	@GetMapping("/register")
	public String register() {
		return "user/cliente/alta_cliente";
	}
	
	@PostMapping("/register")
	public String registered(@RequestParam String nombre, @RequestParam String calle, @RequestParam Integer numero,
								@RequestParam String codPostal, @RequestParam String ciudad, @RequestParam String pais, @RequestParam String email ) {
		
		Optional<Usuario> optUser = usuarioServiceImpl.getByEmail(email);
		
		if(optUser.isPresent()) {
			Usuario usuario = optUser.get();
			Cliente cl = new Cliente();
			
			cl.setNombre(nombre);
			cl.setCalle(calle);
			cl.setNumero(numero);
			cl.setCodPostal(codPostal);
			cl.setCiudad(ciudad);
			cl.setPais(pais);
			cl.setEmail(email);
			cl.setUsuario(usuario);
			
			clienteService.create(cl);
			
			usuario.setCliente(cl);
			usuario.setRol(Rol.CLIENT);
			usuarioServiceImpl.update(usuario);
					
			return "redirect:/home";
		}	
		
		return "user/alta_cliente";
	}
}
