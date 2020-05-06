package com.algaworks.algafood.domain.filter;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CozinhaFilter {

	@ApiModelProperty(example = "1", value = "ID da cozinha da pesquisa")
	private Long cozinhaId;
	
	@ApiModelProperty(example = "1", value = "Nome da cozinha da pesquisa")
	private String nome;
	
	
}
