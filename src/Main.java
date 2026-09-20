import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Publicacion> feed = new ArrayList<>();
        boolean ejecutando = true;

        while (ejecutando) {
            try {
                System.out.println("\n=== INSTAGRAM CONSOLE APP ===");
                System.out.println("1. Crear publicación");
                System.out.println("2. Ver Feed completo");
                System.out.println("3. Dar Like a una publicación");
                System.out.println("4. Filtrar por publicación");
                System.out.println("5. Buscar publicación por ID");
                System.out.println("6. Estadísticas del Feed");
                System.out.println("9. Salir");

                String opcion = solicitaString(scanner, "Seleccione una opción: ");

                switch (opcion) {
                    case "1":
                        crearPublicacion(scanner, feed);
                        break;
                    case "2":
                        mostrarFeed(feed);
                        break;
                    case "3":
                        darLikePublicacion(scanner, feed);
                        break;
                    case "4":
                        filtrarPublicaciones(scanner, feed);
                        break;
                    case "5":
                        buscarPorId(scanner, feed);
                        break;
                    case "6":
                        mostrarEstadisticas(feed);
                        break;
                    case "9":
                        System.out.println("¡Hasta pronto!");
                        ejecutando = false;
                        break;
                    default:
                        System.out.println("Opción no válida. Intente nuevamente.");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Error inesperado: " + e.getMessage());
                System.out.println("El programa continúa funcionando.");
            }
        }
    }

    static void crearPublicacion(Scanner scanner, ArrayList<Publicacion> feed) {
        boolean menuPublicacion = true;

        while (menuPublicacion) {
            System.out.println("=== Nueva Publicación ===");
            System.out.println("1. Historia");
            System.out.println("2. Reel");
            System.out.println("3. Post");
            System.out.println("9. Volver al menú principal");

            String opcionMenuPublicacion = solicitaString(scanner, "Elija una opción: ");
            
            if (opcionMenuPublicacion.equals("9")) {
                menuPublicacion = false;
                break;
            }

            if (!List.of("1","2","3").contains(opcionMenuPublicacion)) {
                System.out.println("Opcion Inválida. Intente nuevamente.");
                continue;
            }

            //datos comunes/del padre
            Autor autor = solicitaAutor(scanner);
            
            switch (opcionMenuPublicacion) {
                case "1":
                    boolean mejoresAmigos;
                    System.out.print("-- Visibilidad -- \n1) Mejores Amigos \n2) Todo el mundo\n");
                    String opcionMejoresAmigos = solicitaString(scanner, "Ingrese su opción: ");
                    switch (opcionMejoresAmigos) {
                        case "1":
                            mejoresAmigos = true;
                            break;
                        case "2":
                            mejoresAmigos = false;
                            break;
                        default:
                            mejoresAmigos = false;
                            break;
                    }
                    Historia historia = new Historia(autor, mejoresAmigos);
                    System.out.println("Historia Publicada");
                    feed.add(historia);
                    break;
                case "2":
                    int segundos = validarEntero(scanner, "Duración del reel (seg): ");
                    String audio = solicitaString(scanner, "Nombre del audio del reel: ");
                    Reel reel = new Reel(autor, segundos, audio);
                    System.out.println("Reel Publicado");
                    feed.add(reel);
                    break;
                case "3":
                    String pieFoto =  solicitaString(scanner, "Ingrese el pie de foto: ");
                    String ubicacion = solicitaString(scanner, "Ingrese ubicación: ");
                    Post post = new Post(autor, pieFoto, ubicacion);
                    System.out.println("Post Publicado");
                    feed.add(post);
                    break;
                case "9":
                    menuPublicacion = false;
                    break;
                default:
                    System.out.println("Opcion Inválida. Intente nuevamente.");
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

    static void darLikePublicacion(Scanner scanner, ArrayList<Publicacion> feed) {

        if (feed.isEmpty()) {
            System.out.println("El feed está vacío. ¡Crea una publicación primero!");
            return;
        }
        
        String idPublicacion = solicitaString(scanner, "Ingrese id de la publicacion: ");

        for (Publicacion pub : feed) {
            if (pub.getId().equals(idPublicacion)) {
                pub.darLike();
                if (pub instanceof Reel reel) {
                    reel.reproducir();
                }
                return;//también se puede hacer con un bool o contador
            }
        }
        System.out.println("No se encontró ninguna publicación con id: " + idPublicacion);
    }

    static void filtrarPublicaciones(Scanner scanner, ArrayList<Publicacion> feed) {
        
        if (feed.isEmpty()) {
            System.out.println("El feed está vacío. ¡Crea una publicación primero!");
            return;
        }

        boolean menuFiltro = true;
        while (menuFiltro) {
            System.out.println("Filtrar Publicaciones por: ");
            System.out.println("1. Historia");
            System.out.println("2. Reel");
            System.out.println("3. Post");
            System.out.println("9. Salir");
            System.out.print("Elija una opción (1/2/3/9): ");
            String opcionFiltro = scanner.nextLine();
            int contador = 0; //puede haber mejor opcion

            switch(opcionFiltro){
                case "1":
                    System.out.println("\n--- HISTORIA ---");
                    for (Publicacion pub : feed) {
                        if (pub instanceof Historia historia) {
                            pub.mostrarDetalle();
                            contador++;
                        }
                    }
                    if (contador == 0) {
                        System.out.println("No hay historias publicadas");
                    }
                    System.out.println("---------------------------");
                    break;
                case "2":
                    System.out.println("\n--- REEL ---");
                    for (Publicacion pub : feed) {
                        if (pub instanceof Reel reel) {
                            pub.mostrarDetalle();
                            contador++;
                        }
                    }
                    if (contador == 0) {
                        System.out.println("No hay reels publicados");
                    }
                    System.out.println("---------------------------");
                    break;
                case "3":
                    System.out.println("\n--- POST ---");
                    for (Publicacion pub : feed) {
                        if (pub instanceof Post post) {
                            pub.mostrarDetalle();
                            contador++;
                        }
                    }
                    if (contador == 0) {
                        System.out.println("No hay posts publicados");
                    }
                    System.out.println("---------------------------");
                    break;
                case "9":
                    menuFiltro = false;
                    break;
                default:
                    System.out.println("Elija una opción válida.");
                    break;
            }
        }
    }

    private static void buscarPorId(Scanner scanner, ArrayList<Publicacion> feed) {
        if (feed.isEmpty()) {
            System.out.println("El feed está vacío.");
            return;
        }

        System.out.print("Ingrese el ID a buscar: ");
        String idBuscado = scanner.nextLine().trim();

        // Búsqueda lineal
        Publicacion encontrada = null;
        for (Publicacion pub : feed) {
            if (pub.getId().equals(idBuscado)) {
                encontrada = pub;
                break;
            }
        }

        if (encontrada != null) {
            System.out.println("\nPublicación encontrada:");
            encontrada.mostrarDetalle();
        } else {
            System.out.println("No se encontró una publicación con ID: " + idBuscado);
        }
    }

    private static void mostrarEstadisticas(ArrayList<Publicacion> feed){
        if (feed.isEmpty()) {
            System.out.println("El feed está vacío.");
            return;
        }
        // Contar publicaciones por tipo usando HashMap
        HashMap<String, Integer> conteo = new HashMap<>();
        int totalLikes = 0;

        for (Publicacion pub : feed) {
            // Obtener el nombre de la clase real del objeto
            String tipo = pub.getClass().getSimpleName(); // "Historia", "Reel" o "Post"
            conteo.put(tipo, conteo.getOrDefault(tipo, 0) + 1);
            totalLikes += pub.getLikes();
        }

        // Mostrar resultados
        System.out.println("\n--- ESTADÍSTICAS DEL FEED ---");
        System.out.println("Total de publicaciones: " + feed.size());
        System.out.println("Total de likes: " + totalLikes);

        if (!feed.isEmpty()) {
            double promedio = (double) totalLikes / feed.size();
            System.out.printf("Promedio de likes: %.1f%n", promedio);
        }

        System.out.println("\nPor tipo:");
        for (String tipo : conteo.keySet()) {
            System.out.println("  " + tipo + ": " + conteo.get(tipo));
        }
    }

    /* VALIDACION DE INPUTS */
    
    public static int validarEntero(Scanner scanner, String mensaje) {
        while (true) {
            try {
                System.out.println(mensaje);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Error: debe ingresar un número entero.");
            }
        }
    }

    static Autor solicitaAutor(Scanner scanner) {
        String nombreIngresado;
        while(true) {
            System.out.print("Ingrese su nombre: ");
            nombreIngresado = scanner.nextLine();

            if (Autor.validarNombreAutor(nombreIngresado)) { //esto se puede hacer porque el metodo validarNombreAutor es static
                break;
            } else {
                System.out.print("Intente nuevamente \n");
            }
        }

        Autor autor = new Autor(nombreIngresado);
        autor.almacenarAutores(autor);

        return autor;
    }

    static String solicitaString(Scanner scanner, String mensaje) {
        System.out.print(mensaje);
        String scan = scanner.nextLine().trim();

        return scan;
    }
}