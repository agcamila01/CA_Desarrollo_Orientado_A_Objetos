package com.video.juegos.repository;

import java.util.ArrayList;
import java.util.Objects;
import com.video.juegos.dao.UsuarioDAO;
import com.video.juegos.model.Usuario;

public class UsuarioRepository {
    private final UsuarioDAO usuarioDAO;

    public UsuarioRepository(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = Objects.requireNonNull(usuarioDAO);
    }

    public ArrayList<Usuario> obtenerTodos() {
        return usuarioDAO.obtenerTodos();
    }

    public Usuario buscarPorRut(String rut) {
        return usuarioDAO.buscarPorRut(rut);
    }

    public ArrayList<Usuario> obtenerPorNivel(String nivel) {
        ArrayList<Usuario> resultado = new ArrayList<>();
        for (Usuario usuario : usuarioDAO.obtenerTodos()) {
            if (usuario.getNivelGamer().equalsIgnoreCase(nivel)) {
                resultado.add(usuario);
            }
        }
        return resultado;
    }

    public void guardar(Usuario usuario) {
        usuarioDAO.guardar(usuario);
    }
}
