import java.util.Scanner;

public class Main {//
    public static void main(String[] args) {
        mostrarMenu();
    } 
    
    static void mostrarMenu(){
        Personaje personaje_jugador = new Personaje();
        String opcion_elegida;
        Scanner scanner = new Scanner(System.in);
        boolean iniciar_menu = true;
        System.out.println("***** BIENVENIDOS AL MUNDO DE WESTEROS *****");
        
        while (iniciar_menu) {
            System.out.println("1.- Crear personaje");
            System.out.println("2.- Ver personaje");
            System.out.println("3.- Salir");
            System.out.print("Por favor ingrese una opción: "); 
            opcion_elegida = scanner.nextLine();
            System.out.print("La opcion escogida por el usuario es: " + opcion_elegida + "\n");

            switch (opcion_elegida) {
                case "1":
                    System.out.println("CREACION DE PERSONAJE");

                    System.out.println("¿Como llamaras a tu heroe?");
                    personaje_jugador.setNombre(scanner.nextLine());

                    System.out.println("¿Cual es el apellido de tu héroe?");
                    personaje_jugador.setApellido(scanner.nextLine());
                    
                    System.out.println("Indique la vida del heroe: ");
                    //ya no lo haremos asi porque es inseguro
                    //personaje_jugador.vida = Integer.parseInt(scanner.nextLine());
                    personaje_jugador.setearVida(Integer.parseInt(scanner.nextLine()));

                    System.out.println("Asignarle la edad al personaje: ");
                    personaje_jugador.ingresarEdad(Integer.parseInt(scanner.nextLine()));

                    break;
                case "2":
                    System.out.println("VER PERSONAJE");
                    System.out.println("Nombre: " + personaje_jugador.getNombre());
                    System.out.println("Apellido: " + personaje_jugador.getApellido());
                    System.out.println("Vida: " + personaje_jugador.verVida());
                    System.out.println("¿Se encuentra vivo? " + personaje_jugador.esta_vivo);
                    System.out.println("Edad: " + personaje_jugador.obtenerEdad());
                    break;
                case "3":
                    System.out.println("GRACIAS POR JUGAR");
                    iniciar_menu = false;
                    break;
                default:
                    System.out.println("POR FAVOR INGRESE UNA DE LAS OPCIONES PERMITIDAS");
                    break;
            }
        }
    }
}
