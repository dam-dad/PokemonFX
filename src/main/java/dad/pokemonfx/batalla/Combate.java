package dad.pokemonfx.batalla;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Combate {

	EleccionController eleccionController;
	private ListProperty<Pokemon> entrenador1 = new SimpleListProperty<>(FXCollections.observableArrayList());
	private ListProperty<Pokemon> entrenador2 = new SimpleListProperty<>(FXCollections.observableArrayList());

	public Combate() {
		try {
			eleccionController = new EleccionController();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cargarlistaPokemon();
		int pk = 0;
		for (int i = 0; i < 6; i++) {
			entrenador1.get().add(i, eleccionController.getListpokemon().get(pk));
			pk++;
		}
		for (int i = 0; i < 6; i++) {
			entrenador2.add(i, eleccionController.getListpokemon().get(pk));
			pk++;
		}

	}

	public static void ataque(Ataque ataque, Pokemon pok) {
		int numeroaletatorio = (int) (Math.random() * 100 + 1);
		if (numeroaletatorio <= ataque.getPrecision()) {
			if (ataque.getPoder() > pok.getVida()) {
				pok.setVida(0);
			} else {
				pok.setVida(pok.getVida() - ataque.getPoder());
			}
		}

	}

	public static void ataquecpu(Pokemon pok) {
		int valorDado = (int) Math.floor(Math.random() * 4);
		Ataque ataque = pok.getAtaques().get(valorDado);
		ataque(ataque, pok);

	}

	public void cargarlistaPokemon() {
		try {
			SAXBuilder builder = new SAXBuilder();
			File xml = new File("src/main/resources/Pokemon.xml");
			Document document = builder.build(xml);
			Element root = document.getRootElement();
			List<Element> list = root.getChildren("Pokemon");
			for (int i = 0; i < list.size(); i++) {
				Element Pokemon = list.get(i);
				Element ataque = Pokemon.getChild("Ataques");
				List<Element> ataques = ataque.getChildren();
				ArrayList<Ataque> listaAtaques = new ArrayList<Ataque>();
				for (int j = 0; j < ataques.size(); j++) {
					int poder = Integer.valueOf(ataques.get(j).getAttributeValue("poder"));
					int precision = Integer.valueOf(ataques.get(j).getAttributeValue("precision"));
					listaAtaques.add(j, new Ataque(ataques.get(j).getText(), poder, precision,
							ataques.get(j).getAttributeValue("tipo")));
				}
				int nivel = Integer.valueOf(list.get(i).getAttributeValue("nivel"));
				Pokemon pok = new Pokemon(Pokemon.getAttributeValue("nombre"), Pokemon.getAttributeValue("tipo"), nivel,
						listaAtaques);
				eleccionController.getListpokemon().add(i, pok);

			}
		} catch (JDOMException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public ListProperty<Pokemon> entrenador1Property() {
		return this.entrenador1;
	}

	public ObservableList<Pokemon> getEntrenador1() {
		return this.entrenador1Property().get();
	}

	public void setEntrenador1(final ObservableList<Pokemon> entrenador1) {
		this.entrenador1Property().set(entrenador1);
	}

	public ListProperty<Pokemon> entrenador2Property() {
		return this.entrenador2;
	}

	public ObservableList<Pokemon> getEntrenador2() {
		return this.entrenador2Property().get();
	}

	public void setEntrenador2(final ObservableList<Pokemon> entrenador2) {
		this.entrenador2Property().set(entrenador2);
	}

	public EleccionController getEleccionController() {
		return eleccionController;
	}

	public void setEleccionController(EleccionController eleccionController) {
		this.eleccionController = eleccionController;
	}

}
