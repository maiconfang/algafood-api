package com.algaworks.algafood.infrastructure.repository.spec;

import java.util.ArrayList;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import com.algaworks.algafood.domain.filter.GrupoFilter;
import com.algaworks.algafood.domain.model.Grupo;

public class GrupoSpecs {

	public static Specification<Grupo> comNome(GrupoFilter filtro) {
		return (root, query, builder) -> {
			
			var predicates = new ArrayList<Predicate>();
			
			if (filtro.getNome() != null) {
				predicates.add(builder.like(root.get("nome"), "%" + filtro.getNome() + "%"));
			}
			
			return builder.and(predicates.toArray(new Predicate[0]));
		};
	}
	
}
