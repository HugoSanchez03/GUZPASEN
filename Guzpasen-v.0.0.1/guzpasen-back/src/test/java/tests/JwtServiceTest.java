package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.security.Key;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import guzpasen.services.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

class JwtServiceTest {

    private JwtService jwtService;

    @BeforeEach
    void setUp() {
        jwtService = new JwtService();
    }

    @Test
    void testGenerateTokenNotNull() {
        String token = jwtService.generateToken("usuarioTest");
        assertNotNull(token);
        assertFalse(token.isEmpty());
    }

    @Test
    void testGenerateTokenContainsUsername() {
        String username = "usuarioTest";
        String token = jwtService.generateToken(username);

        // Extraemos el key usando reflexión para hacer el parseo (alternativa sería exponer el key, 
        // pero aquí se usa reflexión para test)
        Key key = getKeyFromJwtService(jwtService);

        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        assertEquals(username, claims.getSubject());
        assertNotNull(claims.getIssuedAt());
        assertTrue(claims.getExpiration().after(new Date()));
    }

    // Método helper para obtener la key privada usando reflexión (porque está private final)
    private Key getKeyFromJwtService(JwtService jwtService) {
        try {
            var field = JwtService.class.getDeclaredField("key");
            field.setAccessible(true);
            return (Key) field.get(jwtService);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
