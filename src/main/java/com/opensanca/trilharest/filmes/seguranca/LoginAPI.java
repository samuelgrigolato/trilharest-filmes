package com.opensanca.trilharest.filmes.seguranca;

import com.opensanca.trilharest.filmes.comum.BindingException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/login")
public class LoginAPI {

    @Autowired
    private UsuariosRepository usuariosRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokensAutenticacaoRepository tokensAutenticacaoRepository;

    @Value("${autenticacao.secret}")
    private String secret;

    @Value("${autenticacao.jwtSecret}")
    private String jwtSecret;

    @PostMapping
    public String login(@Valid @RequestBody CredenciaisDTO credenciaisDTO, BindingResult results) {
        if (results.hasErrors()) {
            throw new BindingException(results);
        }
        Usuario usuario = usuariosRepository.findByUsuario(credenciaisDTO.getUsuario())
                .orElseThrow(CredencialInvalidaException::new);
        if (!passwordEncoder.matches(credenciaisDTO.getSenha(), usuario.getSenha())) {
            throw new CredencialInvalidaException();
        }

        /*UUID tokenId = UUID.randomUUID();
        TokenAutenticacao token = new TokenAutenticacao();
        token.setId(tokenId);
        token.setUsuario(usuario);
        token.setValidoAte(LocalDateTime.now().plusMinutes(30));
        tokensAutenticacaoRepository.save(token);*/

        return Jwts.builder()
                .setSubject(usuario.getUsuario())
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

}
