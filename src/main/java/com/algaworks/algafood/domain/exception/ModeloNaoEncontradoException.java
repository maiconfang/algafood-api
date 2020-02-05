package com.algaworks.algafood.domain.exception;

public class ModeloNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public ModeloNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public ModeloNaoEncontradoException(Long modeloId) {
		this(String.format("Não existe um cadastro de modelo com código %d", modeloId));
	}
	
}
