package com.opensanca.trilharest.filmes.filmes;


import com.opensanca.trilharest.filmes.comum.Pagina;
import com.opensanca.trilharest.filmes.comum.ParametrosDePaginacao;
import io.swagger.annotations.ApiOperation;
import java.time.LocalDate;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
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
  public Pagina<Filme> get(ParametrosDePaginacao parametrosDePaginacao) {
    if (parametrosDePaginacao == null) {
      parametrosDePaginacao = new ParametrosDePaginacao();
    }
    LocalDate hoje = LocalDate.now();
    return filmesRepository.buscarPaginaEmExibicao(parametrosDePaginacao, hoje);
  }

  @GetMapping("/{id}")
  public Filme getPorId(@PathVariable UUID id) {
    return filmesRepository.buscarPorId(id);
  }

}
