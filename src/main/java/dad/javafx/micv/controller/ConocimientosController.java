package dad.javafx.micv.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dad.javafx.micv.model.Conocimientos;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;

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
	private TableColumn<Conocimientos, String> denominacion, nivel;
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
	public void initialize(URL location, ResourceBundle resources) { }
    
	@FXML
    private void onNuevoConocimiento(ActionEvent event) { }

	@FXML
    private void onNuevoIdioma(ActionEvent event) { }
	
    @FXML
    private void onEliminar(ActionEvent event) { }

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
