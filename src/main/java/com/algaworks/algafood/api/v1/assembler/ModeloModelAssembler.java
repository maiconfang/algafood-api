package com.algaworks.algafood.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.v1.AlgaLinks;
import com.algaworks.algafood.api.v1.controller.ModeloController;
import com.algaworks.algafood.api.v1.model.ModeloModel;
import com.algaworks.algafood.core.security.AlgaSecurity;
import com.algaworks.algafood.domain.model.Modelo;

@Component
public class ModeloModelAssembler 
		extends RepresentationModelAssemblerSupport<Modelo, ModeloModel> {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private AlgaLinks algaLinks;
	
	@Autowired
	private AlgaSecurity algaSecurity;
	
	public ModeloModelAssembler() {
		super(ModeloController.class, ModeloModel.class);
	}
	
	@Override
	public ModeloModel toModel(Modelo modelo) {
		ModeloModel modeloModel = createModelWithId(modelo.getId(), modelo);
		modelMapper.map(modelo, modeloModel);
		
		if (algaSecurity.podeConsultarModelos()) {
			modeloModel.add(algaLinks.linkToModelos("modelos"));
		}
		
		return modeloModel;
	}
	
	@Override
	public CollectionModel<ModeloModel> toCollectionModel(Iterable<? extends Modelo> entities) {
		CollectionModel<ModeloModel> collectionModel = super.toCollectionModel(entities);
		
		if (algaSecurity.podeConsultarModelos()) {
			collectionModel.add(algaLinks.linkToModelos());
		}
		
		return collectionModel;
	}
	
}
