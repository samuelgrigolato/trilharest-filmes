package com.opensanca.trilharest.filmes.themoviedb;

import com.opensanca.trilharest.filmes.filmes.Filme;
import com.opensanca.trilharest.filmes.filmes.FilmesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnNotWebApplication;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Component
@ConditionalOnNotWebApplication
public class ImportadorTheMovieDB {

    private static final Logger LOGGER = LoggerFactory.getLogger(ImportadorTheMovieDB.class);

    @Value("${themoviedb.api_key}")
    private String apiKey;

    @Autowired
    private FilmesRepository filmesRepository;

    @Scheduled(fixedDelay = 15 * 1000)
    public void executar() {
        LOGGER.info("Executando importação de filmes do TheMovieDB...");

        RestTemplate api = new RestTemplate();

        String url = "https://api.themoviedb.org/3/movie/upcoming?language=pt-BR&page=1&api_key=" + apiKey;

        FilmesTheMovieDB filmes = api.getForObject(url, FilmesTheMovieDB.class);

        List<Filme> entidades = filmes.getResults().stream()
            .map(x -> {
                Filme entidade = new Filme();
                entidade.setId(UUID.randomUUID());
                entidade.setNome(x.getTitle());
                entidade.setSinopse(x.getOverview());
                return entidade;
            })
            .collect(Collectors.toList());

        filmesRepository.save(entidades);
    }

}
