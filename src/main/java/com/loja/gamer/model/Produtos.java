package com.loja.gamer.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_produtos")
public class Produtos {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private Long id;
	
	@NotBlank(message = "O atributo nome é obrigatorio e não pode conter espaços em branco")
	@Size(min = 5, max = 255, message = "O atributo nome deve conter no minimo 5 e no maximo 255 caracteres" )
	private String nome;
	
	@Size(min = 10, max = 500, message = "O atributo descricao deve conter no minimo 10 e no maximo 500 caracteres")
	private String descricao;
	
	@NotBlank(message = "O atributo console é obrigatorio e não pode conter espaços em branco")
	private String console;
	
	private int quantidade;
	
	@UpdateTimestamp
	private LocalDate dataLancamento;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	@NotBlank(message ="O atributo preco é obrigatorio e não pode conter espaços em branco")
	@Positive(message = "O preco deve ser maior que zero")
	private BigDecimal preco;
	
	private String foto;
	
	@ManyToOne
	@JsonIgnoreProperties("produtos")
	private Categoria categoria;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getConsole() {
		return console;
	}
	public void setConsole(String console) {
		this.console = console;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public LocalDate getDataLancamento() {
		return dataLancamento;
	}
	public void setDataLancamento(LocalDate dataLancamento) {
		this.dataLancamento = dataLancamento;
	}
	public BigDecimal getPreco() {
		return preco;
	}
	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

}
