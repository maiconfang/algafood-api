package com.algaworks.algafood.api.v1.openapi.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.v1.model.MeioTransporteModel;
import com.algaworks.algafood.api.v1.model.input.MeioTransporteInput;
import com.algaworks.algafood.domain.filter.MeioTransporteFilter;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Meios de transporte")
public interface MeioTransporteControllerOpenApi {

	@ApiOperation("Lista os meios de transporte")
	PagedModel<MeioTransporteModel> listar(MeioTransporteFilter filtro, Pageable pageable);
	
	@ApiOperation("Busca um meio de transporte por ID")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID do meio de transporte inválido", response = Problem.class),
		@ApiResponse(code = 404, message = "Meio de transporte não encontrado", response = Problem.class)
	})
	MeioTransporteModel buscar(
			@ApiParam(value = "ID de um meio de transporte", example = "1", required = true)
			Long meioTransporteId);
	
	@ApiOperation("Cadastra um meio de transporte")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Meio de transporte cadastrado"),
	})
	MeioTransporteModel adicionar(
			@ApiParam(name = "corpo", value = "Representação de um novo meio de transporte", required = true)
			MeioTransporteInput meioTransporteInput);
	
	@ApiOperation("Atualiza um meio transporte por ID")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Meio de transporte atualizado"),
		@ApiResponse(code = 404, message = "Meio de transporte não encontrado", response = Problem.class)
	})
	MeioTransporteModel atualizar(
			@ApiParam(value = "ID de um meio de transporte", example = "1", required = true) 
			Long meioTransporteId,
			
			@ApiParam(name = "corpo", value = "Representação de um meio de transporte com os novos dados", required = true)
			MeioTransporteInput meioTransporteInput);
	
	@ApiOperation("Exclui um meio de transporte por ID")
	@ApiResponses({
		@ApiResponse(code = 204, message = "meio de transporte excluído"),
		@ApiResponse(code = 404, message = "meio de transporte não encontrado", response = Problem.class)
	})
	void remover(
			@ApiParam(value = "ID de um meio de transporte", example = "1", required = true)
			Long meioTransporteId);
	
}
