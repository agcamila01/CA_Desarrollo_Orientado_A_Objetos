package com.camila.biblioapp.model;

public class Revista extends Material {

    private String mesPublicacion;
    private String periodicidad;

    public Revista(String titulo, String autor, int cantidadDisponible, String mesPublicacion, String periodicidad) {
        super(titulo, autor, cantidadDisponible);
        this.mesPublicacion = mesPublicacion;
        this.periodicidad = periodicidad;
    }

    public String getMesPublicacion() {
        return mesPublicacion;
    }

    public String getPeriodicidad() {
        return periodicidad;
    }

    @Override
    public int calcularDiasPrestamo() {
        return 3;
    }

    @Override
    public String mostrarInfo() {
        return "[REVISTA]\n" + super.mostrarInfo() + "\nMes de publicación: " + mesPublicacion + "\nPeriodicidad: " + periodicidad;
    }
}
