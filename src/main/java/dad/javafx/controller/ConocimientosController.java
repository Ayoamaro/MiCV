package dad.javafx.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dad.javafx.micv.model.Conocimientos;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;

/**
 * @author Ayoze Amaro
 *
 */
public class ConocimientosController implements Initializable {

	// VIEW
	@FXML
	private HBox view;
	@FXML
	private TableView<Conocimientos> table;
	@FXML
	private TableColumn<Conocimientos, String> denominacion, nivel;
	
	public ConocimientosController() throws IOException {
		FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/ConocimientosView.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) { }
    
	public HBox getView() {
    	return view;
    }
	
	@FXML
    private void onNuevoConocimiento(ActionEvent event) { }

	@FXML
    private void onNuevoIdioma(ActionEvent event) { }
	
    @FXML
    private void onEliminar(ActionEvent event) { }
}
