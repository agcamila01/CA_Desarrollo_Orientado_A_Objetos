/*
Vigencia Temporal: Nace activa con duración de 24 hrs. Al llamar a caducar(), estaActiva = false.
Visualización Restringida: Si esMejoresAmigos == true, solo usuarios autorizados pueden acceder al contenido.
Métricas: Cada llamada a registrarVista() incrementa el contador de vistas si la historia está activa.
*/

public class Historia extends Publicacion {
    private int duracionHoras = 24;
    private boolean esMejoresAmigos;
    private int vistas;
    private static int contadorHistoria = 1;
    
    public Historia(Autor autor, boolean esMejoresAmigos){
        super("HIST-"+ contadorHistoria, autor);
        contadorHistoria++;
        this.esMejoresAmigos = esMejoresAmigos;
    }
    
    @Override 
    public void mostrarDetalle(){
        System.out.println("=== " + this.getId() + " ===");
        System.out.println("Autor: " + this.getAutor().getNombre());
        if (this.isEstaActiva()) {
            System.out.println("Historia Activa");
            if (this.mejoresAmigos()) {
                System.out.println("Visibilidad: Mejores Amigos");
            } else {
                System.out.println("Visibilidad: Público");
            }
        } else {
            System.out.println("Historia Despublicada");
        }
        System.out.println("Visualizaciones: " + this.getVistas());
        System.out.println("Me gusta: " + this.getLikes());
        System.out.println("Fecha de Publicación: " + this.getFechaCreacion());
        System.out.println("===========================");
    }

    public void registrarVisita(){
        if (isEstaActiva()) {
            vistas++;
        }
    }
    public int getVistas(){
        return this.vistas;
    }

    public void caducar(){
        this.estaActiva = false; //funciona igual sin this porque no hay otro atributo que se llame igual
    }

    public boolean mejoresAmigos(){
        return this.esMejoresAmigos;
    }
}
