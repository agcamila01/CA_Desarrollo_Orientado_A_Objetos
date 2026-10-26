
package com.camila.biblioapp.view;

import com.camila.biblioapp.controller.CatalogoController;
import com.camila.biblioapp.model.Material;
import com.camila.biblioapp.model.Libro;
import com.camila.biblioapp.model.Revista;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class CatalogoView {

    private CatalogoController controller = new CatalogoController();

    public VBox catalogo() {

        TableView<Material> tabla = new TableView<>();
        tabla.setPlaceholder(new Label("No hay materiales en el catálogo."));

        TableColumn<Material, String> colTipo = new TableColumn<>("Tipo");

        colTipo.setCellValueFactory(datos -> {
            Material material = datos.getValue();

            String tipo;
            if (material instanceof Libro) {
                tipo = "Libro";
            } else if (material instanceof Revista) {
                tipo = "Revista";
            } else {
                tipo = "Material";
            }

            return new ReadOnlyStringWrapper(tipo);
        });
        colTipo.setPrefWidth(90);

        TableColumn<Material, String> colTitulo = new TableColumn<>("Título");

        colTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        colTitulo.setPrefWidth(260);

        TableColumn<Material, Integer> colDias = new TableColumn<>("Días de Préstamo");

        colDias.setCellValueFactory(datos ->
            new ReadOnlyObjectWrapper<>(
                datos.getValue().calcularDiasPrestamo()
            )
        );
        colDias.setPrefWidth(130);

        TableColumn<Material, Integer> colStock = new TableColumn<>("Disponibles");

        colStock.setCellValueFactory(new PropertyValueFactory<>("cantidadDisponible"));
        colStock.setPrefWidth(110);

        tabla.getColumns().addAll(colTipo, colTitulo, colDias, colStock);

        tabla.setItems(FXCollections.observableArrayList(controller.obtenerCatalogo()));

        Label lblRut = new Label("RUT solicitante:");
        TextField txtRut = new TextField();
        txtRut.setPromptText("Ej: 12345678-9");
        Button btnPrestar = new Button("Prestar seleccionado");

        btnPrestar.setOnAction(e -> {

            Material seleccionado = tabla.getSelectionModel().getSelectedItem();

            if (seleccionado == null) {
                mostrarAlerta(Alert.AlertType.WARNING, "Material no seleccionado","Seleccione un libro o una revista.");
                return;
            }

            String rut = txtRut.getText().trim();

            if (rut.isEmpty()) {
                mostrarAlerta(Alert.AlertType.WARNING, "RUT requerido", "Ingrese el RUT del solicitante.");
                return;
            }

            try {
                String confirmacion = controller.prestar(rut, seleccionado);
                tabla.refresh();

                mostrarAlerta(Alert.AlertType.INFORMATION, "Préstamo exitoso", confirmacion);

            } catch (Exception ex) {
                tabla.refresh();
                mostrarAlerta(Alert.AlertType.WARNING, "No se pudo prestar", ex.getMessage() != null ? ex.getMessage() : "Ocurrió un error al procesar el préstamo.");
            }
        });

        HBox panelAcciones = new HBox(10, lblRut, txtRut, btnPrestar);
        panelAcciones.setPadding(new Insets(10, 0, 0, 0));

        VBox layout = new VBox(12, tabla, panelAcciones);
        layout.setPadding(new Insets(16));

        return layout;
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
