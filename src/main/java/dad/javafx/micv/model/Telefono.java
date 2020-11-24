package dad.javafx.micv.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * @author Ayoze Amaro
 *
 */
public class Telefono {

	private StringProperty numero = new SimpleStringProperty();

	public final StringProperty numeroProperty() {
		return this.numero;
	}

	public final String getNumero() {
		return this.numeroProperty().get();
	}
	
	public final void setNumero(final String numero) {
		this.numeroProperty().set(numero);
	}
}
