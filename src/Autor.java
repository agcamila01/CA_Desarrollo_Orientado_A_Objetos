import java.util.ArrayList;

public class Autor {
    private int id;
    private String nombre;
    private ArrayList <Autor> autores = new ArrayList<>();

    public Autor(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void almacenarAutores(Autor autor) {
        autores.add(autor); //retorna true false
    }

    public void verAutores() {
        System.out.println("Lista de Autores: ");
        for (int i = 0; i < autores.size(); i++) {
            System.out.println(autores.get(i));
        }
    }

    public ArrayList<Autor> getAutores() {
        return this.autores;
    }

}

/* 
crear rama nueva llamada proyecto/instagram en donde debes crear el diagrama de clases UML de instagram disponible en la 
web del curso
DESAFIO: el autor debe ser una clase en lugar de un atributo y ud debe implementar un metodo que me permita almacenar multiples autores.
Luego implemente un segundo metodo para mostrar esos autores.
*/