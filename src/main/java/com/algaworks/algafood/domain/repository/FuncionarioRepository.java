package com.algaworks.algafood.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.model.Funcionario;

@Repository
public interface FuncionarioRepository extends CustomJpaRepository<Funcionario, Long>, JpaSpecificationExecutor<Funcionario> {
	
	List<Funcionario> findAll();

}
