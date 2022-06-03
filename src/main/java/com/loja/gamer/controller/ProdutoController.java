package com.loja.gamer.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.loja.gamer.model.Produtos;
import com.loja.gamer.repository.CategoriaRepository;
import com.loja.gamer.repository.ProdutosRepository;

@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProdutoController {

	@Autowired
	private ProdutosRepository produtoRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@GetMapping
	public ResponseEntity<List<Produtos>> getAll(){
		return ResponseEntity.ok(produtoRepository.findAll());
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Produtos> getById(@PathVariable Long id){
			return produtoRepository.findById(id)
					.map(resp -> ResponseEntity.ok(resp))
					.orElse(ResponseEntity.notFound().build());
			}
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Produtos>> getByNome(@PathVariable String nome){
			return ResponseEntity.ok(produtoRepository.findByNomeContainingIgnoreCase(nome));
	}
	
	@PostMapping
	public ResponseEntity<Produtos> postProduto(@Valid @RequestBody Produtos produto){
		
		if (categoriaRepository.existsById(produto.getCategoria().getId()))
			return ResponseEntity.status(HttpStatus.CREATED).body(produtoRepository.save(produto));
			
		return ResponseEntity.notFound().build();
	
	}
	
	@PutMapping
	public ResponseEntity<Produtos> putProduto(@Valid @RequestBody Produtos produto) {
					
		return produtoRepository.findById(produto.getId())
				.map(resposta -> {
					return ResponseEntity.ok().body(produtoRepository.save(produto));
				})
				.orElse(ResponseEntity.notFound().build());

}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteProduto(@PathVariable Long id) {
		
		return produtoRepository.findById(id)
				.map(resposta -> {
					produtoRepository.deleteById(id);
					return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
				})
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/preco_maior/{preco}")
	public ResponseEntity<List<Produtos>> getPrecoMaiorQue(@PathVariable BigDecimal preco){ 
		return ResponseEntity.ok(produtoRepository.findByPrecoGreaterThanOrderByPreco(preco));
	}
	
	@GetMapping("/preco_menor/{preco}")
	public ResponseEntity<List<Produtos>> getPrecoMenorQue(@PathVariable BigDecimal preco){ 
		return ResponseEntity.ok(produtoRepository.findByPrecoLessThanOrderByPreco(preco));
	}
}
	
	
	
	
	
