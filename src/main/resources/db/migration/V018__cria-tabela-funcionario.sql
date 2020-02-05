create table funcionario (
	id bigint not null auto_increment,
	nome varchar(200) not null,
    rg varchar(50) not null,
    cpf varchar(50) not null,
	data_atualizacao datetime not null,
	data_cadastro datetime not null,
	
	endereco_cidade_id bigint,
	endereco_cep varchar(9),
	endereco_logradouro varchar(100),
	endereco_numero varchar(20),
	endereco_complemento varchar(60),
	endereco_bairro varchar(60),
	
	primary key (id)
) engine=InnoDB default charset=utf8;

alter table funcionario add constraint fk_funcionario_cidade
foreign key (endereco_cidade_id) references cidade (id);

alter table meio_transporte add constraint fk_meio_transporte_funcionario
foreign key (funcionario_id) references funcionario (id);