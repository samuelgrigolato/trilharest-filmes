package com.opensanca.trilharest.filmes.filmes;

import com.opensanca.trilharest.filmes.comum.Pagina;
import com.opensanca.trilharest.filmes.comum.ParametrosDePaginacao;
import java.time.LocalDate;
import java.util.UUID;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class FilmesRepositoryJPA implements FilmesRepository {

  @Autowired
  private EntityManager entityManager;

  @Override
  public Pagina<Filme> buscarPaginaEmExibicao(ParametrosDePaginacao parametrosDePaginacao,
      LocalDate referencia) {

    CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();

    CriteriaQuery<Filme> criteriaSeletora = cb.createQuery(Filme.class);
    Root<Filme> raizSeletora = criteriaSeletora.from(Filme.class);
    criteriaSeletora.where(construirPredicadoEmExibicao(referencia, cb, raizSeletora));
    Query querySeletora = this.entityManager.createQuery(criteriaSeletora);
    querySeletora.setFirstResult((parametrosDePaginacao.getPagina() - 1) * parametrosDePaginacao.getTamanhoDaPagina());
    querySeletora.setMaxResults(parametrosDePaginacao.getTamanhoDaPagina());

    CriteriaQuery<Long> criteriaContadora = cb.createQuery(Long.class);
    Root<Filme> raizContadora = criteriaContadora.from(Filme.class);
    criteriaContadora.select(cb.count(cb.literal(1)));
    criteriaContadora.where(construirPredicadoEmExibicao(referencia, cb, raizContadora));
    Query queryContadora = this.entityManager.createQuery(criteriaContadora);

    Pagina<Filme> pagina = new Pagina<>();
    pagina.setTotalDeRegistros(((Number) queryContadora.getSingleResult()).intValue());
    pagina.setRegistros(querySeletora.getResultList());
    return pagina;
  }

  @Override
  public Filme buscarPorId(UUID id) {
    return this.entityManager.find(Filme.class, id);
  }

  private Predicate construirPredicadoEmExibicao(LocalDate referencia, CriteriaBuilder cb,
      Root<Filme> raiz) {
    return cb.between(cb.literal(referencia), raiz.get("inicioExibicao"), raiz.get("fimExibicao"));
  }
}
