package com.algaworks.algafood.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.model.Permissao;

@Repository
public interface PermissaoRepository extends CustomJpaRepository<Permissao, Long>, JpaSpecificationExecutor<Permissao> {
	
	List<Permissao> findAll();

}
