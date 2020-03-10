package com.algaworks.algafood.domain.filter;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PermissaoFilter {

	@ApiModelProperty(example = "1", value = "ID da permissão da pesquisa")
	private Long permissaoId;
	
	@ApiModelProperty(example = "1", value = "Nome da permissao da pesquisa")
	private String nome;
	
	@ApiModelProperty(example = "1", value = "Descrição da permissao da pesquisa")
	private String descricao;
	
	
}
