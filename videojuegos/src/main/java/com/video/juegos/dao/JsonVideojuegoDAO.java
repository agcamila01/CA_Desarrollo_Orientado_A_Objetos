package com.video.juegos.dao;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Objects;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.video.juegos.exception.AccesoDatosException;
import com.video.juegos.model.JuegoDigital;
import com.video.juegos.model.JuegoFisico;
import com.video.juegos.model.Videojuego;

public class JsonVideojuegoDAO implements VideojuegoDAO {
    private final ArchivoJson archivo;
    private final Gson gson = new Gson();

    public JsonVideojuegoDAO() {
        this(Path.of("datos", "inventario.json"));
    }

    public JsonVideojuegoDAO(Path ruta) {
        archivo = new ArchivoJson(Objects.requireNonNull(ruta), "/inventario.json");
    }

    @Override
    public ArrayList<Videojuego> obtenerTodos() {
        ArrayList<Videojuego> inventario = new ArrayList<>();

        try {
            for (JsonElement elemento : archivo.leer()) {
                JsonObject videojuego = elemento.getAsJsonObject();
                String tipo = videojuego.get("tipo").getAsString();

                switch (tipo) {
                    case "fisico":
                        inventario.add(gson.fromJson(videojuego, JuegoFisico.class));
                        break;
                    case "digital":
                        inventario.add(gson.fromJson(videojuego, JuegoDigital.class));
                        break;
                    default:
                        throw new AccesoDatosException("Tipo de videojuego desconocido: " + tipo);
                }
            }
        } catch (JsonParseException | IllegalStateException | NullPointerException e) {
            throw new AccesoDatosException("El inventario contiene un registro inválido", e);
        }

        return inventario;
    }

    @Override
    public Videojuego buscarPorTitulo(String titulo) {
        Objects.requireNonNull(titulo, "Debe indicar un título");
        for (Videojuego videojuego : obtenerTodos()) {
            if (videojuego.getTitulo().equalsIgnoreCase(titulo.trim())) {
                return videojuego;
            }
        }
        return null;
    }

    @Override
    public void guardar(Videojuego videojuego) {
        Objects.requireNonNull(videojuego, "Debe indicar un videojuego");
        JsonObject datosJuego = gson.toJsonTree(videojuego).getAsJsonObject();

        // getTipo() es para la tabla; el JSON conserva sus valores originales.
        if (videojuego instanceof JuegoFisico) {
            datosJuego.addProperty("tipo", "fisico");
        } else if (videojuego instanceof JuegoDigital) {
            datosJuego.addProperty("tipo", "digital");
        } else {
            throw new IllegalArgumentException("El tipo de videojuego no está soportado");
        }

        JsonArray inventario = archivo.leer();
        inventario.add(datosJuego);
        archivo.escribir(inventario);
    }
}
