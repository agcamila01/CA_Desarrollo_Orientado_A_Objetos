package com.hachimakidev.techstore.controller;

import java.util.ArrayList;

import com.hachimakidev.techstore.model.Dispositivo;
import com.hachimakidev.techstore.model.DispositivoManager;

public class DispositivoController {

    private DispositivoManager dispositivoManager;

    public DispositivoController() {
        dispositivoManager = new DispositivoManager();
    }

    public ArrayList<Dispositivo> getDispositivos() {
        return dispositivoManager.getDispositivos();
    }

    public void agregarDispositivo(Dispositivo dispositivoNuevo) {

        if (dispositivoNuevo == null) {
            throw new IllegalArgumentException("Debes ingresar un dispositivo.");
        }

        if (dispositivoNuevo.getMarca() == null || dispositivoNuevo.getMarca().isBlank()) {
            throw new IllegalArgumentException("Debes completar la marca.");
        }

        if (dispositivoNuevo.getModelo() == null || dispositivoNuevo.getModelo().isBlank()) {
            throw new IllegalArgumentException("Debes completar el modelo.");
        }
        
        if (dispositivoNuevo.getPrecio() <= 0) {
            throw new IllegalArgumentException("El precio debe ser mayor a cero.");
        }

        int siguienteId = dispositivoManager.getDispositivos().size() + 1;
        dispositivoNuevo.setId(siguienteId);
        dispositivoManager.agregarDispositivo(dispositivoNuevo);
    }

}



