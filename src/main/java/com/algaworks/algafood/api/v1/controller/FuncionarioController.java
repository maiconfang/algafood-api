package com.algaworks.algafood.api.v1.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.ResourceUriHelper;
import com.algaworks.algafood.api.v1.assembler.FuncionarioInputDisassembler;
import com.algaworks.algafood.api.v1.assembler.FuncionarioModelAssembler;
import com.algaworks.algafood.api.v1.model.FuncionarioModel;
import com.algaworks.algafood.api.v1.model.input.FuncionarioInput;
import com.algaworks.algafood.api.v1.openapi.controller.FuncionarioControllerOpenApi;
import com.algaworks.algafood.core.security.CheckSecurity;
import com.algaworks.algafood.domain.model.Funcionario;
import com.algaworks.algafood.domain.repository.FuncionarioRepository;
import com.algaworks.algafood.domain.service.CadastroFuncionarioService;

@RestController
@RequestMapping(path = "/v1/funcionario", produces = MediaType.APPLICATION_JSON_VALUE)
public class FuncionarioController implements FuncionarioControllerOpenApi {

	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	@Autowired
	private CadastroFuncionarioService cadastroFuncionario;
	
	@Autowired
	private FuncionarioModelAssembler funcionarioModelAssembler;
	
	@Autowired
	private FuncionarioInputDisassembler funcionarioInputDisassembler;
	
	@CheckSecurity.Funcionarios.PodeConsultar
	@Override
	@GetMapping
	public CollectionModel<FuncionarioModel> listar() {
		List<Funcionario> todosFuncionarios = funcionarioRepository.findAll();
		
		return funcionarioModelAssembler.toCollectionModel(todosFuncionarios);
	}
	
	@CheckSecurity.Funcionarios.PodeConsultar
	@Override
	@GetMapping("/{funcionarioId}")
	public FuncionarioModel buscar(@PathVariable Long funcionarioId) {
		Funcionario funcionario = cadastroFuncionario.buscarOuFalhar(funcionarioId);
		
		return funcionarioModelAssembler.toModel(funcionario);
	}
	
	@CheckSecurity.Funcionarios.PodeConsultar
	@Override
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public FuncionarioModel adicionar(@RequestBody @Valid FuncionarioInput funcionarioInput) {
			Funcionario funcionario = funcionarioInputDisassembler.toDomainObject(funcionarioInput);
			
			funcionario = cadastroFuncionario.salvar(funcionario);
			
			FuncionarioModel funcionarioModel = funcionarioModelAssembler.toModel(funcionario);
			
			ResourceUriHelper.addUriInResponseHeader(funcionarioModel.getId());
			
			return funcionarioModel;
	}
	
	@CheckSecurity.Funcionarios.PodeConsultar
	@Override
	@PutMapping("/{funcionarioId}")
	public FuncionarioModel atualizar(@PathVariable Long funcionarioId,
			@RequestBody @Valid FuncionarioInput funcionarioInput) {

			Funcionario funcionarioAtual = cadastroFuncionario.buscarOuFalhar(funcionarioId);
			
			funcionarioInputDisassembler.copyToDomainObject(funcionarioInput, funcionarioAtual);
			
			funcionarioAtual = cadastroFuncionario.salvar(funcionarioAtual);
			
			return funcionarioModelAssembler.toModel(funcionarioAtual);
		
	}
	
	@CheckSecurity.Funcionarios.PodeConsultar
	@Override
	@DeleteMapping("/{funcionarioId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long funcionarioId) {
		cadastroFuncionario.excluir(funcionarioId);	
	}
	
}
