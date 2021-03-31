package com.atividade2.venda.controle;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.atividade2.venda.entidade.Pedido;
import com.atividade2.venda.entidade.PedidoItem;
import com.atividade2.venda.entidade.PedidoItemRepositorio;
import com.atividade2.venda.entidade.PedidoRepositorio;
import com.atividade2.venda.entidade.Pessoa;
import com.atividade2.venda.entidade.PessoaRepositorio;
import com.atividade2.venda.entidade.ProdutoRepositorio;


import org.springframework.ui.Model;

@Controller
public class PedidoControle {
	
	private PedidoRepositorio pedidoRepo;
	private PedidoItemRepositorio pedidoItemRepo;
	private PessoaRepositorio pessoaRepo;
	private ProdutoRepositorio produtoRepo;


	public PedidoControle(PedidoRepositorio pedidoRepo, PessoaRepositorio pessoaRepo, ProdutoRepositorio produtoRepo, PedidoItemRepositorio pedidoItemRepo) {
		this.pedidoRepo = pedidoRepo;
		this.pedidoItemRepo = pedidoItemRepo;
		this.pessoaRepo = pessoaRepo;
		this.produtoRepo = produtoRepo;
	}
	
	@RequestMapping("/pedidos")
	public String getPedidos(Model model) {
		model.addAttribute("listaPedidos", pedidoRepo.findAll());
		return "pedidos/index";
	}
	
	@GetMapping("/pedidos/novo")
	public String novoPedido(Model model) {
		model.addAttribute("pedido", new Pedido());
		model.addAttribute("pessoas", pessoaRepo.findAll());
		model.addAttribute("produtos", produtoRepo.findAll());
		return "pedidos/form";
	}
	
	@GetMapping("/pedidos/{id}")
	public String alterarPedido(@PathVariable("id") long id, Model model) {
		Optional<Pedido> pedidoOpt = pedidoRepo.findCompletoById(id);
		if (!pedidoOpt.isPresent()) {
			throw new IllegalArgumentException("Pedido Invalido.");
		}
		
		model.addAttribute("pedido", pedidoOpt.get());
		model.addAttribute("pessoas", pessoaRepo.findAll());
		model.addAttribute("produtos", produtoRepo.findAll());
		
		
		return "pedidos/form";
	}
	
	@GetMapping("/pedidos/excluir/{id}")
	public String excluirPedido(@PathVariable("id") long id, Model model) {
	
		Optional<Pedido> pedidoOpt = pedidoRepo.findById(id);
		
		if(!pedidoOpt.isPresent()) {
			throw new IllegalArgumentException("Pedido Inválida");
		}
		
		pedidoRepo.delete(pedidoOpt.get());
		return "redirect:/pedidos";
		
	}
	
	@PostMapping("/pedidos/salvar")
	public String salvarContato(@Valid Pedido pedido, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "pedidos/form";
		}
		
		pedido.corrigirItens();
		pedidoRepo.save(pedido);
		
		return "redirect:/pedidos";
	}
	

	@RequestMapping(value="/pedidos/salvar", params = {"addItem"})
	public String addItem(Pedido pedido, BindingResult bindingResult, Model model) {
		
		pedido.addPedidoItem(new PedidoItem());
		
		String fieldId = "itens" + (pedido.getItensPedido().size() - 1) + ".numero";
		model.addAttribute("fieldToFocus", fieldId);
		
		model.addAttribute("pessoas", pessoaRepo.findAll());
		model.addAttribute("produtos", produtoRepo.findAll());
		return "pedidos/form";
	}
	
	@RequestMapping(value="/pedidos/salvar", params = {"removeItem"})
	public String removeItem(Pedido pedido, BindingResult bindingResult, HttpServletRequest req, Model model) {
	final Integer itemIndex = Integer.valueOf(req.getParameter("removeItem"));
		
		pedido.removePedidoItem(itemIndex.intValue());
		model.addAttribute("pessoas", pessoaRepo.findAll());
		model.addAttribute("produtos", produtoRepo.findAll());
		return "pedidos/form";
	}

}
