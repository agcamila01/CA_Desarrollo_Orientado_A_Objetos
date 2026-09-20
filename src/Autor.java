import java.util.ArrayList;

public class Autor {
    private String id;
    private String nombre;
    private ArrayList <Autor> autores = new ArrayList<>();
    private static int contadorAutor = 1;

    public Autor(String nombre) {
        this.id = "AU-" + contadorAutor++;
        this.setNombre(nombre);
    }

    public String getId() {
        return this.id;
    }

    public void setNombre(String nombre) { //poner el bucle cuando se pide el dato en main
        if (validarNombreAutor(nombre)) {
            this.nombre = nombre;
        } else {
            this.nombre = "####";
        }
        
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

    public static boolean validarNombreAutor(String nombreAutor) { //static -> funciona sin crear instancia
        if (nombreAutor == null) {
            System.out.println("El autor no puede ser null");
            return false;
        }
        if (nombreAutor.trim().length() < 2) {
            System.out.println("El autor no puede estar vacío o tener menos de 2 caracteres");
            return false;
        }
        return true;
    }

}

/* 
crear rama nueva llamada proyecto/instagram en donde debes crear el diagrama de clases UML de instagram disponible en la 
web del curso
DESAFIO: el autor debe ser una clase en lugar de un atributo y ud debe implementar un metodo que me permita almacenar multiples autores.
Luego implemente un segundo metodo para mostrar esos autores.
*/