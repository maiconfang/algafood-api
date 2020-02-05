package com.algaworks.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.ModeloNaoEncontradoException;
import com.algaworks.algafood.domain.model.Modelo;
import com.algaworks.algafood.domain.repository.ModeloRepository;

@Service
public class CadastroModeloService {

	private static final String MSG_MODELO_EM_USO 
		= "Modelo de código %d não pode ser removido, pois está em uso";
	
	@Autowired
	private ModeloRepository modeloRepository;
	
	@Transactional
	public Modelo salvar(Modelo modelo) {
		return modeloRepository.save(modelo);
	}
	
	@Transactional
	public void excluir(Long modeloId) {
		try {
			modeloRepository.deleteById(modeloId);
			modeloRepository.flush();
			
		} catch (EmptyResultDataAccessException e) {
			throw new ModeloNaoEncontradoException(modeloId);
		
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
				String.format(MSG_MODELO_EM_USO, modeloId));
		}
	}

	public Modelo buscarOuFalhar(Long modeloId) {
		return modeloRepository.findById(modeloId)
			.orElseThrow(() -> new ModeloNaoEncontradoException(modeloId));
	}
	
}
