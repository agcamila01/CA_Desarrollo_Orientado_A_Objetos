package com.video.juegos.dao;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.AtomicMoveNotSupportedException;
import java.nio.file.StandardCopyOption;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonParseException;
import com.video.juegos.exception.AccesoDatosException;

// Ayudante interno de los DAO: concentra la lectura y escritura de archivos.
final class ArchivoJson {
    private final Path archivo;
    private final String recursoInicial;
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    ArchivoJson(Path archivo, String recursoInicial) {
        this.archivo = archivo;
        this.recursoInicial = recursoInicial;
    }

    JsonArray leer() {
        try (Reader reader = abrirLectura()) {
            JsonArray datos = gson.fromJson(reader, JsonArray.class);
            if (datos == null) {
                throw new AccesoDatosException("El JSON está vacío: " + archivo);
            }
            return datos;
        } catch (IOException | JsonParseException e) {
            throw new AccesoDatosException("No se pudo leer el JSON: " + archivo, e);
        }
    }

    private Reader abrirLectura() throws IOException {
        if (Files.exists(archivo)) {
            return Files.newBufferedReader(archivo, StandardCharsets.UTF_8);
        }

        // Los recursos incluidos con la aplicación son los datos iniciales.
        InputStream stream = ArchivoJson.class.getResourceAsStream(recursoInicial);
        if (stream == null) {
            throw new AccesoDatosException("No se encontró el recurso: " + recursoInicial);
        }
        return new InputStreamReader(stream, StandardCharsets.UTF_8);
    }

    void escribir(JsonArray datos) {
        Path temporal = null;
        try {
            Path destino = archivo.toAbsolutePath();
            Path directorio = destino.getParent();
            Files.createDirectories(directorio);
            // Primero se escribe completo un temporal para proteger el archivo anterior.
            temporal = Files.createTempFile(directorio, "gamevault-", ".tmp");
            try (Writer writer = Files.newBufferedWriter(temporal, StandardCharsets.UTF_8)) {
                gson.toJson(datos, writer);
            }
            try {
                Files.move(temporal, destino, StandardCopyOption.REPLACE_EXISTING,
                        StandardCopyOption.ATOMIC_MOVE);
            } catch (AtomicMoveNotSupportedException e) {
                Files.move(temporal, destino, StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (IOException e) {
            throw new AccesoDatosException("No se pudo guardar el JSON: " + archivo, e);
        } finally {
            if (temporal != null) {
                try {
                    Files.deleteIfExists(temporal);
                } catch (IOException e) {
                    // Un temporal sobrante no debe ocultar el resultado del guardado.
                    temporal.toFile().deleteOnExit();
                }
            }
        }
    }
}
