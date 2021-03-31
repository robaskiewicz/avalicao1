package com.atividade2.venda.entidade;

import java.util.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.format.annotation.DateTimeFormat;



@Entity
public class Pedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@OneToMany
	(cascade = CascadeType.ALL, orphanRemoval = true,mappedBy = "pedido")
	private List<PedidoItem> itensPedido = new ArrayList<>();
	
	@ManyToOne
	private Pessoa pessoa;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date dataPedio;
	
	
	public Pedido() {
	}
	
	public Pedido(Date data, Pessoa pessoa) {
		this.dataPedio = data;
		this.setPessoa(pessoa);
	}
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	
	
	public Date getDataPedio() {
		return dataPedio;
	}

	public void setDataPedio(Date dataPedio) {
		this.dataPedio = dataPedio;
	}

	public void addPedidoItem(PedidoItem pedidoItem) {
		this.itensPedido.add(pedidoItem);
		pedidoItem.setPedido(this);
	}
	
	public void removePedidoItem(PedidoItem pedidoItem) {
		this.itensPedido.remove(pedidoItem);
		pedidoItem.setPedido(null);
	}
	
	public void removePedidoItem(int index) {
		PedidoItem pedidoItem = this.itensPedido.get(index);
		if (pedidoItem != null) {
			this.itensPedido.remove(index);
			pedidoItem.setPedido(null);
		}
	}
	
	public void corrigirItens() {
//		limparPedidosVazios();
		
		for (PedidoItem item : this.itensPedido) {
			item.setPedido(this);
		}
	}
	
//	private void limparPedidosVazios() {
//		List<PedidoItem> pedidosVazios = itensPedido.stream().filter(e -> e.isVazio()).collect(Collector.toList());
//		
//		for (PedidoItem pedido : this.itensPedido) {
//			removePedidoItem(pedido);
//		}
//	}

	public List<PedidoItem> getItensPedido() {
		return itensPedido;
	}

	public void setItensPedido(List<PedidoItem> itensPedido) {
		this.itensPedido = itensPedido;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}



	
	
	

}
