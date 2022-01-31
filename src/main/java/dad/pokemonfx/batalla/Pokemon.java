package dad.pokemonfx.batalla;

import java.util.ArrayList;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;

public class Pokemon {

	private StringProperty nombre = new SimpleStringProperty();
	private String tipo;
	private Integer nivel;
	private Image delante;
	private Image cpu;
	private ArrayList<Ataque> ataques;
	private DoubleProperty vida = new SimpleDoubleProperty();

	public Pokemon(String nombre, String tipo, Integer nivel, ArrayList<Ataque> ataques) {
		super();
		this.setDelante(new Image("file:src/main/resources/images/" + nombre + ".png"));
		this.setCpu(new Image("file:src/main/resources/images/" + nombre + "-CPU.png"));
		this.nombre.set(nombre);
		this.tipo = tipo;
		this.nivel = nivel;
		this.ataques = ataques;
		if (nivel > 0 && nivel < 100) {
			for (int i = 0; i < ataques.size(); i++) {
				Double poder = ataques.get(i).getPoder();
				ataques.get(i).setPoder(poder + (nivel / 100) * nivel);
			}
		} else {
			System.out.println("El nivel tiene que estar entre 0 y 100");
		}
		this.setVida(400 + nivel * 0.5);
	}

	public Integer getNivel() {
		return nivel;
	}

	public void setNivel(Integer nivel) {
		this.nivel = nivel;
	}

	public ArrayList<Ataque> getAtaques() {
		return ataques;
	}

	public void setAtaques(ArrayList<Ataque> ataques) {
		this.ataques = ataques;
	}

	public DoubleProperty vidaProperty() {
		return this.vida;
	}

	public double getVida() {
		return this.vidaProperty().get();
	}

	public void setVida(final double vida) {
		this.vidaProperty().set(vida);
	}

	public Image getDelante() {
		return delante;
	}

	public void setDelante(Image delante) {
		this.delante = delante;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return this.getNombre() + "->" + this.getTipo();
	}

	public Image getCpu() {
		return cpu;
	}

	public void setCpu(Image cpu) {
		this.cpu = cpu;
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
