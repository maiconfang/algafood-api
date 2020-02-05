package com.algaworks.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.MeioTransporteNaoEncontradaException;
import com.algaworks.algafood.domain.model.MeioTransporte;
import com.algaworks.algafood.domain.model.Modelo;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.MeioTransporteRepository;

@Service
public class CadastroMeioTransporteService {

	private static final String MSG_MEIO_TRANSPORTE_EM_USO 
		= "Meio de transporte do código %d não pode ser removido, pois está em uso";

	@Autowired
	private MeioTransporteRepository meioTransporteRepository;
	
	@Autowired
	private CadastroModeloService cadastroModelo;
	
	@Autowired
	private CadastroRestauranteService cadastroRestaurante;
	
	@Transactional
	public MeioTransporte salvar(MeioTransporte meioTransporte) {
		Long modeloId = meioTransporte.getModelo().getId();
		Long restauranteId = meioTransporte.getRestaurante().getId();

		Modelo modelo = cadastroModelo.buscarOuFalhar(modeloId);
		Restaurante restaurante = cadastroRestaurante.buscarOuFalhar(restauranteId);
		
		meioTransporte.setModelo(modelo);
		meioTransporte.setRestaurante(restaurante);
		
		return meioTransporteRepository.save(meioTransporte);
	}
	
	@Transactional
	public void excluir(Long meioTransporteId) {
		try {
			meioTransporteRepository.deleteById(meioTransporteId);
			meioTransporteRepository.flush();
			
		} catch (EmptyResultDataAccessException e) {
			throw new MeioTransporteNaoEncontradaException(meioTransporteId);
		
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
				String.format(MSG_MEIO_TRANSPORTE_EM_USO, meioTransporteId));
		}
	}
	
	public MeioTransporte buscarOuFalhar(Long meioTransporteId) {
		return meioTransporteRepository.findById(meioTransporteId)
			.orElseThrow(() -> new MeioTransporteNaoEncontradaException(meioTransporteId));
	}
	
}
