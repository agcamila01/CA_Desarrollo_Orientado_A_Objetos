package com.video.juegos.controller;

import java.util.ArrayList;

import com.video.juegos.model.Videojuego;
import com.video.juegos.service.InventarioService;
import java.util.Objects;

public class InventarioController {

    private final InventarioService inventarioService;

    public InventarioController(InventarioService inventarioService) {
        this.inventarioService = Objects.requireNonNull(inventarioService);
    }

    public ArrayList<Videojuego> getInventario(){
        return inventarioService.obtenerTodos();
    }

    public Videojuego buscarPorTitulo(String titulo) {
        return inventarioService.buscarPorTitulo(titulo);
    }

    public ArrayList<Videojuego> buscarPorPlataforma(String plataforma) {
        return inventarioService.obtenerPorPlataforma(plataforma);
    }

    public void guardar(Videojuego videojuego) {
        inventarioService.guardar(videojuego);
    }
}
