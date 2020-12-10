package com.yeshua.forum.config.security;

import java.util.Date;

import com.yeshua.forum.model.Usuario;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

    @Value("${forum.jwt.expiration}")
    private String expiration;
    @Value("${forum.jwt.secret}")
    private String secret;

    public String gerarToken(Authentication authenticate) {
        Usuario logado = (Usuario) authenticate.getPrincipal();
        Date hoje = new Date();
        Date dataExpiracao = new Date(hoje.getTime() + Long.valueOf(expiration));
        return Jwts.builder().setIssuer("API do Fórum da Alura").setSubject(logado.getId().toString()).setIssuedAt(hoje)
                .setExpiration(dataExpiracao).signWith(SignatureAlgorithm.HS256, secret).compact();
    }

    public boolean isTokenValido(String token) {
        try {
            Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
