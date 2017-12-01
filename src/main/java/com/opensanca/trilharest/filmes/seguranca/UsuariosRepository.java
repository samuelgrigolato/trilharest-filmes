package com.opensanca.trilharest.filmes.seguranca;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface UsuariosRepository extends CrudRepository<Usuario, UUID> {

    Optional<Usuario> findByUsuario(String usuario);

}
