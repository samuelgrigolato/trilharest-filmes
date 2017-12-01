package com.opensanca.trilharest.filmes.seguranca;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private TokensAutenticacaoRepository tokensAutenticacaoRepository;

    @Value("${autenticacao.jwtSecret}")
    private String jwtSecret;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        BearerTokenAuthentication bearerToken = (BearerTokenAuthentication) authentication;
        String jwt = (String)bearerToken.getCredentials();

        try {

            String usuario = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(jwt)
                .getBody()
                .getSubject();
            System.out.println(usuario + " autenticado com sucesso!");

            return new BearerTokenAuthentication(jwt,
                    Arrays.asList(new SimpleGrantedAuthority("ADMIN")));
        } catch (SignatureException ex) {
            throw new BadCredentialsException("JWT inválido");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(BearerTokenAuthentication.class);
    }

}
