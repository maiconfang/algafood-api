alter table meio_transporte add column restaurante_id bigint not null after modelo_id;

alter table meio_transporte add constraint fk_meio_transporte_restaurante
foreign key (restaurante_id) references restaurante (id);