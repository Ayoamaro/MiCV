package dad.javafx.micv.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * @author Ayoze Amaro
 *
 */
public class Telefono {

	private StringProperty numero = new SimpleStringProperty();
	private StringProperty tipo = new SimpleStringProperty();

	public Telefono(String numero, String tipo) {
		super();
		this.numero.set(numero);
		this.tipo.set(tipo);
	}

	public final StringProperty numeroProperty() {
		return this.numero;
	}

	public final String getNumero() {
		return this.numeroProperty().get();
	}
	
	public final void setNumero(final String numero) {
		this.numeroProperty().set(numero);
	}

	public final StringProperty tipoProperty() {
		return this.tipo;
	}
	
	public final String getTipo() {
		return this.tipoProperty().get();
	}
	
	public final void setTipo(final String tipo) {
		this.tipoProperty().set(tipo);
	}
	
	@Override
	public String toString() {
		return "Telefono [numero=" + numero + ", tipo=" + tipo + "]";
	}
}
