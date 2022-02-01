package dad.pokemonfx.batalla;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

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
			
			// variable tipo
			Double cantidadAtaque = ataque.getPoder() * Damage.getEffectivity(PokemonType.valueOf(ataque.getTipoAtaque()), pok.getType());
			
			/*
			// variable crítico, desactivada para testear más fácil el resto de cosas
			Random r = new Random();
	        int randomInt = r.nextInt(100) + 1;		
			float critical = 1f;	
	        if (randomInt <= 6.25f)//then we have a critical hit
	        {
	            critical = 2f;
	        }	        
	        cantidadAtaque = cantidadAtaque * critical;
	        */
			if (cantidadAtaque > pok.getHealth()) {
				pok.setHealth(0);
			} else {
				pok.setHealth(pok.getHealth() - cantidadAtaque);
			}
		}

	}

	public static void ataquecpu(Pokemon pok) {
		int valorDado = (int) Math.floor(Math.random() * 4);
		Ataque ataque = pok.getAttacks().get(valorDado);
		ataque(ataque, pok);

	}

	public void cargarlistaPokemon() {
		try {
			SAXBuilder builder = new SAXBuilder();
			Document document = builder.build(getClass().getResource("/Pokemon.xml"));
			Element root = document.getRootElement();
			List<Element> pokemonElements = root.getChildren("Pokemon");
			for (int i = 0; i < pokemonElements.size(); i++) {
				
				Element pokemonElement = pokemonElements.get(i);

				List<Element> attackElements = pokemonElement.getChild("Ataques").getChildren();
				
				List<Ataque> listaAtaques = attackElements.stream()
					.map(attackElement -> {
						double poder = Integer.valueOf(attackElement.getAttributeValue("poder"));
						double precision = Integer.valueOf(attackElement.getAttributeValue("precision"));
						String nombre = attackElement.getText();
						String tipo = attackElement.getAttributeValue("tipo");					
						return new Ataque(nombre, poder, precision, tipo);
					})
					.collect(Collectors.toList());
				
				String nombre = pokemonElement.getAttributeValue("nombre");
				int nivel = Integer.valueOf(pokemonElements.get(i).getAttributeValue("nivel"));
				PokemonType tipo = PokemonType.valueOf(pokemonElement.getAttributeValue("tipo"));
				Pokemon pok = new Pokemon(nombre, tipo, nivel,listaAtaques);
				eleccionController.getListpokemon().add(pok);

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
