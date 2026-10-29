package com.video.juegos.dao;

import java.util.ArrayList;
import com.video.juegos.model.Videojuego;

// Contrato de acceso a datos; no depende de JavaFX ni del formato JSON.
public interface VideojuegoDAO {
    ArrayList<Videojuego> obtenerTodos();
    Videojuego buscarPorTitulo(String titulo);
    void guardar(Videojuego videojuego);
}
