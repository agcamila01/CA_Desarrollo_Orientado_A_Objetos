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
            System.out.println("6. Salir");

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
                    
                    break;
                case "4":
                    
                    break;
                case "5":
                    
                    break;
                case "6":
                    mostrar_menu = false;
                    break;
                default:
                    break;
            }
        }
    }

    static public void registrarProducto(){
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

    static public void registrarProductoFisico(){
        System.out.println("Ingrese el nombre del juego: ");
        String nombre = sc.nextLine();

        System.out.println("Ingrese precio base del juego: ");
        int precioBase = Integer.parseInt(sc.nextLine());

        System.out.println("Ingrese stock del juego: ");
        int stock = Integer.parseInt(sc.nextLine());

        System.out.println("Ingrese costo de envío del juego: ");
        int costoEnvio = Integer.parseInt(sc.nextLine());

        ProductoFisico juego = new ProductoFisico(nombre, precioBase, stock, costoEnvio);
        
        coleccion_juegos_fisicos.add(juego);
    }

    static public void registrarProductoDigital(){
        System.out.println("Ingrese el nombre del juego: ");
        String nombre = sc.nextLine();

        System.out.println("Ingrese precio base del juego: ");
        int precioBase = Integer.parseInt(sc.nextLine());

        System.out.println("Ingrese el stock del juego: ");
        int stock = Integer.parseInt(sc.nextLine());

        System.out.println("Ingrese descuento del juego: ");
        int descuento = Integer.parseInt(sc.nextLine());

        System.out.println("Ingrese la plataforma del juego: ");
        String plataforma = sc.nextLine();

        ProductoDigital juego = new ProductoDigital(nombre, precioBase, stock, descuento, plataforma);
        
        coleccion_juegos_digitales.add(juego);
    }

    static public void listarInventario(){
        System.out.println("==== LISTADO DE JUEGOS FÍSICOS =====");
        for (ProductoFisico productoFisico : coleccion_juegos_fisicos) {
            System.out.println(productoFisico.mostrarInfo());
        }

        System.out.println("==== LISTADO DE JUEGOS DIGITALES =====");
        for (ProductoDigital productoDigital : coleccion_juegos_digitales) {
            System.out.println(productoDigital.mostrarInfo());
        }
    }


}
