package com.opensanca.trilharest.filmes.filmes;


import com.opensanca.trilharest.filmes.comum.BindingException;
import com.opensanca.trilharest.filmes.comum.EntidadeNaoEncontradaException;
import io.swagger.annotations.ApiOperation;
import java.time.LocalDate;
import java.util.UUID;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;

@RestController
@RequestMapping("/filmes")
@Api("API de filmes em cartaz")
public class FilmesAPI {

  @Autowired
  private FilmesRepository filmesRepository;

  @Autowired
  private MessageSource messageSource;

  @GetMapping("/em-exibicao")
  @ApiOperation(value="Buscar página de filmes em exibição",
                notes="Permite a busca paginada de filmes em exibição, " +
                      "ou seja, filmes que possuam data de início e término " +
                      "de exibição e cujo período engloba a data atual")
  public Page<FilmeResumidoDTO> get(Pageable parametrosDePaginacao) {
    if (parametrosDePaginacao == null) {
      parametrosDePaginacao = new PageRequest(1, 3);
    }
    LocalDate hoje = LocalDate.now();
    return filmesRepository.buscarPaginaEmExibicao(hoje, parametrosDePaginacao);
  }

  @GetMapping("/{id}")
  public FilmeDetalhadoDTO getPorId(@PathVariable UUID id) {
    Filme filme = filmesRepository.findOne(id);
    if (filme == null) {
      throw new EntidadeNaoEncontradaException();
    }
    return new FilmeDetalhadoDTO(filme);
  }

  @GetMapping
  public Page<FilmeResumidoDTO> getTodos(Pageable parametrosDePaginacao) {

    if (System.currentTimeMillis() % 50 == 0) {
      try {
        Thread.sleep(3000);
      } catch (InterruptedException e) {
      }
    }

    if (parametrosDePaginacao == null) {
      parametrosDePaginacao = new PageRequest(1, 3);
    }
    return filmesRepository.buscarPagina(parametrosDePaginacao);
  }

  @PostMapping
  public UUID cadastrar(@Valid @RequestBody FilmeFormDTO dados, BindingResult results, @AuthenticationPrincipal UUID token) {

    System.out.println("Cadastro solicitado pelo token " + token);

    validarNomeDuplicado(dados, null, results);
    if (results.hasErrors()) {
      throw new BindingException(results);
    }
    Filme entidade = dados.construir();
    filmesRepository.save(entidade);
    return entidade.getId();
  }

  @PutMapping("/{id}")
  public void alterar(@PathVariable UUID id, @Valid @RequestBody FilmeFormDTO dados, BindingResult results) {
    validarNomeDuplicado(dados, id, results);
    if (results.hasErrors()) {
      throw new BindingException(results);
    }
    Filme entidade = filmesRepository.findOne(id);
    dados.preencher(entidade);
    filmesRepository.save(entidade);
  }

  @DeleteMapping("/{id}")
  public void excluir(@PathVariable UUID id) {
    filmesRepository.delete(id);
  }

  private void validarNomeDuplicado(FilmeFormDTO dados, UUID id, BindingResult results) {
    if (dados != null && dados.getNome() != null) {
      Filme existente = this.filmesRepository.findByNome(dados.getNome());
      if (existente != null && !existente.getId().equals(id)) {
        results.reject("filme.duplicado",
            messageSource.getMessage("filme.duplicado", null, null));
      }
    }
  }

}
