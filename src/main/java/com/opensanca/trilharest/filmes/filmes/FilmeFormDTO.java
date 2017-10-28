package com.opensanca.trilharest.filmes.filmes;

import java.time.Duration;
import java.time.LocalDate;
import java.util.UUID;
import org.hibernate.validator.constraints.NotBlank;

@FimExibicaoAposInicio
public class FilmeFormDTO {

  @NotBlank
  private String nome;
  private String sinopse;
  private Duration duracao;
  private LocalDate inicioExibicao;
  private LocalDate fimExibicao;

  public void preencher(Filme entidade) {
    entidade.setNome(this.nome);
    entidade.setSinopse(this.sinopse);
    entidade.setDuracao(this.duracao);
    entidade.setInicioExibicao(this.inicioExibicao);
    entidade.setFimExibicao(this.fimExibicao);
  }

  public Filme construir() {
    Filme entidade = new Filme();
    entidade.setId(UUID.randomUUID());
    preencher(entidade);
    return entidade;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getSinopse() {
    return sinopse;
  }

  public void setSinopse(String sinopse) {
    this.sinopse = sinopse;
  }

  public Duration getDuracao() {
    return duracao;
  }

  public void setDuracao(Duration duracao) {
    this.duracao = duracao;
  }

  public LocalDate getInicioExibicao() {
    return inicioExibicao;
  }

  public void setInicioExibicao(LocalDate inicioExibicao) {
    this.inicioExibicao = inicioExibicao;
  }

  public LocalDate getFimExibicao() {
    return fimExibicao;
  }

  public void setFimExibicao(LocalDate fimExibicao) {
    this.fimExibicao = fimExibicao;
  }
}
