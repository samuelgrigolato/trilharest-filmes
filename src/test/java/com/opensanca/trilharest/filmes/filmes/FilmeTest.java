package com.opensanca.trilharest.filmes.filmes;

import java.time.LocalDate;
import org.junit.Assert;
import org.junit.Test;

public class FilmeTest {

    @Test
    public void foraDeExibicaoSeDatasNulas() {
        // preparação
        Filme filme = new Filme(null, null, null, null, null, null);
        LocalDate referencia = LocalDate.of(2017, 10, 21);
        // processamento
        boolean resultado = filme.emExibicao(referencia);
        // verificação
        Assert.assertFalse(resultado);
    }

    @Test
    public void emExibicaoSeDentroDeIntervaloDeDatas() {
        // preparação
        Filme filme = new Filme(null, null, null,
            null,
            LocalDate.of(2017, 10, 1),
            LocalDate.of(2017, 10, 30));
        LocalDate referencia = LocalDate.of(2017, 10, 21);
        // processamento
        boolean resultado = filme.emExibicao(referencia);
        // verificação
        Assert.assertTrue(resultado);
    }

    @Test
    public void foraDeExibicaoSeForaDoIntervaloDeDatas() {
        // preparação
        Filme filme = new Filme(null, null, null,
            null,
            LocalDate.of(2016, 10, 1),
            LocalDate.of(2016, 10, 30));
        LocalDate referencia = LocalDate.of(2017, 10, 21);
        // processamento
        boolean resultado = filme.emExibicao(referencia);
        // verificação
        Assert.assertFalse(resultado);
    }

    @Test
    public void foraDeExibicaoSeInicioExibicaoNulo() {
        // preparação
        Filme filme = new Filme(null, null, null,
            null,
            null,
            LocalDate.of(2016, 10, 30));
        LocalDate referencia = LocalDate.of(2017, 10, 21);
        // processamento
        boolean resultado = filme.emExibicao(referencia);
        // verificação
        Assert.assertFalse(resultado);
    }

    @Test
    public void foraDeExibicaoSeFimExibicaoNulo() {
        // preparação
        Filme filme = new Filme(null, null, null,
            null,
            LocalDate.of(2016, 10, 30),
            null);
        LocalDate referencia = LocalDate.of(2017, 10, 21);
        // processamento
        boolean resultado = filme.emExibicao(referencia);
        // verificação
        Assert.assertFalse(resultado);
    }

    @Test
    public void emExibicaoSeInicioExatamenteHoje() {
        // preparação
        Filme filme = new Filme(null, null, null,
            null,
            LocalDate.of(2017, 10, 21),
            LocalDate.of(2017, 10, 22));
        LocalDate referencia = LocalDate.of(2017, 10, 21);
        // processamento
        boolean resultado = filme.emExibicao(referencia);
        // verificação
        Assert.assertTrue(resultado);
    }

    @Test
    public void emExibicaoSeFimExatamenteHoje() {
        // preparação
        Filme filme = new Filme(null, null, null,
            null,
            LocalDate.of(2017, 10, 20),
            LocalDate.of(2017, 10, 21));
        LocalDate referencia = LocalDate.of(2017, 10, 21);
        // processamento
        boolean resultado = filme.emExibicao(referencia);
        // verificação
        Assert.assertTrue(resultado);
    }

}
