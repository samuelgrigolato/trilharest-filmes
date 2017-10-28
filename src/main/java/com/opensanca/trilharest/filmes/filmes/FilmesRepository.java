package com.opensanca.trilharest.filmes.filmes;

import java.time.LocalDate;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface FilmesRepository extends CrudRepository<Filme, UUID> {

  @Query("select new com.opensanca.trilharest.filmes.filmes.FilmeResumidoDTO( " +
         "  f.id, f.nome, f.duracao) " +
         "from Filme f " +
         "where ?1 between f.inicioExibicao and f.fimExibicao")
  Page<FilmeResumidoDTO> buscarPaginaEmExibicao(LocalDate referencia, Pageable pageable);

  @Query("select new com.opensanca.trilharest.filmes.filmes.FilmeResumidoDTO( " +
         "  f.id, f.nome, f.duracao) " +
         "from Filme f ")
  Page<FilmeResumidoDTO> buscarPagina(Pageable pageable);

  Filme findByNome(String nome);

}
