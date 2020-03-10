package com.algaworks.algafood.infrastructure.repository.spec;

import java.util.ArrayList;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import com.algaworks.algafood.domain.filter.FuncionarioFilter;
import com.algaworks.algafood.domain.model.Funcionario;

public class FuncionarioSpecs {

	public static Specification<Funcionario> comNome(FuncionarioFilter filtro) {
		return (root, query, builder) -> {
			
			var predicates = new ArrayList<Predicate>();
			
			if (filtro.getNome() != null) {
				predicates.add(builder.like(root.get("nome"), "%" + filtro.getNome() + "%"));
			}
			
			return builder.and(predicates.toArray(new Predicate[0]));
		};
	}
	
}
