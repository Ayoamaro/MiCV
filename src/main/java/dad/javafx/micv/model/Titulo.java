package dad.javafx.micv.model;

import java.time.LocalDate;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * @author Ayoze Amaro
 *
 */
public class Titulo {

	private ObjectProperty<LocalDate> desde = new SimpleObjectProperty<LocalDate>();
	private ObjectProperty<LocalDate> hasta = new SimpleObjectProperty<LocalDate>();
	private StringProperty denominacion = new SimpleStringProperty();
	private StringProperty organizador = new SimpleStringProperty();
	
	public Titulo() {}
	
	public Titulo(String denominacion, String organizador, LocalDate desde, LocalDate hasta) {
		super();
		this.denominacion.set(denominacion);
		this.organizador.set(organizador);
		this.desde.set(desde);
		this.hasta.set(hasta);
	}
	
	public final ObjectProperty<LocalDate> desdeProperty() {
		return this.desde;
	}
	
	public final LocalDate getDesde() {
		return this.desdeProperty().get();
	}
	
	public final void setDesde(final LocalDate desde) {
		this.desdeProperty().set(desde);
	}
	
	public final ObjectProperty<LocalDate> hastaProperty() {
		return this.hasta;
	}
	
	public final LocalDate getHasta() {
		return this.hastaProperty().get();
	}
	
	public final void setHasta(final LocalDate hasta) {
		this.hastaProperty().set(hasta);
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
	
	public final StringProperty organizadorProperty() {
		return this.organizador;
	}
	
	public final String getOrganizador() {
		return this.organizadorProperty().get();
	}
	
	public final void setOrganizador(final String organizador) {
		this.organizadorProperty().set(organizador);
	}

	@Override
	public String toString() {
		return "Titulo [fechaDesde=" + desde + ", fechaHasta=" + hasta + ", denominacion=" + denominacion
				+ ", organizador=" + organizador + "]";
	}

	
}
