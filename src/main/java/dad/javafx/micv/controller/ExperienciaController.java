package dad.javafx.micv.controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

import dad.javafx.micv.model.Experiencia;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.util.converter.LocalDateStringConverter;

/**
 * @author Ayoze Amaro
 *
 */
public class ExperienciaController implements Initializable {
	
	// MODEL
	private ListProperty<Experiencia> experiencias = new SimpleListProperty<Experiencia>(FXCollections.observableArrayList());
	
	// VIEW
	@FXML
    private HBox view;
    @FXML
    private TableView<Experiencia> tablaExp;
    @FXML
    private TableColumn<Experiencia, LocalDate> desdeCol, hastaCol;
    @FXML
    private TableColumn<Experiencia, String> denominacionCol, empleadorCol;
    @FXML
	private Button btnNuevo, btnEliminar;
    
    // CONSTRUCTOR
    public ExperienciaController() throws IOException {
		FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/ExperienciaView.fxml"));
		loader.setController(this);
		loader.load();
	}

    // INITIALIZE
	@Override
	public void initialize(URL location, ResourceBundle resources) { 
		
		experiencias.addListener((o, oldValue, newValue) -> onExperienciaChanged(o, oldValue, newValue));

		desdeCol.setCellValueFactory(v -> v.getValue().desdeProperty());
		hastaCol.setCellValueFactory(v -> v.getValue().hastaProperty());
		denominacionCol.setCellValueFactory(v -> v.getValue().denominacionProperty());
		empleadorCol.setCellValueFactory(v -> v.getValue().empleadorProperty());

		desdeCol.setCellFactory(TextFieldTableCell.forTableColumn(new LocalDateStringConverter()));
		hastaCol.setCellFactory(TextFieldTableCell.forTableColumn(new LocalDateStringConverter()));
		denominacionCol.setCellFactory(TextFieldTableCell.forTableColumn());
		empleadorCol.setCellFactory(TextFieldTableCell.forTableColumn());
		
		btnEliminar.setDisable(true);
		 
	}
	
	private void onExperienciaChanged(ObservableValue<? extends ObservableList<Experiencia>> o, ObservableList<Experiencia> oldValue, ObservableList<Experiencia> newValue) {
		
    	if (oldValue != null) {
    		tablaExp.setItems(null);
		}

		if (newValue != null) {
			tablaExp.setItems(newValue);
		}
	}
    
	@FXML
    private void onNuevo(ActionEvent event) { 
		Dialog<Experiencia> dialog = new Dialog<Experiencia>();
		dialog.setTitle("Nueva experiencia");
		
		ButtonType loginButtonType = new ButtonType("Crear", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(15, 150, 15, 15));

		TextField denominacionField = new TextField();
		TextField empleadorField = new TextField();
		DatePicker desdePicker = new DatePicker();
		DatePicker hastaPicker = new DatePicker();
		
		grid.addRow(0, new Label("Denominación: "), denominacionField);
		grid.addRow(1, new Label("Empleador: "), empleadorField);
		grid.addRow(2, new Label("Desde: "), desdePicker);
		grid.addRow(3, new Label("Hasta: "), hastaPicker);
		
		dialog.getDialogPane().setContent(grid);
		
		dialog.setResultConverter(dialogButton -> {
			if (dialogButton == loginButtonType) {
				Experiencia experiencia = new Experiencia();
				experiencia.setDenominacion(denominacionField.textProperty().get());
				experiencia.setEmpleador(empleadorField.textProperty().get());
				experiencia.setDesde(desdePicker.getValue());
				experiencia.setHasta(hastaPicker.getValue());
	        	     	
	    		return experiencia;
			}
			return null;
		});

		Optional<Experiencia> result = dialog.showAndWait();
		if (result.isPresent()) {
			tablaExp.getItems().add(result.get());
			btnEliminar.setDisable(false);
		}
	}

    @FXML
    private void onEliminar(ActionEvent event) { 
    	Experiencia experiencia = tablaExp.getSelectionModel().getSelectedItem();

		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setHeaderText(null);
		alert.setTitle("Eliminar Experiencia");
		alert.setContentText("¿Estás seguro de que quieres borrar esta experiencia?");
		
		Optional<ButtonType> action = alert.showAndWait();
		if (action.get() == ButtonType.OK) {
			tablaExp.getItems().remove(experiencia);
		}
    }
    
    // SHOW VIEW
 	public HBox getView() {
     	return view;
     }
	
    public final ListProperty<Experiencia> experienciasProperty() {
		return this.experiencias;
	}
	
	public final ObservableList<Experiencia> getExperiencias() {
		return this.experienciasProperty().get();
	}
	
	public final void setExperiencias(final ObservableList<Experiencia> experiencias) {
		this.experienciasProperty().set(experiencias);
	}
}
