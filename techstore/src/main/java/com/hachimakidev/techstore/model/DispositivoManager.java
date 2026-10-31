package com.hachimakidev.techstore.model;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class DispositivoManager {

    private ArrayList<Dispositivo> dispositivos = new ArrayList<>();

    public DispositivoManager() {
        cargarDatos();
    }

    private void cargarDatos() {
        InputStream stream = getClass().getResourceAsStream("/dispositivos.json");
    
        if (stream == null) {
            throw new IllegalStateException("No se encontró dispositivos.json en resources");
        }

        try (InputStreamReader reader = new InputStreamReader(stream)) {
            Gson gson = new Gson();

            JsonObject[] dispositivosJson = gson.fromJson(reader, JsonObject[].class);

            for (JsonObject dispositivo : dispositivosJson) {
                String tipo = dispositivo.get("tipo").getAsString();
                if (tipo.equals("notebook")) {
                    Notebook notebook = gson.fromJson(dispositivo, Notebook.class);
                    dispositivos.add(notebook);
                } else if (tipo.equals("smartphone")) {
                    Smartphone smartphone = gson.fromJson(dispositivo, Smartphone.class);
                    dispositivos.add(smartphone);
                }
            }

            /* OTRA FORMA DE HACER ESTA PARTE DEL FOR EACH - SE OBTIENE EL MISMO RESULTADO*/

            // for (JsonObject dispositivo : dispositivosJson) {
            //     String tipo = dispositivo.get("tipo").getAsString();

            //     switch (tipo){
            //         case "notebook":
            //             int idNotebook = dispositivo.get("id").getAsInt();
            //             String marcaNotebook = dispositivo.get("marca").getAsString();
            //             String modeloNotebook = dispositivo.get("modelo").getAsString();
            //             int precioNotebook = dispositivo.get("precio").getAsInt();
            //             boolean disponibleNotebook = dispositivo.get("disponible").getAsBoolean();
            //             int ramGb = dispositivo.get("ramGb").getAsInt();
            //             String procesador = dispositivo.get("procesador").getAsString();

            //             Notebook notebook = new Notebook(idNotebook, marcaNotebook, modeloNotebook, precioNotebook, disponibleNotebook, ramGb, procesador);
            //             dispositivos.add(notebook);
            //             break;
            //         case "smartphone":
            //             int idSmartphone = dispositivo.get("id").getAsInt();
            //             String marcaSmartphone = dispositivo.get("marca").getAsString();
            //             String modeloSmartphone = dispositivo.get("modelo").getAsString();
            //             int precioSmartphone = dispositivo.get("precio").getAsInt();
            //             boolean disponiblSmartphone = dispositivo.get("disponible").getAsBoolean();
            //             int almacenamiento = dispositivo.get("almacenamientoGb").getAsInt();
            //             int camaraMP = dispositivo.get("camaraMp").getAsInt();

            //             Smartphone smartphone = new Smartphone(idSmartphone, marcaSmartphone, modeloSmartphone, precioSmartphone, disponiblSmartphone, almacenamiento, camaraMP);
            //             dispositivos.add(smartphone);
            //             break;
            //         default:
            //             System.out.println("Tipo de dispositivo desconocido: " + tipo);
            //             break;
            //     }
                
            // }
            
        } catch (java.io.IOException e) {
            throw new IllegalStateException("No se pudo leer dispositivos.json", e);
        }
    }

    public ArrayList<Dispositivo> getDispositivos() {
        return dispositivos;
    }

    public void agregarDispositivo(Dispositivo nuevo) {
        dispositivos.add(nuevo);
    }
}

