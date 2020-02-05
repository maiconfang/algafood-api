package com.algaworks.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.FuncionarioNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Funcionario;
import com.algaworks.algafood.domain.repository.FuncionarioRepository;

@Service
public class CadastroFuncionarioService {

	private static final String MSG_FUNCIONARIO_EM_USO 
		= "Funcionário do código %d não pode ser removido, pois está em uso";

	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	@Autowired
	private CadastroCidadeService cadastroCidade;
	
	@Transactional
	public Funcionario salvar(Funcionario funcionario) {
		Long cidadeId = funcionario.getEndereco().getCidade().getId();

		Cidade cidade = cadastroCidade.buscarOuFalhar(cidadeId);
		
		funcionario.getEndereco().setCidade(cidade);
		
		return funcionarioRepository.save(funcionario);
	}
	
	@Transactional
	public void excluir(Long funcionarioId) {
		try {
			funcionarioRepository.deleteById(funcionarioId);
			funcionarioRepository.flush();
			
		} catch (EmptyResultDataAccessException e) {
			throw new FuncionarioNaoEncontradaException(funcionarioId);
		
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
				String.format(MSG_FUNCIONARIO_EM_USO, funcionarioId));
		}
	}
	
	public Funcionario buscarOuFalhar(Long funcionarioId) {
		return funcionarioRepository.findById(funcionarioId)
			.orElseThrow(() -> new FuncionarioNaoEncontradaException(funcionarioId));
	}
	
}
