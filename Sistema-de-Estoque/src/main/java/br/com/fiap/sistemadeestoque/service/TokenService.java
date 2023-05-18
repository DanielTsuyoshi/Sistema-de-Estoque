package br.com.fiap.sistemadeestoque.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

import br.com.fiap.sistemadeestoque.models.Credencial;
import br.com.fiap.sistemadeestoque.models.Token;
import br.com.fiap.sistemadeestoque.models.Usuario;
import br.com.fiap.sistemadeestoque.repository.UsuarioRepository;

@Service
public class TokenService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public Token generateToken(Credencial credencial) {
        Algorithm alg = Algorithm.HMAC256("meusecret");
        var jwt = JWT.create()
                    .withSubject(credencial.email())
                    .withIssuer("NaoFalindo")
                    .withExpiresAt(Instant.now().plus(20, ChronoUnit.MINUTES))
                    .sign(alg)
                    ;
        return new Token(jwt, "JWT", "Bearer");
    }

    public Usuario validate(String token) {
        Algorithm alg = Algorithm.HMAC256("meusecret");
        var email = JWT.require(alg)
                .withIssuer("NaoFalindo")
                .build()
                .verify(token)
                .getSubject();
        
        return usuarioRepository.findByEmail(email).orElseThrow(
                () -> new JWTVerificationException("usuario nao encontrado"));

    }
    
}