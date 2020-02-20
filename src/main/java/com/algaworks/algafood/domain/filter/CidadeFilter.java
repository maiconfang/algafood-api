package com.algaworks.algafood.domain.filter;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CidadeFilter {

	@ApiModelProperty(example = "1", value = "ID da cidade da pesquisa")
	private Long cidadeId;
	
	@ApiModelProperty(example = "1", value = "Nome da cidade da pesquisa")
	private String nome;
	
	
}
