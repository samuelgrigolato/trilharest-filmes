package com.opensanca.trilharest.filmes.filmes;

import java.time.LocalDate;
import org.apache.tomcat.jni.Local;
import org.junit.Assert;
import org.junit.Test;

public class FilmeTest {

  @Test
  public void foraDeExibicaoSeDatasNulas() {
    // preparação
    Filme filme = new Filme(null, null, null, null,
        null, null);
    LocalDate referencia = LocalDate.of(2017, 10, 22);
    // processamento
    boolean emExibicao = filme.emExibicao(referencia);
    // verificação
    Assert.assertFalse(emExibicao);
  }

  @Test
  public void emExibicaoSeDentroDeIntervaloDeDatas() {
    // preparação
    Filme filme = new Filme(null, null, null, null,
        LocalDate.of(2017, 10, 1),
        LocalDate.of(2017, 10, 30));
    LocalDate referencia = LocalDate.of(2017, 10, 22);
    // processamento
    boolean emExibicao = filme.emExibicao(referencia);
    // verificação
    Assert.assertTrue(emExibicao);
  }

  @Test
  public void foraDeExibicaoSeForaDoIntervaloDeDatas() {
    // preparação
    Filme filme = new Filme(null, null, null, null,
        LocalDate.of(2016, 10, 1),
        LocalDate.of(2016, 10, 30));
    LocalDate referencia = LocalDate.of(2017, 10, 22);
    // processamento
    boolean emExibicao = filme.emExibicao(referencia);
    // verificação
    Assert.assertFalse(emExibicao);
  }

  @Test
  public void emExibicaoSeInicioExatamenteHoje() {
    // preparação
    Filme filme = new Filme(null, null, null, null,
        LocalDate.of(2017, 10, 22),
        LocalDate.of(2017, 10, 30));
    LocalDate referencia = LocalDate.of(2017, 10, 22);
    // processamento
    boolean emExibicao = filme.emExibicao(referencia);
    // verificação
    Assert.assertTrue(emExibicao);
  }

  @Test
  public void emExibicaoSeTerminoExatamenteHoje() {
    // preparação
    Filme filme = new Filme(null, null, null, null,
        LocalDate.of(2017, 10, 20),
        LocalDate.of(2017, 10, 22));
    LocalDate referencia = LocalDate.of(2017, 10, 22);
    // processamento
    boolean emExibicao = filme.emExibicao(referencia);
    // verificação
    Assert.assertTrue(emExibicao);
  }

  @Test
  public void foraDeExibicaoSeTerminoNulo() {
    // preparação
    Filme filme = new Filme(null, null, null, null,
        LocalDate.of(2017, 10, 20),
        null);
    LocalDate referencia = LocalDate.of(2017, 10, 22);
    // processamento
    boolean emExibicao = filme.emExibicao(referencia);
    // verificação
    Assert.assertFalse(emExibicao);
  }

}
