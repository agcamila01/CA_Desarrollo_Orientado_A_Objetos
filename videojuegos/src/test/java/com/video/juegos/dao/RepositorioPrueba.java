package com.video.juegos.dao;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import com.video.juegos.controller.InventarioController;
import com.video.juegos.controller.UserController;
import com.video.juegos.exception.AccesoDatosException;
import com.video.juegos.model.JuegoDigital;
import com.video.juegos.model.JuegoFisico;
import com.video.juegos.model.Usuario;
import com.video.juegos.model.Videojuego;
import com.video.juegos.repository.UsuarioRepository;
import com.video.juegos.repository.VideojuegoRepository;
import com.video.juegos.service.InventarioService;
import com.video.juegos.service.UsuarioService;

// Prueba ejecutable sin JUnit, ventanas ni acceso a las portadas remotas.
public class RepositorioPrueba {
    private static int comprobaciones;

    public static void main(String[] args) throws Exception {
        Path raiz = Path.of(args[0]).toAbsolutePath().normalize();
        Path carpeta = Files.createTempDirectory(raiz.resolve("target"), "prueba-dao-");
        Path inventario = carpeta.resolve("datos/inventario.json");
        Path usuarios = carpeta.resolve("datos/usuarios.json");
        Path invalido = carpeta.resolve("invalido.json");

        try {
            JsonVideojuegoDAO juegosDAO = new JsonVideojuegoDAO(inventario);
            VideojuegoRepository repositorio = new VideojuegoRepository(juegosDAO);
            InventarioService servicio = new InventarioService(repositorio);
            InventarioController controlador = new InventarioController(servicio);

            ArrayList<Videojuego> iniciales = controlador.getInventario();
            comprobar(iniciales.size() == 4, "Carga inicial a través de todas las capas");
            comprobar(!Files.exists(inventario), "Consultar no escribe archivos");
            comprobar(controlador.getInventario().size() == 4, "Repetir la carga no duplica juegos");
            comprobar(iniciales.get(0) instanceof JuegoFisico, "Reconoce la subclase física");
            comprobar(iniciales.get(1) instanceof JuegoDigital, "Reconoce la subclase digital");
            comprobar(((JuegoFisico) iniciales.get(0)).getStockTienda() == 14,
                    "Conserva los atributos físicos iniciales");
            comprobar(((JuegoDigital) iniciales.get(1)).getTamanoGB() == 70,
                    "Conserva los atributos digitales iniciales");
            comprobar(controlador.buscarPorTitulo(" HOLLOW KNIGHT ") != null,
                    "Búsqueda por título desde el controlador");
            comprobar(controlador.buscarPorTitulo("No existe") == null, "Título no encontrado");
            comprobar(controlador.buscarPorPlataforma(" playstation 5 ").size() == 1,
                    "El Repository filtra la colección por plataforma");
            esperarError(IllegalArgumentException.class, () -> controlador.buscarPorTitulo(" "),
                    "El Service rechaza una búsqueda vacía");
            esperarError(IllegalArgumentException.class,
                    () -> controlador.guardar(new JuegoFisico("", "PC", "", 1, 10)),
                    "El Service rechaza guardar sin título");
            comprobar(!Files.exists(inventario), "Una validación fallida no crea el archivo");

            controlador.guardar(new JuegoFisico("Aventura física ñ", "Switch", "fisico.jpg", 3, 80));
            controlador.guardar(new JuegoDigital("Aventura digital", "PC", "digital.jpg", 12.5, "ABC-123"));
            JsonVideojuegoDAO reabierto = new JsonVideojuegoDAO(inventario);
            comprobar(reabierto.obtenerTodos().size() == 6, "Guardar persiste y conserva juegos anteriores");
            JuegoFisico fisico = (JuegoFisico) reabierto.buscarPorTitulo("Aventura física ñ");
            comprobar(fisico.getStockTienda() == 3 && fisico.getPesoGramos() == 80
                    && fisico.getPortada().equals("fisico.jpg"), "Recupera campos físicos y acentos");
            JuegoDigital digital = (JuegoDigital) reabierto.buscarPorTitulo("Aventura digital");
            comprobar(digital.getTamanoGB() == 12.5 && digital.getCodigoActivacion().equals("ABC-123")
                    && digital.getPlataforma().equals("PC") && digital.getPortada().equals("digital.jpg"),
                    "Recupera campos digitales y comunes al reabrir");

            UsuarioRepository socios = new UsuarioRepository(new JsonUsuarioDAO(usuarios));
            UsuarioService usuarioService = new UsuarioService(socios);
            UserController userController = new UserController(usuarioService);
            comprobar(socios.obtenerTodos().size() == 3, "Carga los socios iniciales");
            comprobar(userController.buscarUsuario(" 18.111.222-3 ").getNombre().equals("Diego Alarcón"),
                    "Búsqueda de socio desde todas las capas");
            comprobar(userController.buscarUsuario("no existe") == null, "Socio no encontrado");
            comprobar(usuarioService.obtenerPorNivel(" plata ").size() == 1,
                    "El Repository filtra socios por nivel gamer");
            esperarError(IllegalArgumentException.class, () -> userController.buscarUsuario(""),
                    "El Service valida el RUT vacío");
            userController.guardar(new Usuario("11.111.111-1", "María Ñ", "maria@example.com", "Plata"));
            Usuario recuperado = new JsonUsuarioDAO(usuarios).buscarPorRut("11.111.111-1");
            comprobar(recuperado.getNombre().equals("María Ñ")
                    && recuperado.getEmail().equals("maria@example.com")
                    && recuperado.getNivelGamer().equals("Plata"), "Persistencia de socios en UTF-8");
            esperarError(IllegalArgumentException.class,
                    () -> userController.guardar(new Usuario("11.111.111-1", "Otro", "x@example.com", "Plata")),
                    "El Service rechaza un RUT repetido");
            comprobar(socios.obtenerTodos().size() == 4, "El rechazo no altera los datos guardados");

            // Se sustituye el DAO JSON por uno en memoria, sin cambiar Repository ni Service.
            ArrayList<Videojuego> memoria = new ArrayList<>();
            VideojuegoDAO daoMemoria = new VideojuegoDAO() {
                public ArrayList<Videojuego> obtenerTodos() { return new ArrayList<>(memoria); }
                public Videojuego buscarPorTitulo(String titulo) {
                    return memoria.stream().filter(j -> j.getTitulo().equals(titulo)).findFirst().orElse(null);
                }
                public void guardar(Videojuego juego) { memoria.add(juego); }
            };
            InventarioController otroControlador = new InventarioController(
                    new InventarioService(new VideojuegoRepository(daoMemoria)));
            otroControlador.guardar(digital);
            comprobar(otroControlador.getInventario().size() == 1
                    && otroControlador.buscarPorTitulo("Aventura digital") == digital,
                    "Se puede cambiar el DAO sin modificar las capas superiores");

            Files.writeString(invalido, "{", StandardCharsets.UTF_8);
            JsonVideojuegoDAO daoInvalido = new JsonVideojuegoDAO(invalido);
            esperarError(AccesoDatosException.class, daoInvalido::obtenerTodos,
                    "Un JSON mal formado informa un error de acceso a datos");
            esperarError(AccesoDatosException.class, () -> daoInvalido.guardar(digital),
                    "Guardar no sobrescribe un JSON mal formado");
            comprobar(Files.readString(invalido).equals("{"), "El archivo inválido queda intacto");
            Files.writeString(invalido, "[{\"tipo\":\"otro\"}]", StandardCharsets.UTF_8);
            esperarError(AccesoDatosException.class, daoInvalido::obtenerTodos, "Rechaza tipos desconocidos");
            Files.writeString(invalido, "[{}]", StandardCharsets.UTF_8);
            esperarError(AccesoDatosException.class, daoInvalido::obtenerTodos, "Informa registros sin tipo");
            esperarError(AccesoDatosException.class,
                    () -> new JsonUsuarioDAO(invalido).obtenerTodos(), "Informa socios sin RUT");
            Files.writeString(invalido, "null", StandardCharsets.UTF_8);
            esperarError(AccesoDatosException.class, daoInvalido::obtenerTodos, "Informa un JSON nulo");

            System.out.println("Resultado: " + comprobaciones + " comprobaciones aprobadas.");
        } finally {
            Files.deleteIfExists(inventario);
            Files.deleteIfExists(usuarios);
            Files.deleteIfExists(invalido);
            Files.deleteIfExists(carpeta.resolve("datos"));
            Files.deleteIfExists(carpeta);
        }
    }

    private static void comprobar(boolean condicion, String descripcion) {
        if (!condicion) {
            throw new AssertionError(descripcion);
        }
        comprobaciones++;
        System.out.println("OK: " + descripcion);
    }

    private static void esperarError(Class<? extends RuntimeException> tipo, Runnable operacion,
            String descripcion) {
        try {
            operacion.run();
        } catch (RuntimeException error) {
            comprobar(tipo.isInstance(error), descripcion);
            return;
        }
        throw new AssertionError(descripcion);
    }
}
