package dad.javafx.micv.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * @author Ayoze Amaro
 *
 */
public class Conocimientos {

	public StringProperty denominacion = new SimpleStringProperty();
	public StringProperty nivel = new SimpleStringProperty();
	
	public Conocimientos() { }

	public Conocimientos(String denominacion, String nivel) {
		super();
		this.denominacion.set(denominacion);
		this.nivel.set(nivel);
	}
	
	public final StringProperty denominacionProperty() {
		return this.denominacion;
	}
	
	public final String getDenominacion() {
		return this.denominacionProperty().get();
	}
	
	public final void setDenominacion(final String denominacion) {
		this.denominacionProperty().set(denominacion);
	}

	public final StringProperty nivelProperty() {
		return this.nivel;
	}
	
	public final String getNivel() {
		return this.nivelProperty().get();
	}
	
	public final void setNivel(final String nivel) {
		this.nivelProperty().set(nivel);
	}
}
