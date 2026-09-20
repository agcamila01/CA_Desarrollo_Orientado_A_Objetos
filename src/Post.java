/*
•Límite de Texto:
El pieDeFoto tiene un límite máximo de 2200 caracteres.
•Comentarios: 
Si comentariosHabilitados == false, el detalle debe indicar explícitamente "Comentarios desactivados".
•Ubicación Opcional:
Si no se define ubicación, se almacena como "Sin ubicación".
*/

public class Post extends Publicacion {
    private String pieDeFoto;
    private String ubicacion;
    private boolean comentariosHabilitados;
    private static int contadorPost = 1;

    public Post(Autor autor, String pieFoto, String ubicacion) {
        super("POST-"+contadorPost, autor);
        contadorPost++;
        editarPieDeFoto(pieFoto);
        setUbicacion(ubicacion);
        this.comentariosHabilitados = true; 
    }

    @Override 
    public void mostrarDetalle() {
        System.out.println("=== " + this.getId() + " ===");
        System.out.println("Autor: " + this.getAutor().getNombre());
        if (this.getEstadoComentarios()) {
            System.out.println("Comentarios Habilitados");
        } else {
            System.out.println("Comentarios Deshabilitados");
        }
        System.out.println("Descripción: " + this.getPieDeFoto());
        System.out.println("Ubicación: " + this.getUbicacion());
        System.out.println("Me gusta: " + this.getLikes());
        System.out.println("Fecha de Publicación: " + this.getFechaCreacion());
        System.out.println("===========================");
    }

    public void editarPieDeFoto(String nuevoTexto) {
        if (nuevoTexto.length() < 2200) {
            this.pieDeFoto = nuevoTexto;
        } else {
            System.out.println("El pie de foto no fue actualizado porque excedió los 2200 caracteres permitidos");
        }
    }
    
    public String getPieDeFoto() {
        return this.pieDeFoto;
    }

    public void setUbicacion(String ubicacion) {
        if (ubicacion.trim().length() <= 0) {
            this.ubicacion = "Sin Ubicación";
        } else {
            this.ubicacion = ubicacion;
        }
    }

    public String getUbicacion() {
        return this.ubicacion;
    }

    public void setComentariosHabilitados(){
        if (this.comentariosHabilitados == true) {
            this.comentariosHabilitados = false;
            System.out.println("Comentarios Deshabilitados");
        } else {
            this.comentariosHabilitados = true;
            System.out.println("Comentarios Habilitados");
        }
    }

    public boolean getEstadoComentarios() {
        return this.comentariosHabilitados;
    }
}


