package org.generation.api.servicios;

import org.generation.api.dto.PassDto;
import org.generation.api.modelos.Usuario;
import org.generation.api.repositorios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

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
            usuarioRepository.save(usuario);
        } else {
            usuario = null;
        }// else

        return usuario;
    }// crearUsuario


    public Usuario actualizarUsuario(Long id, PassDto dto) {
        Usuario usuario = null;

        if(usuarioRepository.existsById(id)){
            Usuario u = usuarioRepository.findById(id).get();

            if( dto.getPassActual().equals( u.getPass() ) ){
                u.setPass( dto.getPassNuevo() );
                usuario = usuarioRepository.save(u);
            }// if pass
        }// if

        return usuario;
    }// actualizarUsuario

}// class UsuarioService
