package com.algaworks.algafood.domain.filter;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MeioTransporteFilter {

	@ApiModelProperty(example = "1", value = "ID do meio de transporte da pesquisa")
	private Long meioTransporteId;
	
	@ApiModelProperty(example = "1", value = "Nome do meio de transporte da pesquisa")
	private String nome;
	
	
}
