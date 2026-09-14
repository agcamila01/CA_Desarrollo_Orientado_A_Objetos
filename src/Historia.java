/*
Vigencia Temporal: Nace activa con duración de 24 hrs. Al llamar a caducar(), estaActiva = false.
Visualización Restringida: Si esMejoresAmigos == true, solo usuarios autorizados pueden acceder al contenido.
Métricas: Cada llamada a registrarVista() incrementa el contador de vistas si la historia está activa.
*/

public class Historia extends Publicacion {
    private int duracionHoras = 24;
    private boolean esMejoresAmigos;
    private int vistas;
    
    public Historia(String id, String autor, boolean esMejoresAmigos){
        super(id, autor);
        this.esMejoresAmigos = true;
    }
    
    @Override 
    public void mostrarDetalle(){
        System.out.println("Detalle de la Historia");
    }

    public void registrarVisita(){
        if (isEstaActiva()) {
            vistas++;
        }
    }

    public void caducar(){
        this.estaActiva = false; //funciona igual sin this porque no hay otro atributo que se llame igual
    }
}
