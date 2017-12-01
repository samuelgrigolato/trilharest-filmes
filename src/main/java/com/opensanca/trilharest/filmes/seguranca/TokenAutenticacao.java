package com.opensanca.trilharest.filmes.seguranca;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class TokenAutenticacao {

    @Id
    private UUID id;

    @ManyToOne
    private Usuario usuario;

    private LocalDateTime validoAte;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public LocalDateTime getValidoAte() {
        return validoAte;
    }

    public void setValidoAte(LocalDateTime validoAte) {
        this.validoAte = validoAte;
    }
}
