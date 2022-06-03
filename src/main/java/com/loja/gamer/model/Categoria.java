package com.loja.gamer.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_categorias")
public class Categoria {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "O atributo tipo é obrigatorio e não pode conter espaços em branco")
	@Size(min = 5, max = 255, message = "O atributo tipo deve conter no min 5 caracteres e no máximo 255")
	private String tipo;
	
	@OneToMany(mappedBy = "categoria", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties("categoria")
	private List<Produtos> produtos;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	

}
