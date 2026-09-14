/*
 * Paradigma: Orientado a Objetos
 * 
 * Este programa utiliza el paradigma orientado a objetos (POO).
 * En POO, el código se organiza en clases que representan entidades
 * del mundo real, encapsulando datos (atributos) y comportamiento
 * (métodos) en una misma unidad.
 * 
 * Los 4 pilares son:
 * 1.Abstracción: se modelan entidades del mundo real como clases, ocultando
 * detalles internos y exponiendo solo lo necesario.
 * 2.Encapsulamiento: los datos(atributos) y comportamientos (métodos)
 * se agrupan dentro de clases. El acceso se controla con -, +, #
 * 3.Herencia: una clase puede dereivar de otra reutilizando y extendiendo su
 * comportamiento.
 * 4.Polimorfismo: objetos de distintas clases pueden responder al mismo método
 * de dormas diferentes, gracias a la sobreescritura
 *
 * Diferencias con Python funcional:
 * 1. No se declara el tipo de las variables, en java  se debe declarar el tipo 
 * de cada variable, parámetro y valor de retorno
 * En Java muchos errores se detectan antes de 
 * ejecutar el programa, mientras que en Python aparecen mientras se ejecuta
 * 2. El codigo se organiza principalmente en funciones, muchas veces un programa
 * es una secuencia de funciones. En java todo vive dentro de clases, no hay funciones
 * sueltas, el programa es un conjunto de entidades que interactuan.
 */

import java.util.ArrayList;
import java.util.Scanner;

public class Tienda {

    static ArrayList<ProductoFisico> coleccion_juegos_fisicos = new ArrayList<>();
    static ArrayList<ProductoDigital> coleccion_juegos_digitales = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);
    
    public static void main(String[] args) {
        mostrarMenu();
    }

    public static void mostrarMenu() {
        boolean mostrar_menu = true;
        
        while (mostrar_menu) {
            System.out.println("1. Registrar producto");
            System.out.println("2. Listar inventario");
            System.out.println("3. Buscar producto por nombre");
            System.out.println("4. Vender producto");
            System.out.println("5. Resumen del inventario");
            System.out.println("6. Insertar datos de prueba");
            System.out.println("7. Salir");

            System.out.println("Ingrese su opción: ");

            String opcion_menu = sc.nextLine();

            switch (opcion_menu) {
                case "1":
                    registrarProducto();
                    break;
                case "2":
                    listarInventario();
                    break;
                case "3":
                    buscarProductoPorNombre();
                    break;
                case "4":
                    venderProducto();
                    break;
                case "5":
                    
                    break;
                case "6":
                    insertarDatosPrueba();
                    break;
                case "7":
                    mostrar_menu = false;
                    break;
                default:
                    break;
            }
        }
    }

    public static int validarEntero(String mensaje) {
        while (true) {
            try {
                System.out.println(mensaje);
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Error: debe ingresar un número entero.");
            }
        }
    }

    public static void registrarProducto() {
        boolean mostrar_menu = true;

        while (mostrar_menu) {
            System.out.println("1. Registrar un Producto Físico");
            System.out.println("2. Registrar un Producto Digital");
            System.out.println("3. Volver al menú");
            System.out.println("Elija su opción: ");
            
            String opcion_registro_juego = sc.nextLine();

            switch (opcion_registro_juego) {
                case "1":
                    registrarProductoFisico();
                    break;
                case "2":
                    registrarProductoDigital();
                    break;
                case "3":
                    mostrar_menu = false;
                    break;
                default:
                    break;
            }
        }
    }

    public static void registrarProductoFisico() {
        System.out.println("Ingrese el nombre del juego: ");
        String nombre = sc.nextLine();

        int precioBase = validarEntero("Ingrese precio base del juego: ");

        int stock = validarEntero("Ingrese stock del juego: ");

        int costoEnvio = validarEntero("Ingrese costo de envío del juego: ");

        ProductoFisico juego = new ProductoFisico(nombre, precioBase, stock, costoEnvio);
        
        coleccion_juegos_fisicos.add(juego);
    }

    public static void registrarProductoDigital() {
        System.out.println("Ingrese el nombre del juego: ");
        String nombre = sc.nextLine();

        int precioBase = validarEntero("Ingrese precio base del juego: ");

        int stock = validarEntero("Ingrese stock del juego: ");

        int descuento = validarEntero("Ingrese descuento del juego: ");

        System.out.println("Ingrese la plataforma del juego: ");
        String plataforma = sc.nextLine();

        ProductoDigital juego = new ProductoDigital(nombre, precioBase, stock, descuento, plataforma);
        
        coleccion_juegos_digitales.add(juego);
    }

    public static void listarInventario() {
        System.out.println("==== INVENTARIO DE JUEGOS FÍSICOS =====");
        for (ProductoFisico productoFisico : coleccion_juegos_fisicos) {
            System.out.println(productoFisico.mostrarInfo());
        }
        // for (int i = 0; i < coleccion_juegos_fisicos.size(); i++) {
        //     System.out.println(i+1 + ") " + coleccion_juegos_fisicos.get(i).mostrarInfo());
        // }

        System.out.println("==== INVENTARIO DE JUEGOS DIGITALES =====");
        for (ProductoDigital productoDigital : coleccion_juegos_digitales) {
            System.out.println(productoDigital.mostrarInfo());
        }
        // for (int i = 0; i < coleccion_juegos_digitales.size(); i++) {
        //     System.out.println(i+1 + ") " + coleccion_juegos_digitales.get(i).mostrarInfo());
        // }
    }

    public static void insertarDatosPrueba() {
        coleccion_juegos_fisicos.add(new ProductoFisico("Pokemon Escudo", 45000, 10, 2500));
        coleccion_juegos_fisicos.add(new ProductoFisico("Pokemon Espada", 42000, 26, 2500));
        coleccion_juegos_fisicos.add(new ProductoFisico("Factorio", 18000, 60, 2500));
        coleccion_juegos_fisicos.add(new ProductoFisico("PES 2017", 32000, 100, 300));

        coleccion_juegos_digitales.add(new ProductoDigital("Sonic Racing Crossworld", 17000, 25, 30, "PC"));
        coleccion_juegos_digitales.add(new ProductoDigital("Undertale", 20000, 66, 15, "Switch"));

        System.out.println("Datos de prueba insertados.");
    }

    public static void buscarProductoPorNombre() {
        System.out.println("Indique nombre del juego a buscar: ");
        String juegoUsuario = sc.nextLine();
        
        for (ProductoFisico juegoFisico : coleccion_juegos_fisicos) {
            if (juegoFisico.getNombre().contains(juegoUsuario)) {
                System.out.println(juegoFisico.mostrarInfo()); 
            } 
        }

        for (ProductoDigital juegoDigital : coleccion_juegos_digitales) {
            if (juegoDigital.getNombre().contains(juegoUsuario)) {
                System.out.println(juegoDigital.mostrarInfo());
            }
        }
        
    }


    /*
    El sistema lista los productos con su índice. El usuario ingresa el número del producto y la cantidad a vender. El sistema valida:
    Que el número de producto sea válido (exista en la lista).
    Que la cantidad a vender no sea mayor al stock disponible.
    Que la cantidad sea un número positivo.
    Si la venta es válida, descuenta el stock y muestra el total de la venta (cantidad × precio final del producto).
    
    */

    public static void venderProducto() {
        listarInventario();
        int eleccionProducto = validarEntero("¿Quiere un juego digital o juego físico? (1/2): ");
        switch (eleccionProducto) {
            case 1:
                for (int i = 0; i < coleccion_juegos_fisicos.size(); i++) {
                    System.out.println(i+1 + ") " + coleccion_juegos_fisicos.get(i).mostrarInfo());
                }
                break;
            case 2:
                for (int i = 0; i < coleccion_juegos_digitales.size(); i++) {
                    System.out.println(i+1 + ") " + coleccion_juegos_digitales.get(i).mostrarInfo());
                }
                break;
            default:
                System.out.println("Elije bien");
                break;
        }
        int eleccionJuegoUsuario = validarEntero("¿Qué juego quiere comprar?");
        int copiasJuego = validarEntero("¿Cuántas copias quiere?");

        


    }

}
