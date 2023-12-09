package com.tadd.estancias.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/estancia")
public class EstanciaController {
	
	
	
	@GetMapping("/disponibles")
	public String showEstancias(ModelMap model) {
		return "estancias/estancias_disponibles";
	}
	
}
