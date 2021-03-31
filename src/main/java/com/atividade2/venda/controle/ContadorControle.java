package com.atividade2.venda.controle;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


import org.springframework.ui.Model;

@Controller
public class ContadorControle {
	
	Integer conta;
	public ContadorControle() {
		conta=0;
	}
	
	@GetMapping("/contador")
	public String contador(Model model) {
		conta++;
		model.addAttribute("contador", conta);
		return "contador/index";
	}
	
}
