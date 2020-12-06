package dad.javafx.micv.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dad.javafx.micv.model.Contacto;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

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
    private TableView<?> telefonosTable, correoTable, webTable;
    @FXML
    private TableColumn<?, ?> numeroColumn, tipoColumn, emailColumn, urlColumn;
    @FXML
	private Button btnNuevoTlfn, btnEliminarTlfn, btnNuevoCorreo, btnEliminarCorreo, btnNuevaWeb, btnEliminarWeb;

    // CONSTRUCTOR
	public ContactoController() throws IOException {
		FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/ContactoView.fxml"));
		loader.setController(this);
		loader.load();	
	}

	// INITIALIZE
	public void initialize(URL location, ResourceBundle resources) { }
	

	@FXML
    void onAñadirCorreo(ActionEvent e) { }

    @FXML
    void onAñadirTelefono(ActionEvent e) { }

    @FXML
    void onAñadirWeb(ActionEvent e) { }

    @FXML
    void onEliminarCorreo(ActionEvent e) { }

    @FXML
    void onEliminarTelefono(ActionEvent e) { }

    @FXML
    void onEliminarWeb(ActionEvent e) { }

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
