package com.algaworks.algafood.domain.filter;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FuncionarioFilter {

	@ApiModelProperty(example = "1", value = "ID do funcionário da pesquisa")
	private Long funcionarioId;
	
	@ApiModelProperty(example = "1", value = "Nome do funcionário da pesquisa")
	private String nome;
	
	@ApiModelProperty(example = "1", value = "RG do funcionário da pesquisa")
	private String rg;
	
	@ApiModelProperty(example = "1", value = "CPF do funcionário da pesquisa")
	private String cpf;
	
	
}
