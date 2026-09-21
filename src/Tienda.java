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

    static ArrayList<Producto> coleccion_juegos = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);
    
    public static void main(String[] args) {
        mostrarMenu();
    }

    public static void mostrarMenu() {
        boolean mostrar_menu = true;
        
        while (mostrar_menu) {
            try {
                System.out.println("\n1. Registrar producto");
                System.out.println("2. Listar inventario");
                System.out.println("3. Buscar producto por nombre");
                System.out.println("4. Vender producto");
                System.out.println("5. Resumen del inventario");
                System.out.println("6. Insertar datos de prueba");
                System.out.println("7. Salir");

                System.out.print("Ingrese su opción: ");

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
                        resumenDelInventario();
                        break;
                    case "6":
                        insertarDatosPrueba();
                        break;
                    case "7":
                        mostrar_menu = false;
                        break;
                    default:
                        System.out.println("Opcion Inválida");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un entero");
            }
        }
    }

    /* MÉTODOS DEL MENU PRINCIPAL */
    public static void registrarProducto() {
        boolean mostrar_menu = true;

        while (mostrar_menu) {
            try {
                System.out.println("\n1. Registrar un Producto Físico");
                System.out.println("2. Registrar un Producto Digital");
                System.out.println("3. Volver al menú");
                System.out.print("Elija su opción: ");
                
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
                        System.out.println("Opcion Inválida");
                        break;
                }
            } catch (NumberFormatException e){
                System.out.println("Error de formato, ingrese un entero");
            }
        }
    }

    public static void registrarProductoFisico() {
        String nombre = validarString("Ingrese el nombre del juego: ");
        int precioBase = validarEntero("Ingrese precio base del juego: ");
        int stock = validarEntero("Ingrese stock del juego: ");

        int costoEnvio = validarEntero("Ingrese costo de envío del juego: ");

        ProductoFisico juegoFisico = new ProductoFisico(nombre, precioBase, stock, costoEnvio);
        coleccion_juegos.add(juegoFisico);
    }

    public static void registrarProductoDigital() {
        String nombre = validarString("Ingrese el nombre del juego: ");
        int precioBase = validarEntero("Ingrese precio base del juego: ");
        int stock = validarEntero("Ingrese stock del juego: ");

        int descuento = validarEntero("Ingrese descuento del juego: ");
        String plataforma = validarString("Ingrese la plataforma del juego: ");

        ProductoDigital juegoDigital = new ProductoDigital(nombre, precioBase, stock, descuento, plataforma);
        coleccion_juegos.add(juegoDigital);
    }

    public static void listarInventario() {
        if (coleccion_juegos.isEmpty()) {
            System.out.println("--- INVENTARIO DE JUEGOS VACÍO ----");
            return;
        }

        System.out.println("----- INVENTARIO DE JUEGOS FÍSICOS -----");
        for (Producto juego : coleccion_juegos) {
            if (juego instanceof ProductoFisico juegoFisico) {
                System.out.println("\n" + juego.mostrarInfo());
            }
        }
        System.out.println("----------------------------------------");

        System.out.println("----- INVENTARIO DE JUEGOS DIGITALES ----");
        for (Producto juego : coleccion_juegos) {
            if (juego instanceof ProductoDigital juegoDigital) {
                System.out.println("\n" + juego.mostrarInfo());
            }
        }
        System.out.println("-----------------------------------------");
    }

    public static void buscarProductoPorNombre() {
        String juegoUsuario = validarString("Indique nombre del juego a buscar: ");
        int index = 0;
        boolean encontrado = false;
        
        for (Producto juego : coleccion_juegos) {
            if (juego.getNombre().contains(juegoUsuario)){
                System.out.println("\n[" + index + "] " + juego.mostrarInfo() + "\n" + tipoProducto(juego));
                encontrado = true;
                System.out.println("-----------------------------------");
            }
            index++;
        }
        if (!encontrado) {
            System.out.println("\nSIN RESULTADOS");
        }
    }

    public static void venderProducto() {
        if (coleccion_juegos.isEmpty()){
            System.out.println("\nNO HAY JUEGOS DISPONIBLES");
            return;
        }

        System.out.println("\n----- JUEGOS DISPONIBLES -----");
        for (int i = 0; i < coleccion_juegos.size(); i++) {
            Producto juego = coleccion_juegos.get(i);
            System.out.println("\n[" + i + "] " + juego.mostrarInfo() + "\n" +tipoProducto(juego));
        }

        int eleccionJuego = validarEntero("\nIngrese el índice del juego a vender: ");
        if (!(eleccionJuego < coleccion_juegos.size())){
            System.out.println("Indice fuera de rango. Ingrese un índice entre 0 y " + coleccion_juegos.size());
            return;
        } else {
            System.out.println("\n---- JUEGO ELEGIDO ----");
            System.out.println(coleccion_juegos.get(eleccionJuego).mostrarInfo() + "\n" + tipoProducto(coleccion_juegos.get(eleccionJuego)));
            System.out.println("------------------------");
        }

        int cantidadVenta = validarEntero("\nIngrese cantidad de venta: ");
        for (int i = 0; i < coleccion_juegos.size(); i++) {
            Producto juego = coleccion_juegos.get(i);
            if (cantidadVenta <= juego.getStock()) {
                juego.setStock(juego.getStock() - cantidadVenta);
            } else {
                System.out.println("No hay stock sufiente para realizar la venta");
                return;
            }
        }

        System.out.println("Total a pagar: $" + coleccion_juegos.get(eleccionJuego).calcularPrecioFinal() * cantidadVenta);
    }

    public static void resumenDelInventario() {
        int contadorFisicos = 0;
        int contadorDigitales = 0;
        int valorTotalInventario = 0;

        for (Producto juego : coleccion_juegos) {
            if (juego instanceof ProductoFisico juegoFisico) {
                contadorFisicos++;
            } else {
                contadorDigitales++;
            }
            valorTotalInventario += (juego.calcularPrecioFinal() * juego.getStock());
        }

        System.out.println("\n--- RESUMEN DEL INVENTARIO ---");
        System.out.println("Total de productos: " + (contadorFisicos + contadorDigitales));
        System.out.println("Productos Físicos: " + contadorFisicos);
        System.out.println("Productos Digitales: " + contadorDigitales);
        System.out.println("Valor total del inventario: $" + valorTotalInventario);
    }

    public static void insertarDatosPrueba() {
        coleccion_juegos.add(new ProductoFisico("Pokemon Escudo", 45000, 10, 2500));
        coleccion_juegos.add(new ProductoFisico("Pokemon Espada", 42000, 26, 2500));
        coleccion_juegos.add(new ProductoFisico("Factorio", 18000, 60, 2500));
        coleccion_juegos.add(new ProductoFisico("PES 2017", 32000, 100, 300));

        coleccion_juegos.add(new ProductoDigital("Sonic Racing Crossworld", 17000, 25, 30, "PC"));
        coleccion_juegos.add(new ProductoDigital("Undertale", 20000, 66, 15, "Switch"));

        System.out.println("Datos de prueba insertados.");
    }

    private static String tipoProducto(Producto p) {
        if (p instanceof ProductoFisico pFisico){
            return "[FÍSICO]"; 
        } else {
            return "[DIGITAL]";
        }
    }

    /* VALIDACION DE DATOS */
    private static int validarEntero(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Error: debe ingresar un número entero.");
            }
        }
    }

    private static String validarString(String mensaje) {
        String entradaUsuario;
        while (true) {
            System.out.print(mensaje);
            entradaUsuario = sc.nextLine().trim();
            if (entradaUsuario.length() <= 2) {
                System.out.println("Este campo debe tener una longitud de 2 o más caracteres (no vacíos). Intente nuevamente.");
            } else {
                return entradaUsuario;
            }
            
        }
    }

}
