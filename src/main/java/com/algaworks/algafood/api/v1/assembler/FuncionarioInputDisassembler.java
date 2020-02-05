package com.algaworks.algafood.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.v1.model.input.FuncionarioInput;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Funcionario;

@Component
public class FuncionarioInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public Funcionario toDomainObject(FuncionarioInput funcionarioInput) {
		return modelMapper.map(funcionarioInput, Funcionario.class);
	}
	
	public void copyToDomainObject(FuncionarioInput funcionarioInput, Funcionario funcionario) {
		
		if (funcionario.getEndereco() != null) {
			funcionario.getEndereco().setCidade(new Cidade());
		}
		
		modelMapper.map(funcionarioInput, funcionario);
	}
	
}
