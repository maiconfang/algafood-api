package com.algaworks.algafood.domain.filter;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GrupoFilter {

	@ApiModelProperty(example = "1", value = "ID do grupo da pesquisa")
	private Long grupoId;
	
	@ApiModelProperty(example = "1", value = "Nome do grupo da pesquisa")
	private String nome;
	
	
}
