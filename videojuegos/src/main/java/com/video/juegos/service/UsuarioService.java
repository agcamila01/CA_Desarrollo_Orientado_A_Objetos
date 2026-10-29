package com.video.juegos.service;

import java.util.ArrayList;
import java.util.Objects;
import com.video.juegos.model.Usuario;
import com.video.juegos.repository.UsuarioRepository;

public class UsuarioService {
    private final UsuarioRepository repositorio;

    public UsuarioService(UsuarioRepository repositorio) {
        this.repositorio = Objects.requireNonNull(repositorio);
    }

    public ArrayList<Usuario> obtenerTodos() {
        return repositorio.obtenerTodos();
    }

    public Usuario buscarPorRut(String rut) {
        if (rut == null || rut.isBlank()) {
            throw new IllegalArgumentException("Ingrese un RUT para buscar.");
        }
        return repositorio.buscarPorRut(rut.trim());
    }

    public ArrayList<Usuario> obtenerPorNivel(String nivel) {
        if (nivel == null || nivel.isBlank()) {
            throw new IllegalArgumentException("Ingrese un nivel gamer.");
        }
        return repositorio.obtenerPorNivel(nivel.trim());
    }

    public void guardar(Usuario usuario) {
        if (usuario == null || usuario.getRut() == null || usuario.getRut().isBlank()) {
            throw new IllegalArgumentException("El socio debe tener un RUT.");
        }
        if (usuario.getNombre() == null || usuario.getNombre().isBlank()) {
            throw new IllegalArgumentException("El socio debe tener un nombre.");
        }
        if (repositorio.buscarPorRut(usuario.getRut()) != null) {
            throw new IllegalArgumentException("Ya existe un socio con ese RUT.");
        }
        repositorio.guardar(usuario);
    }
}
