package com.video.juegos;

import com.video.juegos.view.GameVaultView;
import com.video.juegos.view.HomeView;
import com.video.juegos.controller.InventarioController;
import com.video.juegos.controller.UserController;
import com.video.juegos.dao.JsonUsuarioDAO;
import com.video.juegos.dao.JsonVideojuegoDAO;
import com.video.juegos.dao.UsuarioDAO;
import com.video.juegos.dao.VideojuegoDAO;
import com.video.juegos.exception.AccesoDatosException;
import com.video.juegos.repository.UsuarioRepository;
import com.video.juegos.repository.VideojuegoRepository;
import com.video.juegos.service.InventarioService;
import com.video.juegos.service.UsuarioService;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class GameVault extends Application {
    @Override
    public void start(Stage stage) {
        try {
            // 1. Fuente de datos: solo aquí elegimos la implementación JSON.
            VideojuegoDAO videojuegoDAO = new JsonVideojuegoDAO();
            UsuarioDAO usuarioDAO = new JsonUsuarioDAO();

            // 2. Los Repository son clases separadas que utilizan las interfaces DAO.
            VideojuegoRepository videojuegos = new VideojuegoRepository(videojuegoDAO);
            UsuarioRepository usuarios = new UsuarioRepository(usuarioDAO);

            // 3. Los servicios concentran las validaciones.
            InventarioService inventarioService = new InventarioService(videojuegos);
            UsuarioService usuarioService = new UsuarioService(usuarios);

            // 4. Los controladores reciben los servicios; las vistas reciben los controladores.
            InventarioController inventarioController = new InventarioController(inventarioService);
            UserController userController = new UserController(usuarioService);
            HomeView homeView = new HomeView(userController);
            GameVaultView gameVaultView = new GameVaultView(inventarioController);

            BorderPane contenedor = new BorderPane();
            contenedor.setTop(homeView.contenedorBusquedaUsuario());
            contenedor.setCenter(gameVaultView.contenedorGameVault());
            stage.setScene(new Scene(contenedor, 1000, 650));
            stage.setTitle("GameVault");
            stage.show();
        } catch (AccesoDatosException error) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("GameVault");
            alerta.setHeaderText("No se pudieron cargar los datos");
            alerta.setContentText(error.getMessage());
            alerta.showAndWait();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
