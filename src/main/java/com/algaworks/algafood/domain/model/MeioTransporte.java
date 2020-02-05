package com.algaworks.algafood.domain.model;

import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.algaworks.algafood.core.validation.Groups;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class MeioTransporte {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Column(nullable = false)
	private String nome;
	
	@NotBlank
	@Column(nullable = false)
	private String cor;
	
	@Column(nullable = false)
	private String ano;
	
	@Column(nullable = false)
	private String placa;
	
	@CreationTimestamp
	@Column(nullable = false, columnDefinition = "datetime")
	private OffsetDateTime dataCadastro;
	
	@UpdateTimestamp
	@Column(nullable = false, columnDefinition = "datetime")
	private OffsetDateTime dataAtualizacao;
	
	@Valid
	@ConvertGroup(from = Default.class, to = Groups.ModeloId.class)
	@NotNull
	@ManyToOne
	@JoinColumn(nullable = false)
	private Modelo modelo;
	
	@Valid
	@ConvertGroup(from = Default.class, to = Groups.RestauranteId.class)
	@NotNull
	@ManyToOne
	@JoinColumn(nullable = false)
	private Restaurante restaurante;
	
	@Valid
	@ConvertGroup(from = Default.class, to = Groups.FuncionarioId.class)
	@NotNull
	@ManyToOne
	@JoinColumn(nullable = false)
	private Funcionario funcionario;
	
}