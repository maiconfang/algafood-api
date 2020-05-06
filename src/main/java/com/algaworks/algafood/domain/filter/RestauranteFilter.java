package com.algaworks.algafood.domain.filter;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RestauranteFilter {

	@ApiModelProperty(example = "1", value = "ID do restaurante da pesquisa")
	private Long restauranteId;
	
	@ApiModelProperty(example = "1", value = "Nome do restaurante da pesquisa")
	private String nome;
	
	
}
