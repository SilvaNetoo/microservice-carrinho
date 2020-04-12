package com.microservcar.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.microservcar.domain.Carrinho;
import com.microservcar.services.CarrinhoService;

@RestController
@RequestMapping(value = "/carrinhos")
public class CarrinhoResource {

	@Autowired
	private CarrinhoService service;

	@GetMapping
	public ResponseEntity<List<Carrinho>> buscarTodos() {
		List<Carrinho> list = service.buscarTodos();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Carrinho> buscar(@PathVariable Integer id) {
		Carrinho obj = service.buscar(id);
		return ResponseEntity.ok().body(obj);
	}

	@PostMapping(value = "/{id}")
	public ResponseEntity<?> inserirCarrinho(@PathVariable("id") Integer id) {
		Carrinho c = new Carrinho(null, id);
		service.inserir(c);
		return ResponseEntity.ok().body(c);
	}

	@PostMapping
	public ResponseEntity<Carrinho> inserir(@RequestBody Carrinho obj) {
		obj = service.inserir(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> atualizar(@RequestBody Carrinho obj, @PathVariable Integer id) {
		obj.setId(id);
		obj = service.atualizar(obj);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{idProd}/produtos/{idCarrinho}")
	public ResponseEntity<Void> inserirProduto(@PathVariable("idProd") Integer idProd, @PathVariable("idCarrinho") Integer idCarrinho) {
		service.inserirProduto(idProd, idCarrinho);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Integer id) {
		service.deletar(id);
		return ResponseEntity.noContent().build();
	}
}