package com.algaworks.algafood.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.model.MeioTransporte;

@Repository
public interface MeioTransporteRepository extends CustomJpaRepository<MeioTransporte, Long>, JpaSpecificationExecutor<MeioTransporte> {
	
	List<MeioTransporte> findAll();

}