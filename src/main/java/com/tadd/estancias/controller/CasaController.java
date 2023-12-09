package com.tadd.estancias.controller;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.tadd.estancias.enums.TipoCasa;
import com.tadd.estancias.model.Casa;
import com.tadd.estancias.service.CasaService;
import com.tadd.estancias.service.UploadFileService;

@Controller
@RequestMapping("/casa")
public class CasaController {
	
	
	@Autowired
	private UploadFileService uploadFileService;

	@Autowired
	private CasaService casaService;
	
	@GetMapping("/disponibles")
	public String showAvailable(ModelMap model) {
		
		List<Casa> casasDisp = casaService.findAll();
		
		model.addAttribute("casas", casasDisp);
		
		return "casas/disponibles";
	}
	
	@GetMapping("/register")
	public String registerHouse(ModelMap model) {
		TipoCasa[] tipos = TipoCasa.values();
		
		model.addAttribute("tipos", tipos);
		
		return "casas/register_form";
	}
	
	@PostMapping("/register-house")
	public String registerHouse(@RequestParam String calle, @RequestParam Integer numero, @RequestParam String ciudad
								, @RequestParam String codPostal, @RequestParam String pais, @RequestParam Integer minDias
								, @RequestParam Integer maxDias,  @RequestParam Double precio
								,@RequestParam("img") MultipartFile file, ModelMap model) {	
		try {
			Casa casa = new Casa();
			
			casa.setCalle(calle);
			casa.setNumero(numero);
			casa.setCiudad(ciudad);
			casa.setCodPostal(codPostal);
			casa.setPais(pais);
			casa.setTipoVivienda(TipoCasa.CASA);
			casa.setMinDias(minDias);
			casa.setMaxDias(maxDias);
			casa.setPrecio(precio);
			
			String nameImage = uploadFileService.saveImage(file, "casa");
			casa.setImage(nameImage);
					
			casaService.create(casa);
			return "redirect:/casa/disponibles";
		} catch (Exception e) {
			model.addAttribute("error", e.toString());
			return "casas/register_form";
		}		
	}
	
	@PostMapping("/delete/{id}")
	public String deleteHouse(@PathVariable String id, ModelMap model) {
		try {
			casaService.delete(id);
			
			return "redirect:/casa/disponibles";
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			return "redirect:/casa/disponibles";// TODO: handle exception
		}	
	}
}
