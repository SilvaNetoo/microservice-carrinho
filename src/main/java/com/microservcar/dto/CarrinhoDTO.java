package com.microservcar.dto;

import java.io.Serializable;

public class CarrinhoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	private Integer idUsuario;

	public CarrinhoDTO() {
		super();
	}

	public CarrinhoDTO(Integer id, Integer idUsuario) {
		super();
		this.id = id;
		this.idUsuario = idUsuario;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

}
