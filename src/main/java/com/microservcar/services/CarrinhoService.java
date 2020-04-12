package com.microservcar.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservcar.domain.Carrinho;
import com.microservcar.exceptions.ObjectNotFoundException;
import com.microservcar.repositories.CarrinhoRepository;

@Service
public class CarrinhoService {

	@Autowired
	private CarrinhoRepository repo;

	public List<Carrinho> buscarTodos() {
		return repo.findAll();
	}

	public Carrinho buscar(Integer id) {
		Optional<Carrinho> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Carrinho.class.getName()));
	}

	public Carrinho inserir(Carrinho obj) {
		obj.setId(null);
		return repo.save(obj);
	}

	public Carrinho atualizar(Carrinho obj) {
		buscar(obj.getId());
		return repo.save(obj);
	}

	public Carrinho inserirProduto(Integer idProd, Integer idCarrinho) {
		Carrinho obj = buscar(idCarrinho);
		obj.setProdutos(idProd);
		return repo.save(obj);
	}

	public void deletar(Integer id) {
		buscar(id);
		repo.deleteById(id);
	}

}
