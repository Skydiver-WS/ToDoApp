package com.example.todoapp.service.secure.impl;

import com.example.todoapp.security.AppUserPrincipal;
import com.example.todoapp.service.secure.JwtService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;

@Service
@RequiredArgsConstructor
@Slf4j
public class JwtServiceImpl implements JwtService {
    /**
     * secretKey: Это секретный ключ, который используется для создания подписи. Этот ключ должен быть известен только серверу, который создает токены, и должен быть достаточно сложным и длинным для обеспечения безопасности.
     * Подписание токена важно для обеспечения его целостности и подлинности. При проверке токена на сервере, сервер использует этот же секретный ключ для проверки подписи. Если подпись не соответствует ожидаемой, то это означает, что токен был изменен или подделан, и сервер отклоняет его.
     * Таким образом, использование signWith с алгоритмом подписи и секретным ключом обеспечивает безопасность JWT токена и защищает его от подделки.
     */
    @Value("${app.secret.key}")
    private String secretKey;

    /**
     * Необходимо сначала сгенерировать токен
     *
     * @param userPrincipal
     * @return
     */
    @Override
    public String generateJwtToken(AppUserPrincipal userPrincipal) {
        log.info("Generate token");
        return generateJwtTokenFromUserName(userPrincipal);
    }

    @Override
    @SneakyThrows
    public String parseTokenForResponse(String token) {
        log.info("Parse token");
        return Jwts.parser()
                .setSigningKey(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();

    }
    @Override
    public boolean validToken(String username, UserDetails userDetails){
        log.info("Check valid token");
        String token = generateJwtTokenFromUserName(userDetails);
        return parseTokenForResponse(token).equals(username);
    }

    /**
     * Изучить моменты время жизни токена
     *

     * @return
     */
    @SneakyThrows
    private String generateJwtTokenFromUserName(UserDetails userDetails) {
        log.info("Generate JWT token from username");
        return Jwts.builder()
                .claims()
                .issuer("test")
                .subject(userDetails.getUsername())
                .audience()
                .and()
                .and()
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }
}
