package com.algaworks.algafood.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.model.Estado;

@Repository
public interface EstadoRepository  extends CustomJpaRepository<Estado, Long>, JpaSpecificationExecutor<Estado> {
	
	List<Estado> findAll();

}
