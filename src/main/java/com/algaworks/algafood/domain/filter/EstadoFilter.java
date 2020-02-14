package com.algaworks.algafood.domain.filter;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EstadoFilter {

	@ApiModelProperty(example = "1", value = "ID do estado da pesquisa")
	private Long estadoId;
	
	@ApiModelProperty(example = "1", value = "Nome do estado da pesquisa")
	private String nome;
	
	
}
