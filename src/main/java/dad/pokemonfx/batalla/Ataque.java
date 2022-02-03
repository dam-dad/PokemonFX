package dad.pokemonfx.batalla;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Ataque {

	private StringProperty nombre = new SimpleStringProperty();
	private Double poder;
	private Double precision;
	private String tipoAtaque;

	public Ataque(String nombretext, Double poder, Double precision, String tipoAtaque) {
		super();
		nombre.set(nombretext);
		this.poder = poder;
		this.precision = precision;
		this.tipoAtaque = tipoAtaque;
	}
	

	public Double getPrecision() {
		return precision;
	}

	public void setPrecision(Double precision) {
		this.precision = precision;
	}

	public Double getPoder() {
		return poder;
	}

	public void setPoder(Double poder) {
		this.poder = poder;
	}


	public String getTipoAtaque() {
		return tipoAtaque;
	}


	public void setTipoAtaque(String tipoAtaque) {
		this.tipoAtaque = tipoAtaque;
	}


	@Override
	public String toString() {
		return "Poder=" + poder + "\n" + precision+"%";
	}


	public StringProperty nombreProperty() {
		return this.nombre;
	}
	


	public String getNombre() {
		return this.nombreProperty().get();
	}
	


	public void setNombre(final String nombre) {
		this.nombreProperty().set(nombre);
	}
	

}
