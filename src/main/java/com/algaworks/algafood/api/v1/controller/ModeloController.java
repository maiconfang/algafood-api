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

import com.algaworks.algafood.api.v1.assembler.ModeloInputDisassembler;
import com.algaworks.algafood.api.v1.assembler.ModeloModelAssembler;
import com.algaworks.algafood.api.v1.model.ModeloModel;
import com.algaworks.algafood.api.v1.model.input.ModeloInput;
import com.algaworks.algafood.api.v1.openapi.controller.ModeloControllerOpenApi;
import com.algaworks.algafood.core.security.CheckSecurity;
import com.algaworks.algafood.domain.model.Modelo;
import com.algaworks.algafood.domain.repository.ModeloRepository;
import com.algaworks.algafood.domain.service.CadastroModeloService;

@RestController
@RequestMapping(path = "/v1/modelos", produces = MediaType.APPLICATION_JSON_VALUE)
public class ModeloController implements ModeloControllerOpenApi {

	@Autowired
	private ModeloRepository modeloRepository;
	
	@Autowired
	private CadastroModeloService cadastroModelo;
	
	@Autowired
	private ModeloModelAssembler modeloModelAssembler;
	
	@Autowired
	private ModeloInputDisassembler modeloInputDisassembler;
	
	@CheckSecurity.Modelos.PodeConsultar
	@Override
	@GetMapping
	public CollectionModel<ModeloModel> listar() {
		List<Modelo> todosModelos = modeloRepository.findAll();
		
		return modeloModelAssembler.toCollectionModel(todosModelos);
	}
	
	@CheckSecurity.Modelos.PodeConsultar
	@Override
	@GetMapping("/{modeloId}")
	public ModeloModel buscar(@PathVariable Long modeloId) {
		Modelo modelo = cadastroModelo.buscarOuFalhar(modeloId);
		
		return modeloModelAssembler.toModel(modelo);
	}
	
	@CheckSecurity.Modelos.PodeEditar
	@Override
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ModeloModel adicionar(@RequestBody @Valid ModeloInput modeloInput) {
		Modelo modelo = modeloInputDisassembler.toDomainObject(modeloInput);
		
		modelo = cadastroModelo.salvar(modelo);
		
		return modeloModelAssembler.toModel(modelo);
	}
	
	@CheckSecurity.Modelos.PodeEditar
	@Override
	@PutMapping("/{modeloId}")
	public ModeloModel atualizar(@PathVariable Long modeloId,
			@RequestBody @Valid ModeloInput modeloInput) {
		Modelo modeloAtual = cadastroModelo.buscarOuFalhar(modeloId);
		
		modeloInputDisassembler.copyToDomainObject(modeloInput, modeloAtual);
		
		modeloAtual = cadastroModelo.salvar(modeloAtual);
		
		return modeloModelAssembler.toModel(modeloAtual);
	}
	
	@CheckSecurity.Modelos.PodeEditar
	@Override
	@DeleteMapping("/{modeloId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long modeloId) {
		cadastroModelo.excluir(modeloId);	
	}
	
}
