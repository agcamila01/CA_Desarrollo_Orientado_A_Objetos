
package com.camila.biblioapp.controller;

import java.util.List;

import com.camila.biblioapp.model.Material;
import com.camila.biblioapp.model.MaterialManager;
import com.camila.biblioapp.model.User;
import com.camila.biblioapp.model.UserManager;

public class CatalogoController {

    private MaterialManager materialManager = new MaterialManager();
    private UserManager userManager = new UserManager();

    public List<Material> obtenerCatalogo() {
        return materialManager.getCatalogo();
    }

    public String prestar(String rut, Material material) throws Exception {

        if (material == null) {
            throw new Exception("Debes seleccionar un material en la tabla.");
        }

        if (rut == null || rut.trim().isEmpty()) {
            throw new Exception("Escribe el RUT de quien solicita el material.");
        }

        String rutLimpio = rut.trim();

        User socio = userManager.devolverUsuarioPorRut(rutLimpio);

        if (socio == null) {
            throw new Exception("No existe ningún socio registrado con el RUT " + rutLimpio + ".");
        }

        if (!material.prestar()) {
            throw new Exception("No quedan ejemplares disponibles de '" + material.getTitulo() + "'.");
        }

        return socio.getNombre() + " se lleva '" + material.getTitulo() + "'.\n" + "Plazo de devolución: " + material.calcularDiasPrestamo() + " días.\n"
            + "Ejemplares restantes: " + material.getCantidadDisponible() + ".";
    }
}
