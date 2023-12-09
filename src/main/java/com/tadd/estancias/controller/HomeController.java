package com.tadd.estancias.controller;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tadd.estancias.model.Usuario;
import com.tadd.estancias.service.UsuarioService;
import com.tadd.estancias.service.UsuarioServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class HomeController {

	private final UsuarioServiceImpl userServiceImpl;

	public HomeController(UsuarioServiceImpl userServiceImpl) {
		this.userServiceImpl = userServiceImpl;
	}

	@GetMapping
	public String showIndex() {
		return "index";
	}

	@GetMapping("/sign-up")
	public String userRegister() {
		return "user/register_form";
	}

	@PostMapping("/sign-up")
	public String userRegister(@RequestParam String alias, @RequestParam String email, @RequestParam String clave,
			@RequestParam String clave2, ModelMap model) {
		try {
			String nameImg = "default.jpg";

			userServiceImpl.save(alias, email, clave, clave2, nameImg);
			return "redirect:/";
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			return "user/register_form";
		}

	}

	@GetMapping("/login")
	public String login(@RequestParam(required = false) String error, ModelMap model) {
		if (error != null) {
			model.addAttribute("error", "Usuario o contraseña incorrectos!");
		}
		return "login";
	}

	@GetMapping("/home")
	public String showHome(HttpSession session) {
		 // Obtén el ID del usuario de la sesión
	    Usuario user = (Usuario) session.getAttribute("userSession");
	    
	    // Obtiene la información más reciente del usuario de la base de datos
	    Optional<Usuario> optUser = userServiceImpl.getById(user.getId());
	    
	    if (optUser.isPresent()) {
	        Usuario updatedUser = optUser.get();
	        
	        // Actualiza la sesión con la información más reciente del usuario
	        session.setAttribute("userSession", updatedUser);
	        
	        // Verifica si el usuario tiene un cliente asociado antes de mostrar la vista
	        if (updatedUser.getCliente() == null && updatedUser.getFamilia() == null)return "inicio";    
	    }
	        
	    
	    return "index";
	}

	@GetMapping("/dashboard")
	public String showDashboard(HttpSession session, ModelMap model) {
		Usuario user = (Usuario) session.getAttribute("userSession");

		if (user != null) {
			model.addAttribute("user", user);

			return "user/dashboard";
		}

		return "redirect:/";
	}

}
