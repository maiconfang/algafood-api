package com.algaworks.algafood.api.v1.model.input;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ModeloInput {

	@ApiModelProperty(example = "Nissan", required = true)
	@NotBlank
	private String nome;
	
}
