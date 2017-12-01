package com.opensanca.trilharest.filmes.seguranca;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

public class BearerTokenAuthentication extends AbstractAuthenticationToken {

    private String jwt;

    public BearerTokenAuthentication(String jwt) {
        super(null);
        this.jwt = jwt;
    }

    public BearerTokenAuthentication(String jwt, List<GrantedAuthority> authorities) {
        super(authorities);
        this.jwt = jwt;
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return jwt;
    }

    @Override
    public Object getPrincipal() {
        return jwt;
    }
}
