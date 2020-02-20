package com.algaworks.algafood.infrastructure.repository.spec;

import java.util.ArrayList;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import com.algaworks.algafood.domain.filter.UsuarioFilter;
import com.algaworks.algafood.domain.model.Usuario;

public class UsuarioSpecs {

	public static Specification<Usuario> comNomeOrEmailOrAnd(UsuarioFilter filtro) {
		return (root, query, builder) -> {
			
			var predicates = new ArrayList<Predicate>();
			
			if (filtro.getNome() != null) {
				predicates.add(builder.like(root.get("nome"), "%" + filtro.getNome() + "%"));
			}
			
			if (filtro.getEmail() != null) {
				predicates.add(builder.like(root.get("email"), "%" + filtro.getEmail() + "%"));
			}
			
			return builder.and(predicates.toArray(new Predicate[0]));
		};
	}
	
}
