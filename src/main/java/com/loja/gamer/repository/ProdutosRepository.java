package com.loja.gamer.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.loja.gamer.model.Produtos;

public interface ProdutosRepository extends JpaRepository<Produtos, Long> {
	
	public List<Produtos> findByNomeContainingIgnoreCase(String nome);
	
	public List<Produtos> findByPrecoGreaterThanOrderByPreco(BigDecimal preco);
	
	public List<Produtos> findByPrecoLessThanOrderByPreco(BigDecimal preco);

}
