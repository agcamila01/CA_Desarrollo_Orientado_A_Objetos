public class Instagram {
    public static void main(String[] args) {
        crearPublicacion();
    }

    static void crearPublicacion (){
        Publicacion publicacion = new Publicacion("1", "Pepito");
        Publicacion publicacion2 = new Publicacion("2", "Amaro");
        publicacion2.darLike();
        publicacion2.darLike();
        publicacion2.darLike();
        publicacion2.darLike();
        publicacion2.darLike();
        publicacion2.darLike();
        Publicacion publicacion3 = new Publicacion("3", "Alan");
        publicacion3.darLike();
        publicacion3.darLike();
        System.out.println("Los autores son: \n" + publicacion.getAutor() + "\n" + publicacion2.getAutor() + "\n" + publicacion3.getAutor());
        System.out.println("Los likes son: ");
        System.out.println(publicacion.getAutor() + ": " + publicacion.getLikes());
        System.out.println(publicacion2.getAutor() + ": " + publicacion2.getLikes());
        System.out.println(publicacion3.getAutor() + ": " + publicacion3.getLikes());
    }
}
