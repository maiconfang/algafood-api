package com.algaworks.algafood.api.v1.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.v1.assembler.PermissaoModelAssembler;
import com.algaworks.algafood.api.v1.model.PermissaoModel;
import com.algaworks.algafood.api.v1.openapi.controller.PermissaoControllerOpenApi;
import com.algaworks.algafood.core.data.PageWrapper;
import com.algaworks.algafood.core.data.PageableTranslator;
import com.algaworks.algafood.core.security.CheckSecurity;
import com.algaworks.algafood.domain.filter.PermissaoFilter;
import com.algaworks.algafood.domain.model.Permissao;
import com.algaworks.algafood.domain.repository.PermissaoRepository;
import com.algaworks.algafood.infrastructure.repository.spec.PermissaoSpecs;

@RestController
@RequestMapping(path = "/v1/permissoes", produces = MediaType.APPLICATION_JSON_VALUE)
public class PermissaoController implements PermissaoControllerOpenApi {

	@Autowired
	private PermissaoRepository permissaoRepository;
	
	@Autowired
	private PermissaoModelAssembler permissaoModelAssembler;
	
	@Autowired
	private PagedResourcesAssembler<Permissao> pagedResourcesAssembler;
	
	@CheckSecurity.UsuariosGruposPermissoes.PodeConsultar
	@Override
	@GetMapping
	public PagedModel<PermissaoModel> listar(PermissaoFilter filtro, Pageable pageable) {
		
		Pageable pageableTraduzido = traduzirPageable(pageable);
		Page<Permissao> permissoesPage = null;
		
		if(filtro.getDescricao()!=null ) {
			permissoesPage = permissaoRepository.findAll(PermissaoSpecs.comDescricao(filtro), pageableTraduzido);
		}
		else
		permissoesPage = permissaoRepository.findAll(pageable);
		
		permissoesPage = new PageWrapper<>(permissoesPage, pageable);
		
		return pagedResourcesAssembler.toModel(permissoesPage, permissaoModelAssembler);
	}
	
	private Pageable traduzirPageable(Pageable apiPageable) {
		var mapeamento = Map.of(
				"id", "código",
				"nome", "nome",
				"descricao", "descrição"
			);
		
		return PageableTranslator.translate(apiPageable, mapeamento);
	}
	
}
