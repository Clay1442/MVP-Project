package com.mvpproject.mvp_project.services;

import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.mvpproject.mvp_project.entities.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

  @Value("${api.security.token.secret}")
    private String secret;

  public String generateToken(User user) {
      try {
          Algorithm algorithm = Algorithm.HMAC256(secret);
          String token = JWT.create()
                  .withIssuer("MVP API")
                  .withSubject(user.getLogin())
                  .withExpiresAt(generatedExpirationDate())
                  .sign(algorithm);

          return token;
      }
      catch(JWTCreationException exception){
          throw new JWTCreationException("Erro ao gerar o token JWT", exception);
      }
  }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return  token = JWT.require(algorithm)
                    .withIssuer("MVP API")
                    .build()
                    .verify(token)
                    .getSubject();
        }
        catch(JWTVerificationException exception){
            return "";
        }
    }

    private Instant generatedExpirationDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }


}
