package com.algaworks.algafood.domain.filter;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UsuarioFilter {

	@ApiModelProperty(example = "1", value = "ID do usuário da pesquisa")
	private Long usuarioId;
	
	@ApiModelProperty(example = "1", value = "Nome do usuário da pesquisa")
	private String nome;
	
	@ApiModelProperty(example = "1", value = "E-mail do usuário da pesquisa")
	private String email;
	
	
}
