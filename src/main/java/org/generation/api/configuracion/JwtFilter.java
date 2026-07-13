package org.generation.api.configuracion;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.filter.GenericFilterBean;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class JwtFilter extends GenericFilterBean {

    public static String signature = "a-string-secret-at-least-256-bits-long";
    public static final String secret = Base64.getEncoder().encodeToString(signature.getBytes(StandardCharsets.UTF_8));

    public static SecretKey getSignInKey(){
        byte[] keyBytes = Base64.getDecoder().decode(secret);
        // byte[] keyBytes = signature.getBytes();
        return Keys.hmacShaKeyFor(keyBytes);
    }// getSignInKey


    // doFilter: filtrar las peticiones HTTP por Method y Endpoit, a través de el token proporcionado
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest; // convierto interfaz a clase HttpServletRequest

        if( requiereAutenticacion(httpRequest) ){ // Esta condición verifica si se requiere un filtro según el method y endpoint recibidos
            // Recuperar el Token
            String token = obtenerToken(httpRequest);

            // Validar el Token
            validarToken(token);
        }// requiereAutenticacion

        filterChain.doFilter(servletRequest, servletResponse);

    }// doFilter

    private boolean requiereAutenticacion(HttpServletRequest httpRequest) {
        String method = httpRequest.getMethod(); // Recupera el méthod: POST, PUT, DELETE, GET
        String URI = httpRequest.getRequestURI(); // Recupera el endpoint al que se hizo la solicitud

        return ( method.equals("POST") && !URI.contains("/api/usuarios/") ||
                 method.equals("GET") && !URI.contains("/api/productos/") ||
                 method.equals("PUT") ||
                 method.equals("DELETE"));
    }// requiereAutenticacion

    private String obtenerToken(HttpServletRequest httpRequest) throws ServletException {
        String authHeader = httpRequest.getHeader("Authorization");

        if( authHeader == null || !authHeader.startsWith("Bearer ")){// Authorization: Bearer xxxxxxxxxxxxxxxxxx
            throw new ServletException("1. Token inválido.");
        }// if

        return authHeader.substring(7);
    }// obtenerToken

    private void validarToken(String token) throws ServletException {

        try{
            Claims claims = Jwts.parser()
                    .verifyWith( getSignInKey() )
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
            System.out.println(claims.getSubject());
        } catch(SignatureException | MalformedJwtException | ExpiredJwtException e){
            throw new ServletException("2. Token inválido.");
        }// try-catch

    }// validarToken

}// class JwtFilter
