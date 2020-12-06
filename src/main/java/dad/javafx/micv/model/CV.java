package dad.javafx.micv.model;

import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * @author Ayoze Amaro
 *
 */
public class CV {

	private ObjectProperty<Personal> personal = new SimpleObjectProperty<Personal>(new Personal());
	private ListProperty<Titulo> formacion = new SimpleListProperty<>(FXCollections.observableArrayList());
	private ListProperty<Experiencia> experiencias = new SimpleListProperty<>(FXCollections.observableArrayList());

	public final ObjectProperty<Personal> personalProperty() {
		return this.personal;
	}

	public final Personal getPersonal() {
		return this.personalProperty().get();
	}

	public final void setPersonal(final Personal personal) {
		this.personalProperty().set(personal);
	}

	public final ListProperty<Titulo> formacionProperty() {
		return this.formacion;
	}
	
	public final ObservableList<Titulo> getFormacion() {
		return this.formacionProperty().get();
	}
	
	public final void setFormacion(final ObservableList<Titulo> formacion) {
		this.formacionProperty().set(formacion);
	}
	
	public final ListProperty<Experiencia> experienciasProperty() {
		return this.experiencias;
	}
	
	public final ObservableList<Experiencia> getExperiencias() {
		return this.experienciasProperty().get();
	}
	
	public final void setExperiencias(final ObservableList<Experiencia> experiencias) {
		this.experienciasProperty().set(experiencias);
	}
}
