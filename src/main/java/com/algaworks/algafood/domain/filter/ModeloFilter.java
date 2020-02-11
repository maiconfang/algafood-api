package com.algaworks.algafood.domain.filter;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ModeloFilter {

	@ApiModelProperty(example = "1", value = "ID do modelo da pesquisa")
	private Long modeloId;
	
	@ApiModelProperty(example = "1", value = "Nome do modelo da pesquisa")
	private String nome;
	
	
}
