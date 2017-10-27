package com.opensanca.trilharest.filmes.filmes;

import java.time.Duration;
import java.time.LocalDate;
import java.util.UUID;

public class FilmeDetalhadoDTO {

  private UUID id;
  private String nome;
  private String sinopse;
  private Duration duracao;
  private LocalDate inicioExibicao;
  private LocalDate fimExibicao;

  public FilmeDetalhadoDTO(Filme entidade) {
    this.id = entidade.getId();
    this.nome = entidade.getNome();
    this.sinopse = entidade.getSinopse();
    this.duracao = entidade.getDuracao();
    this.inicioExibicao = entidade.getInicioExibicao();
    this.fimExibicao = entidade.getFimExibicao();
  }

  public UUID getId() {
    return id;
  }

  public String getNome() {
    return nome;
  }

  public String getSinopse() {
    return sinopse;
  }

  public Duration getDuracao() {
    return duracao;
  }

  public LocalDate getInicioExibicao() {
    return inicioExibicao;
  }

  public LocalDate getFimExibicao() {
    return fimExibicao;
  }
}
