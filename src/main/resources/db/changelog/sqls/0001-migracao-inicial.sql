create table filme
(
  id uuid not null,
  duracao bigint,
  fim_exibicao date,
  inicio_exibicao date,
  nome character varying(100) not null,
  sinopse text,

  constraint filme_pkey primary key (id)
);
