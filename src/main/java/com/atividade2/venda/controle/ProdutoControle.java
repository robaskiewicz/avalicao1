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

import com.atividade2.venda.entidade.Produto;
import com.atividade2.venda.entidade.ProdutoRepositorio;

@Controller
public class ProdutoControle {
	
	private ProdutoRepositorio repoProd;
	

	public ProdutoControle(ProdutoRepositorio repoProd) {
		this.repoProd = repoProd;
	}

	@GetMapping("/produtos")
	public String pessoas(Model model) {
		model.addAttribute("listaProdutos", repoProd.findAll());
		return "produtos/index";
	}
	
	@GetMapping("/produtos/novo")
	public String novaPessoa(Model model) {
		model.addAttribute("produto", new Produto(""));
		return "produtos/form";
	}
	
	
	@PostMapping("/produtos/salvar")
	public String salvarProduto(@Valid @ModelAttribute("produto") Produto produto, BindingResult bindingResult, Model model) {
		
		repoProd.save(produto);
		return "redirect:/produtos";
	}
	
	@GetMapping("/produtos/{id}")
	public String alterarProduto(@PathVariable("id") long id, Model model) {
		Optional<Produto> prodOpt = repoProd.findById(id);
		if (!prodOpt.isPresent()) {
			throw new IllegalArgumentException("Pessoa inválida.");
		}
		
		model.addAttribute("produto", prodOpt.get());
		
		return "produtos/form";
	}
	
	@GetMapping("/produtos/excluir/{id}")
	public String excluirProduto(@PathVariable("id") long id) {
		Optional<Produto> prodOpt = repoProd.findById(id);
		
		if(!prodOpt.isPresent()) {
			throw new IllegalArgumentException("Pessoa Inválida");
		}
		
		repoProd.delete(prodOpt.get());
		return "redirect:/produtos";
	}

}
