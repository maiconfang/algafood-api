package com.algaworks.algafood.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.v1.model.input.ModeloInput;
import com.algaworks.algafood.domain.model.Modelo;

@Component
public class ModeloInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public Modelo toDomainObject(ModeloInput modeloInput) {
		return modelMapper.map(modeloInput, Modelo.class);
	}
	
	public void copyToDomainObject(ModeloInput modeloInput, Modelo modelo) {
		modelMapper.map(modeloInput, modelo);
	}
	
}
