package com.algaworks.algafood.domain.exception;

public class MeioTransporteNaoEncontradaException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public MeioTransporteNaoEncontradaException(String mensagem) {
		super(mensagem);
	}
	
	public MeioTransporteNaoEncontradaException(Long meioTransporteId) {
		this(String.format("Não existe um cadastro de meio de transporte com código %d", meioTransporteId));
	}
	
}
