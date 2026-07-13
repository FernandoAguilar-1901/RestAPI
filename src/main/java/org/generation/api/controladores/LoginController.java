package org.generation.api.controladores;

import jakarta.servlet.ServletException;
import org.generation.api.dto.LoginRequest;
import org.generation.api.dto.TokenAcceso;
import org.generation.api.servicios.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/api/login") // http://localhost:8080/api/login
public class LoginController {

    private final UsuarioService usuarioService;

    @Autowired
    public LoginController(UsuarioService service){
        this.usuarioService = service;
    }// constructor LoginController

    @PostMapping
    public TokenAcceso validarUsuario(@RequestBody LoginRequest request) throws ServletException {

        if( usuarioService.validarUsuario(request) ){
            return new TokenAcceso( usuarioService.generarToken( request.getEmail() ) );
        }// if

        throw new ServletException("> LoginError: El correo o la contrseña son inválidos.");

    }// validarUsuario
}// class LoginController