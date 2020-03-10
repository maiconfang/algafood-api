package com.algaworks.algafood.api.v1.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
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
import com.algaworks.algafood.core.data.PageWrapper;
import com.algaworks.algafood.core.data.PageableTranslator;
import com.algaworks.algafood.core.security.CheckSecurity;
import com.algaworks.algafood.domain.filter.FuncionarioFilter;
import com.algaworks.algafood.domain.model.Funcionario;
import com.algaworks.algafood.domain.repository.FuncionarioRepository;
import com.algaworks.algafood.domain.service.CadastroFuncionarioService;
import com.algaworks.algafood.infrastructure.repository.spec.FuncionarioSpecs;

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
	
	@Autowired
	private PagedResourcesAssembler<Funcionario> pagedResourcesAssembler;
	
	@CheckSecurity.Funcionarios.PodeConsultar
	@Override
	@GetMapping
	public PagedModel<FuncionarioModel> listar(FuncionarioFilter filtro, Pageable pageable) {
		
		Pageable pageableTraduzido = traduzirPageable(pageable);
		Page<Funcionario> funcionariosPage = null;
		
		if(filtro.getNome()!=null ) {
			funcionariosPage = funcionarioRepository.findAll(FuncionarioSpecs.comNome(filtro), pageableTraduzido);
		}
		else
			funcionariosPage = funcionarioRepository.findAll(pageable);
		
		funcionariosPage = new PageWrapper<>(funcionariosPage, pageable);
		
		return pagedResourcesAssembler.toModel(funcionariosPage, funcionarioModelAssembler);
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
	
	private Pageable traduzirPageable(Pageable apiPageable) {
		var mapeamento = Map.of(
				"id", "código",
				"nome", "nome",
				"rg", "rg",
				"cpf", "cpf" 
			);
		
		return PageableTranslator.translate(apiPageable, mapeamento);
	}
	
}
