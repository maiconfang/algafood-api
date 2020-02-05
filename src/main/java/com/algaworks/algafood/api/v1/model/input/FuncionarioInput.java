package com.algaworks.algafood.api.v1.model.input;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FuncionarioInput {

	@ApiModelProperty(example = "Alexander", required = true)
	@NotBlank
	private String nome;
	
	@ApiModelProperty(example = "8.478.488-8", required = true)
	@NotBlank
	private String rg;
	
	@ApiModelProperty(example = "547.942.447-22", required = true)
	@NotBlank
	private String cpf;
	
	@Valid
	@NotNull
	private EnderecoInput endereco;
	
}
