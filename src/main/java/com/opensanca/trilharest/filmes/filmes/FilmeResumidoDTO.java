package com.opensanca.trilharest.filmes.filmes;

import java.time.Duration;
import java.util.UUID;

public class FilmeResumidoDTO {

  private UUID id;
  private String nome;
  private Duration duracao;

  public FilmeResumidoDTO(UUID id, String nome, Duration duracao) {
    this.id = id;
    this.nome = nome;
    this.duracao = duracao;
  }

  public UUID getId() {
    return id;
  }

  public String getNome() {
    return nome;
  }

  public Duration getDuracao() {
    return duracao;
  }
}
