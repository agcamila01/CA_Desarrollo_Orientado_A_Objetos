import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Publicacion> feed = new ArrayList<>();
        boolean ejecutando = true;

        while (ejecutando) {
            System.out.println("\n=== INSTAGRAM CONSOLE APP ===");
            System.out.println("1. Crear publicación");
            System.out.println("2. Ver Feed completo (Polimorfismo)");
            System.out.println("3. Dar Like a una publicación");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");

            String opcion = scanner.nextLine().trim();

            switch (opcion) {
                case "1":
                    //crearPublicacion(scanner, feed);
                    break;
                case "2":
                    mostrarFeed(feed);
                    break;
                case "3":
                    //darLikePublicacion(scanner, feed);
                    break;
                case "4":
                    System.out.println("¡Hasta pronto! 🛸");
                    ejecutando = false;
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
                    break;
            }
        }
    }

    private static void mostrarFeed(ArrayList<Publicacion> feed) {
        if (feed.isEmpty()) {
            System.out.println("El feed está vacío. ¡Crea una publicación primero!");
            return;
        }
        System.out.println("\n--- FEED DE PUBLICACIONES ---");
        // POLIMORFISMO: Una sola llamada a método padre ejecuta la versión del hijo correspondiente
        for (Publicacion pub : feed) {
            pub.mostrarDetalle();
            System.out.println("---------------------------");
        }
    }
}