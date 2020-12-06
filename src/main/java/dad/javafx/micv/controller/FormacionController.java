package dad.javafx.micv.controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

import dad.javafx.micv.model.Titulo;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.util.converter.LocalDateStringConverter;

/**
 * @author Ayoze Amaro
 *
 */
public class FormacionController implements Initializable {

	// MODEL
	private ListProperty<Titulo> formaciones = new SimpleListProperty<Titulo>(FXCollections.observableArrayList());
	
	// VIEW
	@FXML
    private HBox view;
    @FXML
    private TableView<Titulo> tablaFor;
    @FXML
    private TableColumn<Titulo, LocalDate> desdeCol, hastaCol;
    @FXML
    private TableColumn<Titulo, String> denominacionCol, organizadorCol;
    @FXML
	private Button btnNuevo, btnEliminar;

    // CONSTRUCTOR
	public FormacionController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/FormacionView.fxml"));
		loader.setController(this);
		loader.load();	
	}
	
	// INITIALIZE
	public void initialize(URL location, ResourceBundle resources) { 
		
		formaciones.addListener((o, oldValue, newValue) -> onFormacionChanged(o, oldValue, newValue));

		desdeCol.setCellValueFactory(v -> v.getValue().desdeProperty());
		hastaCol.setCellValueFactory(v -> v.getValue().hastaProperty());
		denominacionCol.setCellValueFactory(v -> v.getValue().denominacionProperty());
		organizadorCol.setCellValueFactory(v -> v.getValue().organizadorProperty());

		desdeCol.setCellFactory(TextFieldTableCell.forTableColumn(new LocalDateStringConverter()));
		hastaCol.setCellFactory(TextFieldTableCell.forTableColumn(new LocalDateStringConverter()));
		denominacionCol.setCellFactory(TextFieldTableCell.forTableColumn());
		organizadorCol.setCellFactory(TextFieldTableCell.forTableColumn());
		
		btnEliminar.setDisable(true);
	}
	
    private void onFormacionChanged(ObservableValue<? extends ObservableList<Titulo>> o, ObservableList<Titulo> oldValue, ObservableList<Titulo> newValue) {
		
    	if (oldValue != null) {
    		tablaFor.setItems(null);
		}

		if (newValue != null) {
			tablaFor.setItems(newValue);
		}
	}

	@FXML
    private void onNuevo(ActionEvent event) { 
		Dialog<Titulo> dialog = new Dialog<Titulo>();
		dialog.setTitle("Nuevo título");
		
		ButtonType loginButtonType = new ButtonType("Crear", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(15, 150, 15, 15));

		TextField denominacionField = new TextField();
		TextField organizadorField = new TextField();
		DatePicker desdePicker = new DatePicker();
		DatePicker hastaPicker = new DatePicker();
		
		grid.addRow(0, new Label("Denominación: "), denominacionField);
		grid.addRow(1, new Label("Organizador: "), organizadorField);
		grid.addRow(2, new Label("Desde: "), desdePicker);
		grid.addRow(3, new Label("Hasta: "), hastaPicker);
		
		dialog.getDialogPane().setContent(grid);
		
		dialog.setResultConverter(dialogButton -> {
			if (dialogButton == loginButtonType) {
				Titulo titulo = new Titulo();
				
	    		titulo.setDenominacion(denominacionField.textProperty().get());
	    		titulo.setOrganizador(organizadorField.textProperty().get());
	    		titulo.setDesde(desdePicker.getValue());
	    		titulo.setHasta(hastaPicker.getValue());
	        	     	
	    		return titulo;
			}
			return null;
		});

		Optional<Titulo> result = dialog.showAndWait();
		if (result.isPresent()) {
			tablaFor.getItems().add(result.get());
			btnEliminar.setDisable(false);
		}
	}

    @FXML
    private void onEliminar(ActionEvent event) { 
    	Titulo titulo = tablaFor.getSelectionModel().getSelectedItem();

		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setHeaderText(null);
		alert.setTitle("Eliminar Título");
		alert.setContentText("¿Estás seguro de que quieres borrar este título?");
		
		Optional<ButtonType> action = alert.showAndWait();
		if (action.get() == ButtonType.OK) {
			tablaFor.getItems().remove(titulo);
		}
    }

    // SHOW VIEW
 	public HBox getView() {
 		return view;
 	}

	public final ListProperty<Titulo> formacionesProperty() {
		return this.formaciones;
	}
	
	public final ObservableList<Titulo> getFormaciones() {
		return this.formacionesProperty().get();
	}
	
	public final void setFormaciones(final ObservableList<Titulo> formaciones) {
		this.formacionesProperty().set(formaciones);
	}
}
