package dad.javafx.micv.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import dad.javafx.micv.model.Contacto;
import dad.javafx.micv.model.Email;
import dad.javafx.micv.model.Telefono;
import dad.javafx.micv.model.TipoTelefono;
import dad.javafx.micv.model.Web;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.cell.ChoiceBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

/**
 * @author Ayoze Amaro
 *
 */
public class ContactoController implements Initializable {

	// MODEL
	private ObjectProperty<Contacto> contacto = new SimpleObjectProperty<>();
	
	// VIEW
	@FXML
    private SplitPane view;
    @FXML
    private TableView<Telefono> telefonosTable;
    @FXML
    private TableView<Email> correoTable;
    @FXML
    private TableView<Web> webTable;
    @FXML
    private TableColumn<Telefono, String> numeroColumn, tipoColumn;
    @FXML
    private TableColumn<Email, String> emailColumn;
    @FXML
    private TableColumn<Web, String> webColumn;
    @FXML
	private Button btnNuevoTlfn, btnEliminarTlfn, btnNuevoCorreo, btnEliminarCorreo, btnNuevaWeb, btnEliminarWeb;

    // CONSTRUCTOR
	public ContactoController() throws IOException {
		FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/ContactoView.fxml"));
		loader.setController(this);
		loader.load();	
	}

	// INITIALIZE
	public void initialize(URL location, ResourceBundle resources) {
		
		contacto.addListener((o, oldValue, newValue) -> onContactoChanged(o, oldValue, newValue));

		numeroColumn.setCellValueFactory(new PropertyValueFactory<Telefono, String>("numero"));
		numeroColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		tipoColumn.setCellValueFactory(new PropertyValueFactory<Telefono, String>("tipo"));
		tipoColumn.setCellFactory(ChoiceBoxTableCell.forTableColumn("Fijo", "Móvil", "Empresa"));
		emailColumn.setCellValueFactory(new PropertyValueFactory<Email, String>("direccion"));
		emailColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		webColumn.setCellValueFactory(new PropertyValueFactory<Web, String>("url") );
		webColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		
		btnEliminarTlfn.setDisable(true);
		btnEliminarCorreo.setDisable(true);
		btnEliminarWeb.setDisable(true);
	}
	

    private void onContactoChanged(ObservableValue<? extends Contacto> o, Contacto oldValue, Contacto newValue) {

    	if (oldValue != null) {
    		telefonosTable.itemsProperty().unbindBidirectional(oldValue.telefonosProperty());
			correoTable.itemsProperty().unbindBidirectional(oldValue.emailsProperty());
			webTable.itemsProperty().unbindBidirectional(oldValue.websProperty());
		}

		if (newValue != null) {
			telefonosTable.itemsProperty().bindBidirectional(newValue.telefonosProperty());
			correoTable.itemsProperty().bindBidirectional(newValue.emailsProperty());
			webTable.itemsProperty().bindBidirectional(newValue.websProperty());
		}
	}

	@FXML
    void onNuevoTelefono(ActionEvent e) { 
		Dialog<Pair<String, String>> tab = new Dialog<>();
		tab.setTitle("Nuevo teléfono");
		tab.setHeaderText("Introduzca el número de teléfono");

		ButtonType createButton = new ButtonType("Añadir", ButtonData.OK_DONE);
		tab.getDialogPane().getButtonTypes().addAll(createButton, ButtonType.CANCEL);

		// CREATE SCENE TAB WITH GRIDPANE
		GridPane scene = new GridPane();
		scene.setHgap(10);
		scene.setVgap(10);
		scene.setPadding(new Insets(15, 150, 15, 15));

		TextField tlfnField = new TextField();
		tlfnField.setPromptText("Número de teléfono");
		ComboBox tipoTlfnBox = new ComboBox();
		tipoTlfnBox.setPromptText("Seleccione un tipo");
		tipoTlfnBox.getItems().addAll(TipoTelefono.values());

		scene.addRow(0, new Label("Número: "), tlfnField);
		scene.addRow(1, new Label("Tipo: "), tipoTlfnBox);

		tab.getDialogPane().setContent(scene);
		
		tab.setResultConverter(tabButton -> {
			if (tabButton == createButton) {
				String tlfnSelected = tlfnField.getText().toString();
				String tipoSelected = tipoTlfnBox.getSelectionModel().getSelectedItem().toString();
	
				Pair<String, String> tlfn = new Pair<String, String>(tlfnSelected, tipoSelected);
	
				return tlfn;
	        	     	
			}
			return null;
		});

		Optional<Pair<String, String>> result = tab.showAndWait();
		if (result.isPresent()) {
			Telefono insert = new Telefono(result.get().getKey(), result.get().getValue().toString());

			telefonosTable.getItems().add(insert);
			btnEliminarTlfn.setDisable(false);
		}
	}
    
    @FXML
    void onEliminarTelefono(ActionEvent e) { 
    	Telefono lastTelephone = (Telefono) telefonosTable.getSelectionModel().getSelectedItem();
    	telefonosTable.getItems().remove(lastTelephone);
    	if (telefonosTable.getSelectionModel().getSelectedItem() == null) {
    		btnEliminarTlfn.setDisable(true);
		}
    }

    @FXML
    void onNuevoCorreo(ActionEvent e) { 
    	TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Nuevo e-mail");
		dialog.setHeaderText("Crear una nueva dirección de correo");
		dialog.setContentText("Email:");

		Optional<String> result = dialog.showAndWait();

		result.ifPresent(newEmail -> { 
			correoTable.getItems().add(new Email(newEmail));
			btnEliminarCorreo.setDisable(false);
		});
    }
    
    @FXML
    void onEliminarCorreo(ActionEvent e) { 
    	Email lastEmail = correoTable.getSelectionModel().getSelectedItem();
    	correoTable.getItems().remove(lastEmail);
    	if (correoTable.getSelectionModel().getSelectedItem() == null) {
    		btnEliminarCorreo.setDisable(true);
		}
    }

    @FXML
    void onNuevoWeb(ActionEvent e) { 
    	TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Nuevo web");
		dialog.setHeaderText("Crear una nueva dirección web");
		dialog.setContentText("URL:");

		Optional<String> result = dialog.showAndWait();

		result.ifPresent(newWeb -> { 
			webTable.getItems().add(new Web(newWeb));
			btnEliminarWeb.setDisable(false);
		});
    }
    
    @FXML
    void onEliminarWeb(ActionEvent e) { 
    	Web lastWeb = webTable.getSelectionModel().getSelectedItem();
    	webTable.getItems().remove(lastWeb);
    	if (webTable.getSelectionModel().getSelectedItem() == null) {
    		btnEliminarWeb.setDisable(true);
		}
    }

    // SHOW VIEW
 	public SplitPane getView() {
 		return view;
 	}
    
    public final ObjectProperty<Contacto> contactoProperty() {
		return this.contacto;
	}

	public final Contacto getContacto() {
		return this.contactoProperty().get();
	}

	public final void setContacto(final Contacto contacto) {
		this.contactoProperty().set(contacto);
	}
}
