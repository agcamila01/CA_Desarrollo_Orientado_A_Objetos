package com.camila.biblioapp.model;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

public class MaterialManager {

    private List<Material> catalogo = new ArrayList<>();

    public MaterialManager() {
        cargarDesdeJson();
    }

    private void cargarDesdeJson() {
        try {
            InputStream is = getClass().getResourceAsStream("/catalogo.json");
            InputStreamReader reader = new InputStreamReader(is, StandardCharsets.UTF_8);

            CatalogoJson datos = new Gson().fromJson(reader, CatalogoJson.class);

            if (datos != null) {
                if (datos.getLibros() != null) catalogo.addAll(datos.getLibros());
                if (datos.getRevistas() != null) catalogo.addAll(datos.getRevistas());
            }

            System.out.println("Materiales cargados: " + catalogo.size());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Material> getCatalogo() {
        return catalogo;
    }
}
