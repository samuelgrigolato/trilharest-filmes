create table token_autenticacao (
    id uuid not null,
    usuario_id uuid not null,
    valido_ate timestamp with time zone,

    constraint pk_tokenautenticacao primary key (id),
    constraint fk_tokenautenticacao_usuario foreign key (usuario_id)
      references usuario (id)
);
