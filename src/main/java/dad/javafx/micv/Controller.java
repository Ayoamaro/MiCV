package dad.javafx.micv;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dad.javafx.micv.controller.ConocimientosController;
import dad.javafx.micv.controller.ContactoController;
import dad.javafx.micv.controller.ExperienciaController;
import dad.javafx.micv.controller.FormacionController;
import dad.javafx.micv.controller.PersonalController;
import dad.javafx.micv.model.CV;
import dad.javafx.micv.utils.JSONUtils;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

/**
 * @author Ayoze Amaro
 *
 */
public class Controller implements Initializable {

	// CONTROLLER
	private PersonalController personalController = new PersonalController();
	private ContactoController contactoController = new ContactoController();
	private FormacionController formacionController = new FormacionController();
	private ExperienciaController experienciaController = new ExperienciaController();
	private ConocimientosController conocimientosController = new ConocimientosController();
		
	// MODEL
	private ObjectProperty<CV> cv = new SimpleObjectProperty<>();
		
	// VIEW
	@FXML
	private BorderPane view;
	@FXML
	private Tab personalTab, contactoTab, formacionTab, experienciaTab, conocimientosTab;
		
	// CONSTRUCTOR
	public Controller() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainView.fxml"));
		loader.setController(this);
		loader.load();
	}
		
	// INITIALIZE
	public void initialize(URL location, ResourceBundle resources) {
		personalTab.setContent(personalController.getView());
		contactoTab.setContent(contactoController.getView());
		formacionTab.setContent(formacionController.getView());
		experienciaTab.setContent(experienciaController.getView());
		conocimientosTab.setContent(conocimientosController.getView());
		cv.addListener((o, oldValue, newValue) -> onCVChanged(o, oldValue, newValue));
		cv.set(new CV());	
	}
		
	private void onCVChanged(ObservableValue<? extends CV> o, CV oldValue, CV newValue) {
	    	
		// UNBIND CV PREVIOUS
		if (oldValue != null) {
			personalController.personalProperty().unbind(); 
	    	contactoController.contactoProperty().unbind();
			formacionController.formacionesProperty().unbind();
			experienciaController.experienciasProperty().unbind();
			conocimientosController.habilidadesProperty().unbind();
	    }
	    // BIND NEW CV
	    if (newValue != null) {
	    	personalController.personalProperty().bind(newValue.personalProperty()); 
	    }
	}

	@FXML
	void onAbrirAction(ActionEvent event) {

		FileChooser fileChooser = new FileChooser();
	    fileChooser.setTitle("Abrir un currículum");
	    fileChooser.getExtensionFilters().add(new ExtensionFilter("Currículum Vitae (*.cv)", "*.cv"));
	    fileChooser.getExtensionFilters().add(new ExtensionFilter("Todos los archivos", "*.*"));
	    File cvFile = fileChooser.showOpenDialog(App.getPrimaryStage());
	    if (cvFile != null) {
	    	try {
				cv.set(JSONUtils.fromJson(cvFile, CV.class));
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }
			
	}

	@FXML
	void onAcercaDeAction(ActionEvent event) { }

	@FXML
	void onGuardarAction(ActionEvent event) { }

	@FXML
	void onGuardarComoAction(ActionEvent event) {

	    FileChooser fileChooser = new FileChooser();
	    fileChooser.setTitle("Guardar un currículum");
	    fileChooser.getExtensionFilters().add(new ExtensionFilter("Currículum Vitae (*.cv)", "*.cv"));
	    fileChooser.getExtensionFilters().add(new ExtensionFilter("Todos los archivos", "*.*"));
	    File cvFile = fileChooser.showSaveDialog(App.getPrimaryStage());
	    if (cvFile != null) {
	    	try {
				JSONUtils.toJson(cvFile, cv.get());
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }
	}

	@FXML
	void onNuevoAction(ActionEvent event) {
		cv.set(new CV());
	}

	@FXML
	void onSalirAction(ActionEvent event) { }
	
	// SHOW VIEW
	public BorderPane getView() {
		return view;
	}
}
