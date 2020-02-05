package com.algaworks.algafood.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.v1.AlgaLinks;
import com.algaworks.algafood.api.v1.controller.MeioTransporteController;
import com.algaworks.algafood.api.v1.model.MeioTransporteModel;
import com.algaworks.algafood.core.security.AlgaSecurity;
import com.algaworks.algafood.domain.model.MeioTransporte;

@Component
public class MeioTransporteModelAssembler 
		extends RepresentationModelAssemblerSupport<MeioTransporte, MeioTransporteModel> {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private AlgaLinks algaLinks;
	
	@Autowired
	private AlgaSecurity algaSecurity;
	
	public MeioTransporteModelAssembler() {
		super(MeioTransporteController.class, MeioTransporteModel.class);
	}
	
	@Override
	public MeioTransporteModel toModel(MeioTransporte meioTransporte) {
		MeioTransporteModel meioTransporteModel = createModelWithId(meioTransporte.getId(), meioTransporte);
		
		modelMapper.map(meioTransporte, meioTransporteModel);
		
		if (algaSecurity.podeConsultarMeiosTransporte()) {
			meioTransporteModel.add(algaLinks.linkToMeioTransportes("meioTransportes"));
		}
		
		if (algaSecurity.podeConsultarModelos()) {
			meioTransporteModel.getModelo().add(algaLinks.linkToModelo(meioTransporteModel.getModelo().getId()));
		}
		
		if (algaSecurity.podeConsultarRestaurantes()) {
			meioTransporteModel.getRestaurante().add(algaLinks.linkToRestaurante(meioTransporteModel.getRestaurante().getId()));
		}
		
		return meioTransporteModel;
	}
	
	@Override
	public CollectionModel<MeioTransporteModel> toCollectionModel(Iterable<? extends MeioTransporte> entities) {
		CollectionModel<MeioTransporteModel> collectionModel = super.toCollectionModel(entities);
		
		if (algaSecurity.podeConsultarMeiosTransporte()) {
			collectionModel.add(algaLinks.linkToMeioTransportes());
		}
		
		return collectionModel;
	}
	
}
