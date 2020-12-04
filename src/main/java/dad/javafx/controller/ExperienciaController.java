package dad.javafx.controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import dad.javafx.micv.model.Experiencia;
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
public class ExperienciaController implements Initializable {
	
	// VIEW
	@FXML
    private HBox view;
    @FXML
    private TableView<Experiencia> table;
    @FXML
    private TableColumn<Experiencia, LocalDate> desde, hasta;
    @FXML
    private TableColumn<Experiencia, String> denominacion, empleador;
    
    public ExperienciaController() throws IOException {
		FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/ExperienciaView.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) { }
    
	public HBox getView() {
    	return view;
    }
	
	@FXML
    private void onNuevo(ActionEvent event) { }

    @FXML
    private void onEliminar(ActionEvent event) { }
    
}
