package com.loja.gamer.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.loja.gamer.model.Produto;

public interface ProdutosRepository extends JpaRepository<Produto, Long> {
	
	public List<Produto> findByNomeContainingIgnoreCase(String nome);
	
	public List<Produto> findByPrecoGreaterThanOrderByPreco(BigDecimal preco);
	
	public List<Produto> findByPrecoLessThanOrderByPreco(BigDecimal preco);

}
