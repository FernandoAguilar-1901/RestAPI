package org.generation.api.servicios;

import io.jsonwebtoken.Jwts;
import org.generation.api.configuracion.JwtFilter;
import org.generation.api.dto.LoginRequest;
import org.generation.api.dto.PassDto;
import org.generation.api.modelos.Usuario;
import org.generation.api.repositorios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    public UsuarioService(UsuarioRepository repository){
        this.usuarioRepository = repository;
    }// constructor UsuarioService


    public List<Usuario> getUsuarios() {
        return usuarioRepository.findAll();
    }// getUsuarios


    public Usuario getUsuario(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("El usuario con el id [" + id + "] no existe"));
    }// getUsuario

    public Usuario deleteUsuario(Long id) {
        Usuario usuario = null;

        if(usuarioRepository.existsById(id)){
            usuario = usuarioRepository.findById(id).get();
            usuarioRepository.deleteById(id);
        }// if

        return usuario;
    }// deleteUsuario


    public Usuario crearUsuario(Usuario usuario) {
        Optional<Usuario> usr = usuarioRepository.findByEmail( usuario.getEmail() );

        if( usr.isEmpty() ){
            usuario.setPass( encoder.encode( usuario.getPass() ) );
            usuarioRepository.save(usuario);
        } else {
            usuario = null;
        }// else

        return usuario;
    }// crearUsuario


    // Versión actual
    public Usuario actualizarUsuario(Long id, PassDto dto) {
        Usuario usuario = null;

        if(usuarioRepository.existsById(id)){
            Usuario u = usuarioRepository.findById(id).get();

            if( encoder.matches( dto.getPassActual(), u.getPass() ) ){
                u.setPass( encoder.encode( dto.getPassNuevo() ) );
                usuario = usuarioRepository.save(u);
            }// if pass
        }// if

        return usuario;
    }// actualizarUsuario

    public boolean validarUsuario(LoginRequest request) {
        Optional<Usuario> usuario = usuarioRepository.findByEmail( request.getEmail() );

        if(usuario.isPresent()){
            Usuario u = usuario.get();

            if( encoder.matches(request.getPass(), u.getPass()) ){
                return true;
            }// Request pass matches user pass
        }// isPresent

        return false;
    }// validarUsuario

    public String generarToken(String email) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, 24);

        return Jwts.builder()
                .subject(email)
                .claim("role", "user")
                .issuedAt(new Date())
                .expiration(calendar.getTime())
                .signWith(JwtFilter.getSignInKey() )
                .compact();
    }// generarToken

}// class UsuarioService
