package com.algaworks.algafood.domain.filter;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FormaPagamentoFilter {

	@ApiModelProperty(example = "1", value = "ID da forma de pagamento pda pesquisa")
	private Long formaPagamentoId;
	
	@ApiModelProperty(example = "1", value = "Descricao da forma de pagamento da pesquisa")
	private String descricao;
	
	
}
