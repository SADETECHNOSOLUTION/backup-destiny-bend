package com.Sadetechno.api_getway.config;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import reactor.core.publisher.Mono;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.Objects;

@Configuration
public class ApiGatewayConfig {

    @Value("${jwt.secret}")
    private String secretString;

    private JwtDecoder jwtDecoder;

    @PostConstruct
    public void init() {
        byte[] keyBytes = Base64.getDecoder().decode(secretString.getBytes(StandardCharsets.UTF_8));
        this.jwtDecoder = NimbusJwtDecoder.withSecretKey(new SecretKeySpec(keyBytes, "HmacSHA256")).build();
    }

    @Bean
public KeyResolver jwtKeyResolver() {
    return exchange -> Mono.justOrEmpty(exchange.getRequest().getHeaders().getFirst("Authorization"))
            .filter(auth -> auth.startsWith("Bearer "))
            .map(auth -> auth.substring(7))
            .flatMap(token -> Mono.fromCallable(() -> "gw:" + validateTokenAndGetUsername(token))
                    .subscribeOn(reactor.core.scheduler.Schedulers.boundedElastic())) // Offload blocking decode
            .defaultIfEmpty("gw:anonymous")
            .onErrorReturn("gw:invalid_token"); // Prevents 500 error if token is malformed
}

    // Helper function to extract and validate the username from the JWT token
    private String validateTokenAndGetUsername(String token) {
        Jwt jwt = jwtDecoder.decode(token);
        if (isTokenExpired(jwt)) {
            throw new RuntimeException("Token has expired");
        }
        return jwt.getClaimAsString("sub"); // "sub" is the claim for the username (or use any other claim you need)
    }

    // Helper function to check if the token is expired
    private boolean isTokenExpired(Jwt jwt) {
        Date expiration = Date.from(Objects.requireNonNull(jwt.getExpiresAt()));
        return expiration.before(new Date());
    }


}
