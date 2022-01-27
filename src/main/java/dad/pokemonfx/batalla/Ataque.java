package dad.pokemonfx.batalla;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Ataque {

	private StringProperty nombre = new SimpleStringProperty();
	private Integer poder;
	private Integer precision;
	private String tipoAtaque;

	public Ataque(String nombretext, Integer poder, Integer precision, String tipoAtaque) {
		super();
		nombre.set(nombretext);
		this.poder = poder;
		this.precision = precision;
		this.tipoAtaque = tipoAtaque;
	}
	

	public Integer getPrecision() {
		return precision;
	}

	public void setPrecision(Integer precision) {
		this.precision = precision;
	}

	public Integer getPoder() {
		return poder;
	}

	public void setPoder(Integer poder) {
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
