package com.video.juegos.repository;

import java.util.ArrayList;
import java.util.Objects;
import com.video.juegos.dao.VideojuegoDAO;
import com.video.juegos.model.Videojuego;

// Capa distinta del DAO: presenta la colección de videojuegos a la aplicación.
public class VideojuegoRepository {
    private final VideojuegoDAO videojuegoDAO;

    public VideojuegoRepository(VideojuegoDAO videojuegoDAO) {
        this.videojuegoDAO = Objects.requireNonNull(videojuegoDAO);
    }

    public ArrayList<Videojuego> obtenerTodos() {
        return videojuegoDAO.obtenerTodos();
    }

    public Videojuego buscarPorTitulo(String titulo) {
        return videojuegoDAO.buscarPorTitulo(titulo);
    }

    // Consulta de la colección, independiente de si el DAO usa JSON o SQLite.
    public ArrayList<Videojuego> obtenerPorPlataforma(String plataforma) {
        ArrayList<Videojuego> resultado = new ArrayList<>();
        for (Videojuego videojuego : videojuegoDAO.obtenerTodos()) {
            if (videojuego.getPlataforma().equalsIgnoreCase(plataforma)) {
                resultado.add(videojuego);
            }
        }
        return resultado;
    }

    public void guardar(Videojuego videojuego) {
        videojuegoDAO.guardar(videojuego);
    }
}
