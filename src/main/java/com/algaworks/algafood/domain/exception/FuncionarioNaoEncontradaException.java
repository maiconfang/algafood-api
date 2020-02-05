package com.algaworks.algafood.domain.exception;

public class FuncionarioNaoEncontradaException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public FuncionarioNaoEncontradaException(String mensagem) {
		super(mensagem);
	}
	
	public FuncionarioNaoEncontradaException(Long funcionarioId) {
		this(String.format("Não existe um cadastro de funcionário com código %d", funcionarioId));
	}
	
}
