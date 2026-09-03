public class Publicacion {
    private String id;
    private String autor;
    private String fechaCreacion;
    private int likes;
    protected boolean estaActiva;

    public Publicacion(String id, String autor) {
        this.id = id;
        this.autor = autor;
        this.likes = 0;
    }
    
    public void setAutor(String nombre) {
        if (nombre.length() > 20){
            System.out.println("El nombre no puede exceder los 20 caracteres");
        } else {
            this.autor = nombre;
        } 
    }

    public String getAutor() {
        return this.autor;
    }

    public void darLike() {
        this.likes = this.likes + 1;
    }

    public int getLikes() {
        return this.likes;
    }
}
