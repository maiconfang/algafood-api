package com.algaworks.algafood.api.v1.model.input;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MeioTransporteInput {
	
	@ApiModelProperty(example = "1", required = true)
	private Long id;

	@ApiModelProperty(example = "Palio SL", required = true)
	@NotBlank
	private String nome;
	
	@ApiModelProperty(example = "Preto", required = true)
	@NotBlank
	private String cor;
	
	@Valid
	@NotNull
	private ModeloIdInput modelo;
	
	@Valid
	@NotNull
	private RestauranteIdInput restaurante;
	
	@Valid
	@NotNull
	private FuncionarioIdInput funcionario;
	
}
