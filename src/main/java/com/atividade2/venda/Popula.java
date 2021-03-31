package com.atividade2.venda;

import java.util.Date;
import java.math.BigDecimal;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.atividade2.venda.entidade.Pedido;
import com.atividade2.venda.entidade.PedidoItem;
import com.atividade2.venda.entidade.PedidoRepositorio;
import com.atividade2.venda.entidade.Pessoa;
import com.atividade2.venda.entidade.PessoaRepositorio;
import com.atividade2.venda.entidade.Produto;
import com.atividade2.venda.entidade.ProdutoRepositorio;

@Component
@Transactional
public class Popula implements CommandLineRunner{
	
	@Autowired
	private PessoaRepositorio repositorioPessoa;
	@Autowired
	private ProdutoRepositorio repositorioProduto;
	@Autowired
	private PedidoRepositorio pedidoRepositorio;
	
	
	@Override
	public void run(String... args) throws Exception{
		
		Pessoa pessoa1 = new Pessoa("");
		pessoa1.setNome("Michel");
		pessoa1.setEmail("michel@gmail.com");
		pessoa1.setTelefone("44444444");
		
		Pessoa pessoa2 = new Pessoa("");
		pessoa2.setNome("William");
		pessoa2.setEmail("William@gmail.com");
		pessoa2.setTelefone("666666666");
	
		
		repositorioPessoa.save(pessoa1);
		repositorioPessoa.save(pessoa2);
		repositorioPessoa.flush();
		
		
		Produto produto1 = new Produto("");
		produto1.setNome("Corda");
		repositorioProduto.save(produto1);
		repositorioProduto.flush();
		
		
		Pedido pedido1 = new Pedido();
		pedido1.setPessoa(pessoa1);
		pedido1.setDataPedio(new Date());


		PedidoItem pedidoItem = new PedidoItem();
		pedidoItem.setPedido(pedido1);
		pedidoItem.setProduto(produto1);
		pedidoItem.setQuantidade(1);
		pedidoItem.setValor(new BigDecimal(10));

		pedido1.addPedidoItem(pedidoItem);
		
		pedidoRepositorio.save(pedido1);
		pedidoRepositorio.flush();
		
	}

}
