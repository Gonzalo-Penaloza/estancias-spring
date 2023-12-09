package com.tadd.estancias.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tadd.estancias.service.FamiliaService;
import com.tadd.estancias.service.UsuarioServiceImpl;

@Controller
@RequestMapping("/familia")
public class FamiliaController {
	
	@Autowired
	private FamiliaService familiaService;
	
	@Autowired
	private UsuarioServiceImpl usuarioServiceImpl;
	
	
	@GetMapping("/register")
	public String register() {
		return "user/familia/alta_cliente";
	}
	
}
