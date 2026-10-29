package com.video.juegos.exception;

// Permite informar errores de persistencia sin exponer Gson o IOException a la vista.
public class AccesoDatosException extends RuntimeException {
    public AccesoDatosException(String mensaje) {
        super(mensaje);
    }

    public AccesoDatosException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
