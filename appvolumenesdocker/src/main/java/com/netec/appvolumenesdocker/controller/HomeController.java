package com.netec.appvolumenesdocker.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.netec.appvolumenesdocker.entities.Comentario;
import com.netec.appvolumenesdocker.services.IComentarioService;

@Controller
public class HomeController {
	@Autowired
	private IComentarioService servicio;
	
	@GetMapping("/")
	public String inicio(Model model) {
		var comentariosTemp=servicio.getTemporales();
		var comentariosPerm=servicio.getPermanentes();
		var cT=comentariosTemp==null?new ArrayList<Comentario>():comentariosTemp;
		var cP=comentariosPerm==null?new ArrayList<Comentario>():comentariosPerm;
		model.addAttribute("temporales",cT);
		model.addAttribute("permanentes", cP);
		return "formulario_comentarios";
	}
	
	
	@PostMapping("/salvart")
	public String insertTemporal(Comentario comentario) {
		servicio.comentarioTemporal(comentario);
		return "redirect:/";
	}
	
	@PostMapping("/salvarp")
	public String insertPermanente(Comentario comentario) {
		servicio.comentarioPermanente(comentario);
		return "redirect:/";
	}

	
}
