package com.camila.biblioapp.model;

import java.util.List;

public class CatalogoJson {
    private List<Libro> libros;
    private List<Revista> revistas;

    public CatalogoJson() {}

    public List<Libro> getLibros() { return libros; }
    public List<Revista> getRevistas() { return revistas; }
}
