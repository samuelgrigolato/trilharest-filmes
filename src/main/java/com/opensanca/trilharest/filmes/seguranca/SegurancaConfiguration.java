package com.opensanca.trilharest.filmes.seguranca;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.sql.DataSource;


@Configuration
public class SegurancaConfiguration extends WebSecurityConfigurerAdapter {

    @Value("${autenticacao.secret}")
    private String secret;

    @Autowired
    private DataSource dataSource;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new StandardPasswordEncoder(secret);
    }

    @Autowired
    private CustomAuthenticationProvider customAuthenticationProvider;

    @Autowired
    private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // em APIs de dados não usamos CSRF
        http.csrf().disable();

        http.exceptionHandling()
                .authenticationEntryPoint(customAuthenticationEntryPoint);

        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        /*http.formLogin()
                .failureHandler(new SimpleUrlAuthenticationFailureHandler())
                .successHandler(new CustomAuthenticationSuccessHandler());*/

        /*http.httpBasic()
                .authenticationEntryPoint(customAuthenticationEntryPoint);*/

        http.addFilterBefore(new BearerTokenAuthenticationFilter(), BasicAuthenticationFilter.class);

        http.authorizeRequests()
                .antMatchers(HttpMethod.POST, "/login").permitAll()
                .antMatchers(HttpMethod.POST, "/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.PUT, "/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.GET, "/**/*").permitAll()
                .anyRequest().denyAll();

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .authenticationProvider(customAuthenticationProvider);
    }
}
