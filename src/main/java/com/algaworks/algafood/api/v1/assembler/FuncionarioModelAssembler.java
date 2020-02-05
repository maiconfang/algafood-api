package com.algaworks.algafood.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.v1.AlgaLinks;
import com.algaworks.algafood.api.v1.controller.FuncionarioController;
import com.algaworks.algafood.api.v1.model.FuncionarioModel;
import com.algaworks.algafood.core.security.AlgaSecurity;
import com.algaworks.algafood.domain.model.Funcionario;

@Component
public class FuncionarioModelAssembler extends RepresentationModelAssemblerSupport<Funcionario, FuncionarioModel> {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private AlgaLinks algaLinks;
	
	@Autowired
	private AlgaSecurity algaSecurity;
	
	public FuncionarioModelAssembler() {
		super(FuncionarioController.class, FuncionarioModel.class);
	}
	
	@Override
	public FuncionarioModel toModel(Funcionario funcionario) {
		FuncionarioModel funcionarioModel = createModelWithId(funcionario.getId(), funcionario);
		
		modelMapper.map(funcionario, funcionarioModel);
		
		if (algaSecurity.podeConsultarFuncionarios()) {
			funcionarioModel.add(algaLinks.linkToFuncionarios("funcionarios"));
		}
		
		if (algaSecurity.podeConsultarCidades()) {
			if (funcionarioModel.getEndereco() != null 
					&& funcionarioModel.getEndereco().getCidade() != null) {
				funcionarioModel.getEndereco().getCidade().add(
						algaLinks.linkToCidade(funcionario.getEndereco().getCidade().getId()));
			}
		}
		
		return funcionarioModel;
	}
	
	@Override
	public CollectionModel<FuncionarioModel> toCollectionModel(Iterable<? extends Funcionario> entities) {
		CollectionModel<FuncionarioModel> collectionModel = super.toCollectionModel(entities);
		
		if (algaSecurity.podeConsultarFuncionarios()) {
			collectionModel.add(algaLinks.linkToFuncionarios());
		}
		
		return collectionModel;
	}
	
}
