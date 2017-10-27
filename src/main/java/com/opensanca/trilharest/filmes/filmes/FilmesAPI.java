package com.opensanca.trilharest.filmes.filmes;


import com.opensanca.trilharest.filmes.comum.EntidadeNaoEncontradaException;
import io.swagger.annotations.ApiOperation;
import java.time.LocalDate;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;

@RestController
@RequestMapping("/filmes")
@Api("API de filmes em cartaz")
public class FilmesAPI {

  @Autowired
  private FilmesRepository filmesRepository;

  @GetMapping("/em-exibicao")
  @ApiOperation(value="Buscar página de filmes em exibição",
                notes="Permite a busca paginada de filmes em exibição, " +
                      "ou seja, filmes que possuam data de início e término " +
                      "de exibição e cujo período engloba a data atual")
  public Page<Filme> get(Pageable parametrosDePaginacao) {
    if (parametrosDePaginacao == null) {
      parametrosDePaginacao = new PageRequest(1, 3);
    }
    LocalDate hoje = LocalDate.now();
    return filmesRepository.buscarPaginaEmExibicao(hoje, parametrosDePaginacao);
  }

  @GetMapping("/{id}")
  public Filme getPorId(@PathVariable UUID id) {
    Filme filme = filmesRepository.findOne(id);
    if (filme == null) {
      throw new EntidadeNaoEncontradaException();
    }
    return filme;
  }

}
