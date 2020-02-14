package com.algaworks.algafood.api.v1.openapi.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.v1.model.ModeloModel;
import com.algaworks.algafood.api.v1.model.input.ModeloInput;
import com.algaworks.algafood.domain.filter.ModeloFilter;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Modelos")
public interface ModeloControllerOpenApi {

	@ApiOperation("Lista os modelos")
	PagedModel<ModeloModel> listar(ModeloFilter filtro, Pageable pageable);

	@ApiOperation("Busca um modelo por ID")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID do modelo inválido", response = Problem.class),
		@ApiResponse(code = 404, message = "Modelo não encontrado", response = Problem.class)
	})
	ModeloModel buscar(
			@ApiParam(value = "ID de um modelo", example = "1", required = true)
			Long modeloId);

	@ApiOperation("Cadastra um modelo")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Modelo cadastrado"),
	})
	ModeloModel adicionar(
			@ApiParam(name = "corpo", value = "Representação de um novo modelo", required = true)
			ModeloInput modeloInput);

	@ApiOperation("Atualiza um modelo por ID")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Modelo atualizado"),
		@ApiResponse(code = 404, message = "Modelo não encontrado", response = Problem.class)
	})
	ModeloModel atualizar(
			@ApiParam(value = "ID de um modelo", example = "1", required = true)
			Long modeloId,
			
			@ApiParam(name = "corpo", value = "Representação de um modelo com os novos dados", required = true)
			ModeloInput modeloInput);

	@ApiOperation("Exclui um modelo por ID")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Modelo excluído"),
		@ApiResponse(code = 404, message = "Modelo não encontrado", response = Problem.class)
	})
	void remover(
			@ApiParam(value = "ID de um modelo", example = "1", required = true)
			Long modeloId);

}