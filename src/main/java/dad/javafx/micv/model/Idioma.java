package dad.javafx.micv.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * @author Ayoze Amaro
 *
 */
public class Idioma extends Conocimientos {

	private StringProperty certificacion = new SimpleStringProperty();

	public Idioma() { }
	
	public Idioma(String denominacion, String nivel, String certificacion) {
		super();
		this.denominacion.set(denominacion);
		this.nivel.set(nivel);
		this.certificacion.set(certificacion);
	}
	
	public final StringProperty certificacionProperty() {
		return this.certificacion;
	}
	
	public final String getCertificacion() {
		return this.certificacionProperty().get();
	}
	
	public final void setCertificacion(final String certificacion) {
		this.certificacionProperty().set(certificacion);
	}
}
