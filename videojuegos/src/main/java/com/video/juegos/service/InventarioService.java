package com.video.juegos.service;

import java.util.ArrayList;
import java.util.Objects;
import com.video.juegos.model.Videojuego;
import com.video.juegos.repository.VideojuegoRepository;

// Validaciones de la aplicación; no abre archivos ni utiliza JavaFX.
public class InventarioService {
    private final VideojuegoRepository repositorio;

    public InventarioService(VideojuegoRepository repositorio) {
        this.repositorio = Objects.requireNonNull(repositorio);
    }

    public ArrayList<Videojuego> obtenerTodos() {
        return repositorio.obtenerTodos();
    }

    public Videojuego buscarPorTitulo(String titulo) {
        if (titulo == null || titulo.isBlank()) {
            throw new IllegalArgumentException("Ingrese un título para buscar.");
        }
        return repositorio.buscarPorTitulo(titulo.trim());
    }

    public ArrayList<Videojuego> obtenerPorPlataforma(String plataforma) {
        if (plataforma == null || plataforma.isBlank()) {
            throw new IllegalArgumentException("Ingrese una plataforma.");
        }
        return repositorio.obtenerPorPlataforma(plataforma.trim());
    }

    public void guardar(Videojuego videojuego) {
        if (videojuego == null || videojuego.getTitulo() == null
                || videojuego.getTitulo().isBlank()) {
            throw new IllegalArgumentException("El videojuego debe tener un título.");
        }
        if (videojuego.getPlataforma() == null || videojuego.getPlataforma().isBlank()) {
            throw new IllegalArgumentException("El videojuego debe tener una plataforma.");
        }
        repositorio.guardar(videojuego);
    }
}
