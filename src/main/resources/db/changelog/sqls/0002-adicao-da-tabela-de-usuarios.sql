create table usuario (
    id uuid not null,
    usuario character varying(100) not null,
    senha char(64) not null,

    constraint pk_usuario primary key (id)
);
