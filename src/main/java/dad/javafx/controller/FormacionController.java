package dad.javafx.controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import dad.javafx.micv.model.Formacion;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;

/**
 * @author Ayoze Amaro
 *
 */
public class FormacionController {

	private ListProperty<Formacion> formaciones = new SimpleListProperty<Formacion>(FXCollections.observableArrayList());
	
	// VIEW
	@FXML
    private HBox view;
    @FXML
    private TableView<Formacion> table;
    @FXML
    private TableColumn<Formacion, LocalDate> desdeCol, hastaCol;
    @FXML
    private TableColumn<Formacion, String> denominacionCol, organizadorCol;

	public FormacionController() throws IOException {
		FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/FormacionView.fxml"));
		loader.setController(this);
		loader.load();	
	}
	
	public void initialize(URL location, ResourceBundle resources) { }
	
	
	public HBox getView() {
		return view;
	}

    @FXML
    private void onAñadir(ActionEvent event) { }

    @FXML
    private void onEliminar(ActionEvent event) { }
}
