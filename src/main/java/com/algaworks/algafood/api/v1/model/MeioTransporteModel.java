package com.algaworks.algafood.api.v1.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "meiosTransporte")
@Setter
@Getter
public class MeioTransporteModel extends RepresentationModel<MeioTransporteModel> {

	@ApiModelProperty(example = "1")
	private Long id;
	
	@ApiModelProperty(example = "Honda LX")
	private String nome;
	
	@ApiModelProperty(example = "#a82a2a") 
	private String cor;
	
	private ModeloModel modelo;
	
	private RestauranteApenasNomeModel restaurante;
	
	private FuncionarioBasicoModel funcionario;
	
}
