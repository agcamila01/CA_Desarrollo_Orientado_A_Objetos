package com.camila.biblioapp.view;

import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

public class HomeView {

    public void home(Stage stage) {
        TabPane tabPane = new TabPane();

        Tab tabUsuarios = new Tab("Usuarios", new UserDataView().userData());
        tabUsuarios.setClosable(false);

        Tab tabCatalogo = new Tab("Catálogo y Préstamos", new CatalogoView().catalogo());
        tabCatalogo.setClosable(false);

        tabPane.getTabs().addAll(tabUsuarios, tabCatalogo);

        Scene scene = new Scene(tabPane, 750, 460);
        stage.setTitle("BiblioTech — Sistema de Gestión");
        stage.setScene(scene);
        stage.show();
    }
}