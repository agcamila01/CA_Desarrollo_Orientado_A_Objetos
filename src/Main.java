import java.util.Scanner;

public class Main {//
    public static void main(String[] args) {
        mostrarMenu();
    }

    static void mostrarMenu() {
        String opcion_elegida;
        Scanner scanner = new Scanner(System.in);
        boolean iniciar_menu = true;
        Personaje personaje_jugador = null;
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
                    personaje_jugador = crearPersonaje(scanner);
                    break;
                case "2":
                    if (personaje_jugador != null){
                        verDatosPersonaje(personaje_jugador);
                    } else {
                        System.out.println("No hay personajes registrados");
                    }
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

    static Personaje crearPersonaje(Scanner scanner) {
        System.out.println("CREACION DE PERSONAJE");
        System.out.println("Elije un personaje: ");
        System.out.println("1. Hacker");
        System.out.println("2. Programador");
        System.out.println("3. GameDev");
        System.out.println("4. Ciberseguridad");
        System.out.println("5. Sin categoria");
        System.out.println("Selecciona una opcion (1/2/3/4/5): ");

        String opcionTipoPersonaje = scanner.nextLine();
        Personaje nuevoPersonaje;

        switch (opcionTipoPersonaje) {
            case "1":
                nuevoPersonaje = new Hacker();
                System.out.print("Has seleccionado: Hacker");
                break;
            case "2":
                nuevoPersonaje = new Programador();
                System.out.print("Has seleccionado: Programador");
                break;
            case "3":
                nuevoPersonaje = new GameDev();
                System.out.print("Has seleccionado: GameDev");
                break;
            case "4":
                nuevoPersonaje = new Ciberseguridad();
                System.out.print("Has seleccionado: Ciberseguridad");
                break;
            default:
                nuevoPersonaje = new Personaje();
                System.out.println("Has seleccionado: SIN CATEGORIA");
                break;
        }

        //atributos comunes
        System.out.println("¿Como llamaras a tu heroe?");
        String nombreSinValidar = scanner.nextLine();
        while (nombreSinValidar.length() < 1){
            System.out.println("Ingrese un nombre valido");
            nombreSinValidar = scanner.nextLine();
        }
        nuevoPersonaje.setNombre(nombreSinValidar);

        System.out.println("¿Cual es el apellido de tu héroe?");
        nuevoPersonaje.setApellido(scanner.nextLine());

        System.out.println("Indique la vida del heroe: ");
        // ya no lo haremos asi porque es inseguro
        // personaje_jugador.vida = Integer.parseInt(scanner.nextLine());
        nuevoPersonaje.setearVida(Integer.parseInt(scanner.nextLine()));

        System.out.println("Asignarle la edad al personaje: ");
        nuevoPersonaje.ingresarEdad(Integer.parseInt(scanner.nextLine()));

        System.out.println("PERSONAJE CREADO");
        return nuevoPersonaje;

    }

    static void verDatosPersonaje(Personaje personaje_jugador) {
        System.out.println("VER PERSONAJE");
        System.out.println("Tipo: " + personaje_jugador.getTipo());
        System.out.println("Nombre: " + personaje_jugador.getNombre());
        System.out.println("Apellido: " + personaje_jugador.getApellido());
        System.out.println("Vida: " + personaje_jugador.verVida());
        System.out.println("¿Se encuentra vivo? " + personaje_jugador.esta_vivo);
        System.out.println("Edad: " + personaje_jugador.obtenerEdad());
    }
}
