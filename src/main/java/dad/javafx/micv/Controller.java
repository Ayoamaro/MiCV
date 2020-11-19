package dad.javafx.micv;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;

/**
 * @author Ayoze Amaro
 *
 */
public class Controller implements Initializable {

	@FXML
    private BorderPane view;
    @FXML
    private Tab personalTab, contactoTab, formacionTab, experienciaTab, conocimientosTab;

	public Controller() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainView.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

    @FXML
    void onAbrirAction(ActionEvent event) {

    }

    @FXML
    void onAcercaDeAction(ActionEvent event) {

    }

    @FXML
    void onGuardarAction(ActionEvent event) {

    }

    @FXML
    void onGuardarComoAction(ActionEvent event) {

    }

    @FXML
    void onNuevoAction(ActionEvent event) {
    	System.out.println("Has pulsado nuevo");
    }

    @FXML
    void onSalirAction(ActionEvent event) {

    }
    
    public BorderPane getView() {
		return this.view;
	}
}
