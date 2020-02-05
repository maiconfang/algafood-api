create table meio_transporte (
	id bigint not null auto_increment,
	nome varchar(200) not null,
	cor varchar(100) not null,
    ano varchar(100),
    placa varchar(50),
    modelo_id bigint not null,
    funcionario_id bigint not null,
       
	data_atualizacao datetime not null,
	data_cadastro datetime not null,
		
	primary key (id)
) engine=InnoDB default charset=utf8;

alter table meio_transporte add constraint fk_meio_transporte_modelo
foreign key (modelo_id) references modelo (id);