package com.hachimakidev.techstore.view;

import com.hachimakidev.techstore.controller.DispositivoController;
import com.hachimakidev.techstore.model.Dispositivo;
import com.hachimakidev.techstore.model.Notebook;
import com.hachimakidev.techstore.model.Smartphone;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class CatalogoView {

    private TableView<Dispositivo> tablaDispositivos;
    private DispositivoController dispositivoController;

    public CatalogoView() {
        dispositivoController = new DispositivoController();
        configurarTabla();
        cargarCatalogo();
    }

    private void configurarTabla() {
        tablaDispositivos = new TableView<>();
        tablaDispositivos.setFixedCellSize(35); //alto de la fila
        tablaDispositivos.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN); //columnas autoajustables a la ventana

        TableColumn<Dispositivo, Integer> columnaId = new TableColumn<>("ID");
        columnaId.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Dispositivo, String> columnaTipo = new TableColumn<>("Tipo");
        columnaTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));

        TableColumn<Dispositivo, String> columnaMarca = new TableColumn<>("Marca");
        columnaMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));

        TableColumn<Dispositivo, String> columnaModelo = new TableColumn<>("Modelo");
        columnaModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));

        TableColumn<Dispositivo, Integer> columnaPrecio = new TableColumn<>("Precio");
        columnaPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));

        TableColumn<Dispositivo, String> columnaEspecificacion = new TableColumn<>("Especificación");
        columnaEspecificacion.setCellValueFactory(new PropertyValueFactory<>("especificacion"));

        TableColumn<Dispositivo, String> columnaDisponible = new TableColumn<>("Disponible");

        columnaDisponible.setCellValueFactory(datos -> {
            if (datos.getValue().isDisponible()) {
                return new ReadOnlyStringWrapper("Sí");
            } else {
                return new ReadOnlyStringWrapper("No");
            }
        });

        tablaDispositivos.getColumns().addAll(columnaId, columnaTipo, columnaMarca, columnaModelo, columnaPrecio, columnaEspecificacion, columnaDisponible);
    }

    private void cargarCatalogo() {
        ObservableList<Dispositivo> dispositivos = FXCollections.observableArrayList(dispositivoController.getDispositivos());
        tablaDispositivos.setItems(dispositivos);
    }

    public Scene catalogoScene() {
        VBox contenedorCatalogo = new VBox(tablaDispositivos);
        Scene escenaCatalogo = new Scene(contenedorCatalogo, 800, 600);
        return escenaCatalogo;
    }

    public VBox contenedorDispositivos(){
        Label lblTituloTabla = new Label("TechStore — Catálogo de Notebooks y Smartphones");
        lblTituloTabla.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        return new VBox(15, lblTituloTabla, tablaDispositivos, formularioNuevoDispositivo());
    }

    private VBox formularioNuevoDispositivo() {
        Label lblTitulo = new Label("Agregar nuevo dispositivo al inventario");
    
        ComboBox<String> tipo = new ComboBox<>();
        tipo.getItems().addAll("Notebook", "Smartphone");
        tipo.setValue("Notebook");
        
        //compartidos
        TextField txtMarca = new TextField();
        txtMarca.setPromptText("Marca (ej: Dell)");
        
        TextField txtModelo = new TextField();
        txtModelo.setPromptText("Modelo (ej: XPS 13)");

        TextField txtPrecio = new TextField();
        txtPrecio.setPromptText("Precio (ej: 990000)");

        //especificacion por defecto notebook
        TextField txtEspecificacion1 = new TextField();
        txtEspecificacion1.setPromptText("RAM GB (ej: 16)");

        TextField txtEspecificacion2 = new TextField();
        txtEspecificacion2.setPromptText("Procesador (ej: Core i7)");

        //al cambiar de tipo de dispositivo se cambia el campo de especificacion al que corresponda
        tipo.setOnAction(event -> {
            boolean esNotebook = tipo.getValue().equals("Notebook");
            txtEspecificacion1.clear();
            txtEspecificacion2.clear();
            if (esNotebook) {
                txtEspecificacion1.setPromptText("RAM GB (ej: 16)");
                txtEspecificacion2.setPromptText("Procesador (ej: Core i7)");
            } else {
                txtEspecificacion1.setPromptText("Almacenamiento GB (ej: 256)");
                txtEspecificacion2.setPromptText("Cámara MP (ej: 50)");
            }
        });

        Button btnAgregarDispositivo = new Button("Agregar Dispositivo");
        btnAgregarDispositivo.setOnAction(event -> {
            try {
                String tipoSeleccionado = tipo.getValue();
                String valorMarca = txtMarca.getText().trim();
                String valorModelo = txtModelo.getText().trim();
                int valorPrecio = Integer.parseInt(txtPrecio.getText().trim());

                Dispositivo dispositivoNuevo;

                if ("Notebook".equals(tipoSeleccionado)) {
                    int ramGb = Integer.parseInt(txtEspecificacion1.getText().trim());
                    String procesador = txtEspecificacion2.getText().trim();

                    if (ramGb <= 0) {
                        throw new IllegalArgumentException("La RAM debe ser mayor a cero.");
                    }

                    if (procesador.isBlank()) {
                        throw new IllegalArgumentException("Ingresa el procesador.");
                    }

                    dispositivoNuevo = new Notebook(0, valorMarca, valorModelo, valorPrecio, true, ramGb, procesador);

                } else {
                    int almacenamientoGb = Integer.parseInt(txtEspecificacion1.getText().trim());
                    int camaraMp = Integer.parseInt(txtEspecificacion2.getText().trim());

                    if (almacenamientoGb <= 0 || camaraMp <= 0) {
                        throw new IllegalArgumentException("El almacenamiento y los megapíxeles deben ser mayores que cero.");
                    }

                    dispositivoNuevo = new Smartphone(0, valorMarca, valorModelo, valorPrecio, true, almacenamientoGb, camaraMp);
                }
        
                dispositivoController.agregarDispositivo(dispositivoNuevo);
                tablaDispositivos.setItems(FXCollections.observableArrayList(dispositivoController.getDispositivos()));

                txtMarca.clear();
                txtModelo.clear();
                txtPrecio.clear();
                txtEspecificacion1.clear();
                txtEspecificacion2.clear();

            } catch (NumberFormatException e) {
                new Alert(Alert.AlertType.ERROR,"Completa con números enteros válidos.").showAndWait();
            } catch (IllegalArgumentException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).showAndWait();
            }
        });

        HBox primeraFila = new HBox(12, tipo, txtMarca, txtModelo, txtPrecio);
        primeraFila.setAlignment(Pos.CENTER_LEFT);

        HBox segundaFila = new HBox(12, txtEspecificacion1, txtEspecificacion2, btnAgregarDispositivo);
        segundaFila.setAlignment(Pos.CENTER_LEFT);

        VBox contenedorFormulario = new VBox(12, lblTitulo, primeraFila, segundaFila);

        contenedorFormulario.setPadding(new Insets(18));
        return contenedorFormulario;
    }
}

