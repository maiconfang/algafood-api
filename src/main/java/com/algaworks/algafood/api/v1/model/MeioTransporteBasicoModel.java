package com.algaworks.algafood.api.v1.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "meiosTransporte")
@Setter
@Getter
public class MeioTransporteBasicoModel extends RepresentationModel<MeioTransporteBasicoModel> {

	@ApiModelProperty(example = "1")
	private Long id;
	
	@ApiModelProperty(example = "Bicicleta caloy")
	private String nome;
	
	
}
