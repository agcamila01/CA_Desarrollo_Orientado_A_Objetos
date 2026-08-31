public class Publicacion {
    private String id;
    private String autor;
    private String fechaCreacion;
    private int likes;
    protected boolean estaActiva;

    public Publicacion(String id, String autor) {
        this.id = id;
        this.autor = autor;
    }

    public void darLike() {

    }

    public void mostrarDetalle() {

    }

    public boolean validarAutor(String autor) {
        return true;
    }

    public String getId(){
        return this.id;
    }

    public int getLikes(){
        return this.likes;
    }

    public boolean isEstaActiva(){
        return true;
    }
}
