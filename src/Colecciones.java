import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;

public class Colecciones {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean mostrarMenu = true;
        while (mostrarMenu) {
            System.out.println("1. Conociendo Arrays");
            System.out.println("2. Conociendo ArrayList");
            System.out.println("9. Salir");

            System.out.println("Elige una opcion: ");
            String opcionIngresada = sc.nextLine();
        
            switch (opcionIngresada) {
                case "1":
                    conociendoArrays();
                    break;
                case "2":
                    conociendoArrayList();
                    break;
                case "3":
                    conociendoHashMap();
                    break;
                case "9":
                    mostrarMenu = false;
                    break;
                default:
                    break;
            }
        }

        


    }

    static void conociendoArrays() {
        String[] alumnos = new String[4]; //tamaño 4, indice desde el 0 al 3
        alumnos[0] = "Diego Marin";
        alumnos[1] = "Amaro Lopez";
        alumnos[2] = "Marianny Sanchez";
        alumnos[3] = "Gustavo Araneda";
        

        Integer[] edades = new Integer[4];
        edades[0] = 18;
        edades[1] = 19;
        edades[2] = 19;
        edades[3] = 18;

        // System.out.println("Datos de: " + alumnos[0] + " - Edad: " + alumnos[0]);
        // System.out.println("Datos de: " + alumnos[1] + " - Edad: " + alumnos[1]);
        // System.out.println("Datos de: " + alumnos[2] + " - Edad: " + alumnos[2]);
        // System.out.println("Datos de: " + alumnos[3] + " - Edad: " + alumnos[3]);

        for (int i = 0; i < edades.length; i++) {
            System.out.println("Datos de: " + alumnos[i] + " - Edad: " + alumnos[i]);
        }
    }

    static void conociendoArrayList() {
        ArrayList <String> alumnos = new ArrayList<>();
        alumnos.add("Diego Marin");
        alumnos.add("Amaro Lopes");
        alumnos.add("Marianny Sanchez");
        alumnos.add("Gustavo Araneda");

        alumnos.set(1,"Amaro López");
        alumnos.remove(1);
        for (int i = 0; i < alumnos.size(); i++) {
            System.out.println(alumnos.get(i));
        }
    }

    /* crear rama nueva llamada proyecto/instagram en donde debes crear el diagrama de clases UML de instagram disponible en la 
    web del curso
    DESAFIO: el autor debe ser una clase en lugar de un atributo y ud debe implementar un metodo que me permita almacenar multiples autores.
    Luego implemente un segundo metodo para mostrar esos autores.
    */

    static void conociendoHashMap() {
        HashMap <String, Integer> mapa = new HashMap<>(); 
        mapa.put("hola", 1);
        System.out.println(mapa);

    }
}
