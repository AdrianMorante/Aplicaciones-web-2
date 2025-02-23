package T2_DAWII.util;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class Token {
	private final static String TOKEN_SECRETO = "EwMVqsVAxadThpuw3JEkADOwsS9rayDT";
    private final static Long TOKEN_DURACION = 600L;

    public static String crearToken(String user, String email) {
        long expiracionTiempo = TOKEN_DURACION * 1_000; 
        Date expiracionFecha = new Date(System.currentTimeMillis() + expiracionTiempo);

        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String fechaActual = formatoFecha.format(new Date());

        Map<String, Object> map = new HashMap<>();
        map.put("nombre", user);
        map.put("fecha_creacion", fechaActual); 

        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date()) 
                .setExpiration(expiracionFecha) 
                .addClaims(map)
                .signWith(Keys.hmacShaKeyFor(TOKEN_SECRETO.getBytes()))
                .compact();
    }

    public static UsernamePasswordAuthenticationToken getAuth(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(TOKEN_SECRETO.getBytes())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            String email = claims.getSubject();
            return new UsernamePasswordAuthenticationToken(email, null, Collections.emptyList());
        } catch (JwtException e) {
            return null;
        }
    }
}
