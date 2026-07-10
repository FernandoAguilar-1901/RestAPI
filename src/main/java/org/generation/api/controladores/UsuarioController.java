package org.generation.api.controladores;

import org.generation.api.dto.PassDto;
import org.generation.api.modelos.Usuario;
import org.generation.api.servicios.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/api/usuarios/")// http://localhost:8080/api/usuarios/
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService service){
        this.usuarioService = service;
    }// constructor UsuarioController

    @GetMapping // http://localhost:8080/api/usuarios/
    public List<Usuario> getUsuarios(){
        return usuarioService.getUsuarios();
    }// getUsuarios

    @GetMapping(path="{usuarioId}")// http://localhost:8080/api/usuarios/{usuarioId}
    public Usuario getUsuario(@PathVariable("usuarioId") Long id){
        return usuarioService.getUsuario(id);
    }// getUsuario

    @DeleteMapping(path="{usuarioId}")// http://localhost:8080/api/usuarios/{usuarioId}
    public Usuario deleteUsuario(@PathVariable("usuarioId") Long id){
        return usuarioService.deleteUsuario(id);
    }// deleteUsuario

    @PostMapping
    public Usuario crearUsuario(@RequestBody Usuario usuario){
        return usuarioService.crearUsuario(usuario);
    }// crearUsuario

    @PutMapping(path="{usuarioId}")// http://localhost:8080/api/usuarios/5
    public Usuario actualizarUsuario(@PathVariable("usuarioId") Long id,
                                     @RequestBody PassDto dto){
        return usuarioService.actualizarUsuario(id, dto);
    }// actualizarUsuario
}// class UsuarioController
