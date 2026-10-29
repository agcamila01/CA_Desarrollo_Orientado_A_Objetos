package com.video.juegos.controller;

import com.video.juegos.model.Usuario;
import com.video.juegos.service.UsuarioService;
import java.util.Objects;

public class UserController {
    private final UsuarioService usuarioService;

    public UserController(UsuarioService usuarioService) {
        this.usuarioService = Objects.requireNonNull(usuarioService);
    }
    
    public Usuario buscarUsuario(String rut) {
        return usuarioService.buscarPorRut(rut);
    }

    public void guardar(Usuario usuario) {
        usuarioService.guardar(usuario);
    }

}
