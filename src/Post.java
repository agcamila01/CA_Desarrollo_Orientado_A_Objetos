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

    public Post(String id, String autor, String pieFoto, String ubicacion) {
        super(id, autor);
        editarPieDeFoto(pieFoto);
        setUbicacion(ubicacion);
    }

    @Override 
    public void mostrarDetalle() {
        System.out.println("Detalles del post");
    }

    public void editarPieDeFoto(String nuevoTexto) {
        if (nuevoTexto.length() < 2200) {
            this.pieDeFoto = nuevoTexto;
        } else {
            System.out.println("El pie de foto no fue actualizado porque excedió los 2200 caracteres permitidos");
        }
    }

    public void setUbicacion(String ubicacion) {
        if (ubicacion.trim().length() <= 0) {
            this.ubicacion = "Sin Ubicación";
        } else {
            this.ubicacion = ubicacion;
        }
    }
}


