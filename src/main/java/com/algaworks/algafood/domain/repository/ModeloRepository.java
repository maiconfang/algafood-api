package com.algaworks.algafood.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.model.Modelo;

@Repository
public interface ModeloRepository extends CustomJpaRepository<Modelo, Long>, JpaSpecificationExecutor<Modelo> {
	
	
	List<Modelo> findAll();

}
