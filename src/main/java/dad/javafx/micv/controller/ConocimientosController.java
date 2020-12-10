package dad.javafx.micv.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import dad.javafx.micv.model.Conocimientos;
import dad.javafx.micv.model.Idioma;
import dad.javafx.micv.model.Nivel;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * @author Ayoze Amaro
 *
 */
public class ConocimientosController implements Initializable {

	// MODEL
	private ListProperty<Conocimientos> habilidades = new SimpleListProperty<Conocimientos>(FXCollections.observableArrayList());
	
	// VIEW
	@FXML
	private HBox view;
	@FXML
	private TableView<Conocimientos> tablaCon;
	@FXML
	private TableColumn<Conocimientos, String> denominacionColumn, nivelColumn;
	@FXML
	private Button btnConocimiento, btnNuevo, btnEliminar;
	
	// CONSTRUCTOR
	public ConocimientosController() throws IOException {
		FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/ConocimientosView.fxml"));
		loader.setController(this);
		loader.load();
	}

	// INITIALIZE
	@Override
	public void initialize(URL location, ResourceBundle resources) { 
		
		habilidades.addListener((o, oldValue, newValue) -> onConocimientoChanged(o, oldValue, newValue));
		
		denominacionColumn.setCellValueFactory(v -> v.getValue().denominacionProperty());
		nivelColumn.setCellValueFactory(v -> v.getValue().nivelProperty());

		denominacionColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		nivelColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		
		btnEliminar.setDisable(true);
	}
    
	private void onConocimientoChanged(ObservableValue<? extends ObservableList<Conocimientos>> o, ObservableList<Conocimientos> oldValue, ObservableList<Conocimientos> newValue) {
		
		if (oldValue != null) {
			tablaCon.setItems(null);
		}

		if (newValue != null) {
			tablaCon.setItems(newValue);
		}
	}

	@FXML
    private void onNuevoConocimiento(ActionEvent event) { 
		Dialog<Conocimientos> tab = new Dialog<Conocimientos>();
		tab.setTitle("Nuevo conocimiento");
		
		ButtonType createButton = new ButtonType("Crear", ButtonData.OK_DONE);
		tab.getDialogPane().getButtonTypes().addAll(createButton, ButtonType.CANCEL);

		Stage stage = (Stage) tab.getDialogPane().getScene().getWindow();
		stage.getIcons().add(new Image(this.getClass().getResource("/images/cv64x64.png").toString()));
		
		// CREATE SCENE TAB WITH GRIDPANE
		GridPane scene = new GridPane();
		scene.setHgap(10);
		scene.setVgap(10);
		scene.setPadding(new Insets(15, 150, 15, 15));

		TextField denominacionField = new TextField();
		ComboBox tipoConocimiento = new ComboBox();
		tipoConocimiento.getItems().addAll(Nivel.values());
		Button btnReset = new Button("X");
		btnReset.setOnAction(d -> {
			tipoConocimiento.getSelectionModel().clearSelection();
		});
		
		scene.addRow(0, new Label("Denominación: "), denominacionField);
		scene.addRow(1, new Label("Nivel: "), tipoConocimiento, btnReset);

		tab.getDialogPane().setContent(scene);
		
		tab.setResultConverter(tabButton -> {
			if (tabButton == createButton) {
				Conocimientos conocimiento = new Conocimientos();
				
				conocimiento.setDenominacion(denominacionField.textProperty().get());
				conocimiento.setNivel(tipoConocimiento.getSelectionModel().getSelectedItem().toString());
	        	
	    		return conocimiento;
			}
			return null;
		});

		Optional<Conocimientos> result = tab.showAndWait();
		if (result.isPresent()) {
			tablaCon.getItems().add(result.get());
			btnEliminar.setDisable(false);
		}
	}

	@FXML
    private void onNuevoIdioma(ActionEvent event) { 
		Dialog<Idioma> tab = new Dialog<Idioma>();
		tab.setTitle("Nuevo idioma");
		
		ButtonType createButton = new ButtonType("Crear", ButtonData.OK_DONE);
		tab.getDialogPane().getButtonTypes().addAll(createButton, ButtonType.CANCEL);

		Stage stage = (Stage) tab.getDialogPane().getScene().getWindow();
		stage.getIcons().add(new Image(this.getClass().getResource("/images/cv64x64.png").toString()));
		
		// CREATE SCENE TAB WITH GRIDPANE
		GridPane scene = new GridPane();
		scene.setHgap(10);
		scene.setVgap(10);
		scene.setPadding(new Insets(15, 150, 15, 15));

		TextField denominacionField = new TextField();
		ComboBox tipoIdioma = new ComboBox();
		tipoIdioma.getItems().addAll(Nivel.values());
		Button btnReset = new Button("X");
		btnReset.setOnAction(d -> {
			tipoIdioma.getSelectionModel().clearSelection();
		});
		TextField certificacionField = new TextField();
		
		scene.addRow(0, new Label("Denominación: "), denominacionField);
		scene.addRow(1, new Label("Nivel: "), tipoIdioma, btnReset);
		scene.addRow(2, new Label("Certificación: "), certificacionField);

		tab.getDialogPane().setContent(scene);
		
		tab.setResultConverter(tabButton -> {
			if (tabButton == createButton) {
				Idioma newIdioma = new Idioma();
				
				newIdioma.setDenominacion(denominacionField.textProperty().get());
				newIdioma.setNivel(tipoIdioma.getSelectionModel().getSelectedItem().toString());
				newIdioma.setCertificacion(certificacionField.textProperty().get());
	        	
	    		return newIdioma;
			}
			return null;
		});

		Optional<Idioma> result = tab.showAndWait();
		if (result.isPresent()) {
			tablaCon.getItems().add(result.get());
			btnEliminar.setDisable(false);
		}
	}
	
    @FXML
    private void onEliminar(ActionEvent event) { 
    	Conocimientos lastKnowledge = tablaCon.getSelectionModel().getSelectedItem();

		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		((Stage)alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image("/images/cv64x64.png"));
		alert.setHeaderText(null);
		alert.setTitle("Eliminar Conocimiento");
		alert.setContentText("¿Estas seguro de que quieres borrar este conocimiento?");
		
		Optional<ButtonType> action = alert.showAndWait();
		if (action.get() == ButtonType.OK) {
			tablaCon.getItems().remove(lastKnowledge);
		}
		
		if (tablaCon.getSelectionModel().getSelectedItem() == null) {
    		btnEliminar.setDisable(true);
		}
    }

    // SHOW VIEW
 	public HBox getView() {
     	return view;
     }
 	
	public final ListProperty<Conocimientos> habilidadesProperty() {
		return this.habilidades;
	}
	
	public final ObservableList<Conocimientos> getHabilidades() {
		return this.habilidadesProperty().get();
	}
	
	public final void setHabilidades(final ObservableList<Conocimientos> habilidades) {
		this.habilidadesProperty().set(habilidades);
	}
}
