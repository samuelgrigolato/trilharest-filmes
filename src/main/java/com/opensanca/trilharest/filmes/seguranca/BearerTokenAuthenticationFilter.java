package com.opensanca.trilharest.filmes.seguranca;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class BearerTokenAuthenticationFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        if (request instanceof HttpServletRequest) {
            String authorization = ((HttpServletRequest) request).getHeader("Authorization");
            if (authorization != null && authorization.startsWith("Bearer ")) {
                String jwt = authorization.substring("Bearer ".length());
                SecurityContextHolder.getContext().setAuthentication(new BearerTokenAuthentication(jwt));
            }
        }

        chain.doFilter(request, response);

    }

}
