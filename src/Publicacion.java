/*
1. Reglas de Publicacion (Padre)
•Invariante de Likes: El contador nunca puede ser negativo. darLike() incrementa en 1.
•Autor Obligatorio: No se permiten autores vacíos o nulos (longitud > 2 caracteres).
•ID Inmutable: El identificador único se asigna al construir el objeto y no tiene setter público.
*/
import java.time.LocalDate;

public abstract class Publicacion {
    private String id;
    private String autor;
    private String fechaCreacion;
    private int likes;
    protected boolean estaActiva;

    public Publicacion(String id, String autor) {
        this.id = id;
        this.autor = autor;
        this.fechaCreacion = LocalDate.now().toString();
        this.likes = 0;
        this.estaActiva = true;
    }

    public void darLike() {
        this.likes++;
        System.out.println("¡¡Has dado like a la publicación!!");
    }

    public abstract void mostrarDetalle();

    public boolean validarAutor(String autor) {
        boolean validado = false;
        if (autor == null) {
            System.out.println("El autor no puede ser null");
        } else if (autor.trim().length() < 2) {
            System.out.println("El autor no puede estar vacío o tener menos de 2 caracteres");
        } else {
            validado = true;
        }
        return validado;
    }

    public String getId(){
        return this.id;
    }

    public int getLikes(){
        return this.likes;
    }

    public boolean isEstaActiva(){
        return this.estaActiva;
    }

    public String getAutor(){
        return this.autor;
    }

    public String getFechaCreacion(){
        return this.fechaCreacion;
    }

}
