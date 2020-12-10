package dad.javafx.micv.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import dad.javafx.micv.model.Nacionalidad;
import dad.javafx.micv.model.Personal;
import dad.javafx.micv.utils.ReadCSVFiles;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * @author Ayoze Amaro
 *
 */
public class PersonalController implements Initializable {

	// MODEL
	private ObjectProperty<Personal> personal = new SimpleObjectProperty<>();
	private ListProperty<Nacionalidad> nacionalidadesCSV = new SimpleListProperty<Nacionalidad>(FXCollections.observableArrayList());
	private ListProperty<String> paisesCSV = new SimpleListProperty<String>(FXCollections.observableArrayList());
	private ObjectProperty<Nacionalidad> select = new SimpleObjectProperty<Nacionalidad>();

	// VIEW
	@FXML
	private GridPane view;
	@FXML
	private TextField identificacionText, nombreText, apellidosText, codigoPostalText, localidadText;
	@FXML
	private DatePicker fechaNacimientoDate;
	@FXML
	private TextArea direccionText;
	@FXML
	private ListView<Nacionalidad> nacionalidadesList;
	@FXML
	private ComboBox<String> paisCombo;
	@FXML
	private Button nuevaNacionalidadButton, quitarNacionalidadButton;
	
	// CONSTRUCTOR
	public PersonalController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PersonalView.fxml"));
		loader.setController(this);
		loader.load();
	}

	// INITIALIZE
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		quitarNacionalidadButton.setDisable(true);
		personal.addListener((o, oldValue, newValue) -> onPersonalChanged(o, oldValue, newValue));	
		
		ReadCSVFiles.readNacionalidades(nacionalidadesCSV);
		ReadCSVFiles.readPaises(paisesCSV);

		paisCombo.getItems().addAll(paisesCSV);
		select.bind(nacionalidadesList.getSelectionModel().selectedItemProperty());
		
	}
		
	private void onPersonalChanged(ObservableValue<? extends Personal> o, Personal oldValue, Personal newValue) {

		// UNBIND OLDVALUE
		if (oldValue != null) {
			identificacionText.textProperty().unbindBidirectional(oldValue.identificacionProperty());
			nombreText.textProperty().unbindBidirectional(oldValue.nombreProperty());
			apellidosText.textProperty().unbindBidirectional(oldValue.apellidosProperty());
			fechaNacimientoDate.valueProperty().unbindBidirectional(oldValue.fechaNacimientoProperty());
			direccionText.textProperty().unbindBidirectional(oldValue.direccionProperty());
			localidadText.textProperty().unbindBidirectional(oldValue.localidadProperty());
			codigoPostalText.textProperty().unbindBidirectional(oldValue.codigoPostalProperty());
			paisCombo.valueProperty().unbindBidirectional(oldValue.paisProperty());
			nacionalidadesList.itemsProperty().unbindBidirectional(oldValue.nacionalidadesProperty());
		}
		// BIND NEWVALUE
		if (newValue != null) {
			identificacionText.textProperty().bindBidirectional(newValue.identificacionProperty());
			nombreText.textProperty().bindBidirectional(newValue.nombreProperty());
			apellidosText.textProperty().bindBidirectional(newValue.apellidosProperty());
			fechaNacimientoDate.valueProperty().bindBidirectional(newValue.fechaNacimientoProperty());
			direccionText.textProperty().bindBidirectional(newValue.direccionProperty());
			localidadText.textProperty().bindBidirectional(newValue.localidadProperty());
			codigoPostalText.textProperty().bindBidirectional(newValue.codigoPostalProperty());
			paisCombo.valueProperty().bindBidirectional(newValue.paisProperty());
			nacionalidadesList.itemsProperty().bindBidirectional(newValue.nacionalidadesProperty());
		}
			
	}
	
	@FXML
	void onNuevaNacionalidadAction(ActionEvent event) { 
		
		ChoiceDialog<Nacionalidad> newDialog = new ChoiceDialog<>(nacionalidadesCSV.get(0), nacionalidadesCSV);
		newDialog.setHeaderText("Nueva nacionalidad");
		newDialog.setTitle("Añadir nacionalidad");
		newDialog.setContentText("Seleccione una nacionalidad");

		Optional<Nacionalidad> selectNacionalidad = newDialog.showAndWait();
		nacionalidadesList.getItems().add(selectNacionalidad.get());
		quitarNacionalidadButton.setDisable(false);
	}

	@FXML
	void onQuitarNacionalidadAction(ActionEvent event) { 
		getPersonal().getNacionalidades().remove(select.get());
	}

	// SHOW VIEW
	public GridPane getView() {
		return view;
	}

	public final ObjectProperty<Personal> personalProperty() {
		return this.personal;
	}

	public final Personal getPersonal() {
		return this.personalProperty().get();
	}

	public final void setPersonal(final Personal personal) {
		this.personalProperty().set(personal);
	}
}