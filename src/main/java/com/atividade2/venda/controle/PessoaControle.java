package com.atividade2.venda.controle;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.atividade2.venda.entidade.Pessoa;
import com.atividade2.venda.entidade.PessoaRepositorio;

@Controller
public class PessoaControle {
	
	private PessoaRepositorio pessoaRepo;
	
	
	public PessoaControle(PessoaRepositorio pessoaRepo) {
		this.pessoaRepo = pessoaRepo;
	}

	@GetMapping("/pessoas")
	public String pessoas(Model model) {
		model.addAttribute("listaPessoas", pessoaRepo.findAll());
		return "pessoas/index";
	}
	
	
	@GetMapping("/pessoas/nova")
	public String novaPessoa(Model model) {
		model.addAttribute("pessoa", new Pessoa(""));
		return "pessoas/form";
	}
	
	
	@PostMapping("/pessoas/salvar")
	public String salvarPessoa(@Valid @ModelAttribute("pessoa") Pessoa pessoa, BindingResult bindingResult, Model model) {
		
		pessoaRepo.save(pessoa);
		return "redirect:/pessoas";
	}
	
	@GetMapping("/pessoas/{id}")
	public String alterarPessoa(@PathVariable("id") long id, Model model) {
		Optional<Pessoa> pessOpt = pessoaRepo.findById(id);
		if (!pessOpt.isPresent()) {
			throw new IllegalArgumentException("Pessoa inválida.");
		}
		
		model.addAttribute("pessoa", pessOpt.get());
		
		return "pessoas/form";
	}
	
	@GetMapping("/pessoas/excluir/{id}")
	public String excluirPessoa(@PathVariable("id") long id) {
		Optional<Pessoa> pessOpt = pessoaRepo.findById(id);
		
		if(!pessOpt.isPresent()) {
			throw new IllegalArgumentException("Pessoa Inválida");
		}
		
		pessoaRepo.delete(pessOpt.get());
		return "redirect:/pessoas";
	}
	
}
