package com.opensanca.trilharest.filmes.filmes;

import com.opensanca.trilharest.filmes.comum.Pagina;
import com.opensanca.trilharest.filmes.comum.ParametrosDePaginacao;

import java.time.LocalDate;
import java.util.UUID;

public interface FilmesRepository {

    Pagina<Filme> buscarPaginaEmExibicao(
        ParametrosDePaginacao parametrosDePaginacao,
        LocalDate referencia);

    /**
     * @throws IllegalArgumentException se não encontrar pelo id
     */
    Filme buscarPorId(UUID id);

}
