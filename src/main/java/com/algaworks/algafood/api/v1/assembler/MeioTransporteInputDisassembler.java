package com.algaworks.algafood.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.v1.model.input.MeioTransporteInput;
import com.algaworks.algafood.domain.model.MeioTransporte;
import com.algaworks.algafood.domain.model.Modelo;
import com.algaworks.algafood.domain.model.Restaurante;

@Component
public class MeioTransporteInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public MeioTransporte toDomainObject(MeioTransporteInput meioTransporteInput) {
		return modelMapper.map(meioTransporteInput, MeioTransporte.class);
	}
	
	public void copyToDomainObject(MeioTransporteInput meioTransporteInput, MeioTransporte meioTransporte) {
		// Para evitar org.hibernate.HibernateException: identifier of an instance of 
		// com.algaworks.algafood.domain.model.Modelo was altered from 1 to 2
		meioTransporte.setModelo(new Modelo());
		meioTransporte.setRestaurante(new Restaurante());
		
		modelMapper.map(meioTransporteInput, meioTransporte);
	}
	
}
