package com.atividade2.venda.controle;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.atividade2.venda.entidade.PedidoItem;
import com.atividade2.venda.entidade.PedidoItemRepositorio;
import com.atividade2.venda.entidade.ProdutoRepositorio;

@Controller
public class PedidoItemControle {
	

	private PedidoItemRepositorio pedidoItemRepo; 
	private ProdutoRepositorio produtoRepo; 
	
	
	public PedidoItemControle(PedidoItemRepositorio pedidoItemRepo, ProdutoRepositorio produtoRedo) {
		this.produtoRepo = produtoRedo;
		this.pedidoItemRepo = pedidoItemRepo;

	}
	
//	@GetMapping("/pedidoItens/{id}")
//	public String alterarPedidoItem(@PathVariable("id") long id, Model model) {
//		Optional<PedidoItem> pedidoItemOpt = pedidoItemRepo.findById(id);
//		if (!pedidoItemOpt.isPresent()) {
//			throw new IllegalArgumentException("Pedido Item Invalido.");
//		}
//		
//		model.addAttribute("pedidoItem", pedidoItemOpt.get());
//		model.addAttribute("produtos", produtoRepo.findAll());
//			
//		
//		return "pedidoItens/form";
//	}
//
//	@PostMapping("pedidoItens/salvar")
//	public String salvarPessoa(@ModelAttribute("pedidoItem") PedidoItem pedidoItem, BindingResult bindingResult, Model model) {
//		if(bindingResult.hasErrors()) {
//			model.addAttribute("pessoas", produtoRepo.findAll());
//			return "pedidoItens/form";
//		}
//		
//		pedidoItemRepo.save(pedidoItem);
//		return "redirect:pedidos";
//	}
//	
//	
//	@GetMapping("pedidoItens/excluir/{id}")
//	public String excluirPedido(@PathVariable("id") long id, Model model) {
//		Optional<PedidoItem> pedidoOpt = pedidoItemRepo.findById(id);
//		if (!pedidoOpt.isPresent()) {
//			throw new IllegalArgumentException("Pedido Invalido.");
//		}
//		
//		pedidoItemRepo.delete(pedidoOpt.get());
//		
//		return "redirect:/pedidos";
//	}
	
}
