package com.algaworks.algafood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.v1.model.FuncionarioModel;
import com.algaworks.algafood.api.v1.model.input.FuncionarioInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Funcionário")
public interface FuncionarioControllerOpenApi {

	@ApiOperation("Lista os funcionários")
	CollectionModel<FuncionarioModel> listar();
	
	@ApiOperation("Busca um funcionário por ID")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID do funcionário inválido", response = Problem.class),
		@ApiResponse(code = 404, message = "Funcionário não encontrado", response = Problem.class)
	})
	FuncionarioModel buscar(
			@ApiParam(value = "ID de um funcionário", example = "1", required = true)
			Long funcionarioId);
	
	@ApiOperation("Cadastra um funcionário")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Funcionário cadastrado"),
	})
	FuncionarioModel adicionar(
			@ApiParam(name = "corpo", value = "Representação de um novo funcionário", required = true)
			FuncionarioInput funcionarioInput);
	
	@ApiOperation("Atualiza um funcionário por ID")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Funcionário atualizado"),
		@ApiResponse(code = 404, message = "Funcionário não encontrado", response = Problem.class)
	})
	FuncionarioModel atualizar(
			@ApiParam(value = "ID de um funcionário", example = "1", required = true) 
			Long funcionarioId,
			
			@ApiParam(name = "corpo", value = "Representação de um funcionário com os novos dados", required = true)
			FuncionarioInput FuncionarioInput);
	
	@ApiOperation("Exclui um funcionário por ID")
	@ApiResponses({
		@ApiResponse(code = 204, message = "funcionário excluído"),
		@ApiResponse(code = 404, message = "funcionário não encontrado", response = Problem.class)
	})
	void remover(
			@ApiParam(value = "ID de um funcionário", example = "1", required = true)
			Long funcionárioId);
	
}
