package com.atividade2.venda.entidade;

import java.util.Optional;

public interface PedidoRepositoryCustom {
	Optional<Pedido> findCompletoById(Long id);
}
