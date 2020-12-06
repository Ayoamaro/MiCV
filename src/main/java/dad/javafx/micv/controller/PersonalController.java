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
import javafx.scene.layout.GridPane;

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
	
	public PersonalController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PersonalView.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		personal.addListener((o, ov, nv) -> onPersonalChanged(o, ov, nv));	
		quitarNacionalidadButton.setDisable(true);

		ReadCSVFiles.readNacionalidades(nacionalidadesCSV);
		ReadCSVFiles.readPaises(paisesCSV);

		paisCombo.getItems().addAll(paisesCSV);
		select.bind(nacionalidadesList.getSelectionModel().selectedItemProperty());
		
	}
		
	private void onPersonalChanged(ObservableValue<? extends Personal> o, Personal ov, Personal nv) {

		if (ov != null) {
			identificacionText.textProperty().unbindBidirectional(ov.identificacionProperty());
			nombreText.textProperty().unbindBidirectional(ov.nombreProperty());
			apellidosText.textProperty().unbindBidirectional(ov.apellidosProperty());
			fechaNacimientoDate.valueProperty().unbindBidirectional(ov.fechaNacimientoProperty());
			direccionText.textProperty().unbindBidirectional(ov.direccionProperty());
			localidadText.textProperty().unbindBidirectional(ov.localidadProperty());
			codigoPostalText.textProperty().unbindBidirectional(ov.codigoPostalProperty());
			paisCombo.valueProperty().unbindBidirectional(ov.paisProperty());
			nacionalidadesList.itemsProperty().unbindBidirectional(ov.nacionalidadesProperty());
		}
			
		if (nv != null) {
			identificacionText.textProperty().bindBidirectional(nv.identificacionProperty());
			nombreText.textProperty().bindBidirectional(nv.nombreProperty());
			apellidosText.textProperty().bindBidirectional(nv.apellidosProperty());
			fechaNacimientoDate.valueProperty().bindBidirectional(nv.fechaNacimientoProperty());
			direccionText.textProperty().bindBidirectional(nv.direccionProperty());
			localidadText.textProperty().bindBidirectional(nv.localidadProperty());
			codigoPostalText.textProperty().bindBidirectional(nv.codigoPostalProperty());
			paisCombo.valueProperty().bindBidirectional(nv.paisProperty());
			nacionalidadesList.itemsProperty().bindBidirectional(nv.nacionalidadesProperty());
		}
			
	}

	public GridPane getView() {
		return view;
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
