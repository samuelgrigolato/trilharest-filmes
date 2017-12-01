package com.opensanca.trilharest.filmes.seguranca;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface TokensAutenticacaoRepository extends CrudRepository<TokenAutenticacao, UUID> {
}
