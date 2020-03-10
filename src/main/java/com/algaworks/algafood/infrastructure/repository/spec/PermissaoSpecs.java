package com.algaworks.algafood.infrastructure.repository.spec;

import java.util.ArrayList;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import com.algaworks.algafood.domain.filter.PermissaoFilter;
import com.algaworks.algafood.domain.model.Permissao;

public class PermissaoSpecs {

	public static Specification<Permissao> comDescricao(PermissaoFilter filtro) {
		return (root, query, builder) -> {
			
			var predicates = new ArrayList<Predicate>();
			
			if (filtro.getDescricao() != null) {
				predicates.add(builder.like(root.get("descricao"), "%" + filtro.getDescricao() + "%"));
			}
			
			return builder.and(predicates.toArray(new Predicate[0]));
		};
	}
	
}
