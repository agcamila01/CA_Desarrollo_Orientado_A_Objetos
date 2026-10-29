package com.video.juegos.dao;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Objects;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.video.juegos.exception.AccesoDatosException;
import com.video.juegos.model.Usuario;

public class JsonUsuarioDAO implements UsuarioDAO {
    private final ArchivoJson archivo;
    private final Gson gson = new Gson();

    public JsonUsuarioDAO() {
        this(Path.of("datos", "usuarios.json"));
    }

    public JsonUsuarioDAO(Path ruta) {
        archivo = new ArchivoJson(Objects.requireNonNull(ruta), "/usuarios.json");
    }

    @Override
    public ArrayList<Usuario> obtenerTodos() {
        ArrayList<Usuario> usuarios = new ArrayList<>();
        try {
            for (JsonElement elemento : archivo.leer()) {
                Usuario usuario = gson.fromJson(elemento, Usuario.class);
                if (usuario == null || usuario.getRut() == null) {
                    throw new AccesoDatosException("El archivo contiene un socio sin RUT");
                }
                usuarios.add(usuario);
            }
        } catch (JsonParseException | IllegalStateException e) {
            throw new AccesoDatosException("El archivo contiene un socio inválido", e);
        }
        return usuarios;
    }

    @Override
    public Usuario buscarPorRut(String rut) {
        Objects.requireNonNull(rut, "Debe indicar un RUT");
        for (Usuario usuario : obtenerTodos()) {
            if (usuario.getRut().equals(rut.trim())) {
                return usuario;
            }
        }
        return null;
    }

    @Override
    public void guardar(Usuario usuario) {
        Objects.requireNonNull(usuario, "Debe indicar un usuario");
        JsonArray usuarios = archivo.leer();
        usuarios.add(gson.toJsonTree(usuario));
        archivo.escribir(usuarios);
    }
}
