/*
1. Reglas de Publicacion (Padre)
•Invariante de Likes: El contador nunca puede ser negativo. darLike() incrementa en 1.
•Autor Obligatorio: No se permiten autores vacíos o nulos (longitud > 2 caracteres).
•ID Inmutable: El identificador único se asigna al construir el objeto y no tiene setter público.
*/
import java.time.LocalDate;

public abstract class Publicacion {
    private String id;
    private Autor autor;
    private String fechaCreacion;
    private int likes;
    protected boolean estaActiva;

    public Publicacion(String id, Autor autor) {
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


    public String getId(){
        return this.id;
    }

    public int getLikes(){
        return this.likes;
    }

    public boolean isEstaActiva(){
        return this.estaActiva;
    }

    public Autor getAutor(){
        return this.autor;
    }

    public String getFechaCreacion(){
        return this.fechaCreacion;
    }

    

}
