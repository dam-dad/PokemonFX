package dad.pokemonfx.batalla;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Clase batalla,crea una batalla predeterminada
 * @return batalla
 */
public class Battle {

	ChooseController chooseController;
	private ListProperty<Pokemon> trainer1 = new SimpleListProperty<>(FXCollections.observableArrayList());
	private ListProperty<Pokemon> trainer2 = new SimpleListProperty<>(FXCollections.observableArrayList());

	public Battle() {
		try {
			chooseController = new ChooseController();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		loadPokemonList();
		for (int i = 0; i < 6; i++) {
			int pokemonNumber = (int)(Math.random() * 12);
			trainer1.get().add(i, chooseController.getListPokemon().get(pokemonNumber));
			pokemonNumber++;
		}
		for (int i = 0; i < 6; i++) {
			int pokemonNumber = (int)(Math.random() * 12);
			trainer2.add(i, chooseController.getListPokemon().get(pokemonNumber));
			pokemonNumber++;
		}

	}

	public static void playerAttack(Attack attack, Pokemon pokemon) {
		int randomNum = (int) (Math.random() * 100 + 1);
		if (randomNum <= attack.getAccuracy()) {

			// variable tipo
			Double damageAmount = attack.getDamage()
					* Damage.getEffectivityMultiplier(PokemonType.valueOf(attack.getAttackType()), pokemon.getType());

			/*
			 * // variable crítico, desactivada para testear más fácil el resto de cosas
			 * Random r = new Random(); int randomInt = r.nextInt(100) + 1; float critical =
			 * 1f; if (randomInt <= 6.25f)//then we have a critical hit { critical = 2f; }
			 * cantidadAtaque = cantidadAtaque * critical;
			 */
			if (damageAmount > pokemon.getHealth()) {
				pokemon.setHealth(0);
			} else {
				pokemon.setHealth(pokemon.getHealth() - damageAmount);
			}
		}

	}

	public static void cpuAttack(Pokemon pokemon) {
		int randomCpuAttack = (int) Math.floor(Math.random() * 4);
		Attack attack = pokemon.getAttacks().get(randomCpuAttack);
		playerAttack(attack, pokemon);

	}

	public void loadPokemonList() {
		try {
			SAXBuilder builder = new SAXBuilder();
			Document document = builder.build(getClass().getResource("/Pokemon.xml"));
			Element root = document.getRootElement();
			List<Element> pokemonElements = root.getChildren("Pokemon");
			for (int i = 0; i < pokemonElements.size(); i++) {

				Element pokemonElement = pokemonElements.get(i);

				List<Element> attackElements = pokemonElement.getChild("Ataques").getChildren();

				List<Attack> attackList = attackElements.stream().map(attackElement -> {
					double damage = Integer.valueOf(attackElement.getAttributeValue("poder"));
					double accuracy = Integer.valueOf(attackElement.getAttributeValue("precision"));
					String name = attackElement.getText();
					String type = attackElement.getAttributeValue("tipo");
					return new Attack(name, damage, accuracy, type);
				}).collect(Collectors.toList());

				String name = pokemonElement.getAttributeValue("nombre");
				int level = Integer.valueOf(pokemonElements.get(i).getAttributeValue("nivel"));
				PokemonType type = PokemonType.valueOf(pokemonElement.getAttributeValue("tipo"));
				Pokemon pokemon = new Pokemon(name, type, level, attackList);
				chooseController.getListPokemon().add(pokemon);

			}
		} catch (JDOMException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public ListProperty<Pokemon> trainer1Property() {
		return this.trainer1;
	}

	public ObservableList<Pokemon> getTrainer1() {
		return this.trainer1Property().get();
	}

	public void setTrainer1(final ObservableList<Pokemon> trainer1) {
		this.trainer1Property().set(trainer1);
	}

	public ListProperty<Pokemon> entrenador2Property() {
		return this.trainer2;
	}

	public ObservableList<Pokemon> getTrainer2() {
		return this.entrenador2Property().get();
	}

	public void setTrainer2(final ObservableList<Pokemon> trainer2) {
		this.entrenador2Property().set(trainer2);
	}

	public ChooseController getChooseController() {
		return chooseController;
	}

	public void setEleccionController(ChooseController chooseController) {
		this.chooseController = chooseController;
	}

}
