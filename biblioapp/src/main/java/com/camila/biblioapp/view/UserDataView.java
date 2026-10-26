
package com.camila.biblioapp.view;

import com.camila.biblioapp.controller.UserController;
import com.camila.biblioapp.model.User;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class UserDataView {

    public VBox userData() {

        Label lbl_user_rut = new Label("RUT:");
        TextField txf_user_rut = new TextField();
        txf_user_rut.setPromptText("Ej: 11.111.111-1");

        Button btn_search_user = new Button("Buscar");

        HBox hbox_search_user = new HBox(
            10, lbl_user_rut, txf_user_rut, btn_search_user
        );

        Label lbl_user_data_rut = new Label("RUT: ______________");
        Label lbl_user_data_nombre = new Label("Nombre: ______________");
        Label lbl_user_data_email = new Label("Email: ______________");
        Label lbl_user_data_edad = new Label("Edad: ______________");
        Label lbl_user_data_puede_pedir = new Label("¿Puede solicitar libros?: ______________");
        Label lbl_mensaje = new Label();

        VBox vbox_user_data = new VBox(5, lbl_user_data_rut, lbl_user_data_nombre, lbl_user_data_email, lbl_user_data_edad, lbl_user_data_puede_pedir, lbl_mensaje);

        VBox vbox_home = new VBox(30, hbox_search_user, vbox_user_data);
        vbox_home.setStyle("-fx-padding: 20;");

        btn_search_user.setOnAction(e -> {

            String rut = txf_user_rut.getText().trim();

            if (rut.isEmpty()) {
                lbl_mensaje.setText("Ingrese un RUT para buscar.");
                return;
            }

            UserController userController = new UserController();
            User usuario = userController.buscarUsuario(rut);

            if (usuario == null) {
                lbl_user_data_rut.setText("RUT: ______________");
                lbl_user_data_nombre.setText("Nombre: ______________");
                lbl_user_data_email.setText("Email: ______________");
                lbl_user_data_edad.setText("Edad: ______________");
                lbl_user_data_puede_pedir.setText("¿Puede solicitar libros?: ______________");

                lbl_mensaje.setText("No se encontró un usuario con ese RUT.");
                return;
            }

            lbl_user_data_rut.setText("RUT: " + usuario.getRut());
            lbl_user_data_nombre.setText("Nombre: " + usuario.getNombre());
            lbl_user_data_email.setText("Email: " + usuario.getEmail());
            lbl_user_data_edad.setText("Edad: " + usuario.getEdad());
            lbl_user_data_puede_pedir.setText("¿Puede solicitar libros?: " + usuario.isPuede_solicitar_prestamos());

            //lbl_mensaje.setText("Usuario encontrado.");
        });

        txf_user_rut.setOnAction(e -> btn_search_user.fire());
        return vbox_home;
    }
}

