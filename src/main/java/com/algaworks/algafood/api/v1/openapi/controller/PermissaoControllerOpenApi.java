package com.algaworks.algafood.api.v1.openapi.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

import com.algaworks.algafood.api.v1.model.PermissaoModel;
import com.algaworks.algafood.domain.filter.PermissaoFilter;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Permissões")
public interface PermissaoControllerOpenApi {

	@ApiOperation("Lista as permissões")
	PagedModel<PermissaoModel> listar(PermissaoFilter filtro, Pageable pageable);
	
}
