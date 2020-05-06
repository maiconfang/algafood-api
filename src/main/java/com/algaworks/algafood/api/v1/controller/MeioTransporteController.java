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
import com.algaworks.algafood.api.v1.assembler.MeioTransporteInputDisassembler;
import com.algaworks.algafood.api.v1.assembler.MeioTransporteModelAssembler;
import com.algaworks.algafood.api.v1.model.MeioTransporteModel;
import com.algaworks.algafood.api.v1.model.input.MeioTransporteInput;
import com.algaworks.algafood.api.v1.openapi.controller.MeioTransporteControllerOpenApi;
import com.algaworks.algafood.core.data.PageWrapper;
import com.algaworks.algafood.core.data.PageableTranslator;
import com.algaworks.algafood.core.security.CheckSecurity;
import com.algaworks.algafood.domain.exception.FuncionarioNaoEncontradoException;
import com.algaworks.algafood.domain.exception.ModeloNaoEncontradoException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.exception.RestauranteNaoEncontradoException;
import com.algaworks.algafood.domain.filter.MeioTransporteFilter;
import com.algaworks.algafood.domain.model.MeioTransporte;
import com.algaworks.algafood.domain.repository.MeioTransporteRepository;
import com.algaworks.algafood.domain.service.CadastroMeioTransporteService;
import com.algaworks.algafood.infrastructure.repository.spec.MeioTransporteSpecs;

@RestController
@RequestMapping(path = "/v1/meios-transporte", produces = MediaType.APPLICATION_JSON_VALUE)
public class MeioTransporteController implements MeioTransporteControllerOpenApi {

	@Autowired
	private MeioTransporteRepository meioTransporteRepository;
	
	@Autowired
	private CadastroMeioTransporteService cadastroMeioTransporte;
	
	@Autowired
	private MeioTransporteModelAssembler meioTransporteModelAssembler;
	
	@Autowired
	private MeioTransporteInputDisassembler meioTransporteInputDisassembler;
	
	@Autowired
	private PagedResourcesAssembler<MeioTransporte> pagedResourcesAssembler;
	
	@CheckSecurity.MeiosTransporte.PodeConsultar
	@Override
	@GetMapping
	public PagedModel<MeioTransporteModel> listar(MeioTransporteFilter filtro, Pageable pageable) {
		
		Pageable pageableTraduzido = traduzirPageable(pageable);
		Page<MeioTransporte> meioTransportesPage = null;
		
		if(filtro.getNome()!=null ) {
			meioTransportesPage = meioTransporteRepository.findAll(MeioTransporteSpecs.comNome(filtro), pageableTraduzido);
		}
		else
			meioTransportesPage = meioTransporteRepository.findAll(pageable);
		
		meioTransportesPage = new PageWrapper<>(meioTransportesPage, pageable);
		
		return pagedResourcesAssembler.toModel(meioTransportesPage, meioTransporteModelAssembler);
	}
	
	@CheckSecurity.MeiosTransporte.PodeConsultar
	@Override
	@GetMapping("/{meioTransporteId}")
	public MeioTransporteModel buscar(@PathVariable Long meioTransporteId) {
		MeioTransporte meioTransporte = cadastroMeioTransporte.buscarOuFalhar(meioTransporteId);
		
		return meioTransporteModelAssembler.toModel(meioTransporte);
	}
	
	@CheckSecurity.MeiosTransporte.PodeConsultar
	@Override
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public MeioTransporteModel adicionar(@RequestBody @Valid MeioTransporteInput meioTransporteInput) {
		try {
			MeioTransporte meioTransporte = meioTransporteInputDisassembler.toDomainObject(meioTransporteInput);
			
			meioTransporte = cadastroMeioTransporte.salvar(meioTransporte);
			
			MeioTransporteModel meioTransporteModel = meioTransporteModelAssembler.toModel(meioTransporte);
			
			ResourceUriHelper.addUriInResponseHeader(meioTransporteModel.getId());
			
			return meioTransporteModel;
		} catch (ModeloNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
		 catch (RestauranteNaoEncontradoException e) {
				throw new NegocioException(e.getMessage(), e);
		}
		catch (FuncionarioNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}
	
	@CheckSecurity.MeiosTransporte.PodeEditar
	@Override
	@PutMapping("/{meioTransporteId}")
	public MeioTransporteModel atualizar(@PathVariable Long meioTransporteId,
			@RequestBody @Valid MeioTransporteInput meioTransporteInput) {
		try {
			MeioTransporte meioTransporteAtual = cadastroMeioTransporte.buscarOuFalhar(meioTransporteId);
			
			meioTransporteInputDisassembler.copyToDomainObject(meioTransporteInput, meioTransporteAtual);
			
			meioTransporteAtual = cadastroMeioTransporte.salvar(meioTransporteAtual);
			
			return meioTransporteModelAssembler.toModel(meioTransporteAtual);
		} catch (ModeloNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
		 catch (RestauranteNaoEncontradoException e) {
				throw new NegocioException(e.getMessage(), e);
		}
		catch (FuncionarioNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}
	
	@CheckSecurity.MeiosTransporte.PodeEditar
	@Override
	@DeleteMapping("/{meioTransporteId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long meioTransporteId) {
		cadastroMeioTransporte.excluir(meioTransporteId);	
	}
	
	private Pageable traduzirPageable(Pageable apiPageable) {
		var mapeamento = Map.of(
				"id", "código",
				"nome", "nome"
			);
		
		return PageableTranslator.translate(apiPageable, mapeamento);
	}
	
}
