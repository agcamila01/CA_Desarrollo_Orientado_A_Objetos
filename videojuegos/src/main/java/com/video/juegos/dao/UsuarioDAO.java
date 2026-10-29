package com.video.juegos.dao;

import java.util.ArrayList;
import com.video.juegos.model.Usuario;

public interface UsuarioDAO {
    ArrayList<Usuario> obtenerTodos();
    Usuario buscarPorRut(String rut);
    void guardar(Usuario usuario);
}
