package dad.pokemonfx.batalla;

import java.util.List;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

public class Pokemon {

	private StringProperty name = new SimpleStringProperty();
	private ObjectProperty<PokemonType> type = new SimpleObjectProperty<>();
	private IntegerProperty level = new SimpleIntegerProperty();
	private ObjectProperty<Image> back = new SimpleObjectProperty<>();
	private ObjectProperty<Image> front = new SimpleObjectProperty<>();
	private ListProperty<Ataque> attacks = new SimpleListProperty<>(FXCollections.observableArrayList());
	private DoubleProperty health = new SimpleDoubleProperty();

	public Pokemon(String name, PokemonType type, Integer level, List<Ataque> attacks) {
		super();
		this.setBack(new Image("/images/" + name + ".png"));
		this.setFront(new Image("/images/" + name + "-CPU.png"));
		this.setName(name);
		this.setType(type);
		this.setLevel(level);
		this.attacks.setAll(attacks);
		if (level > 0 && level < 100) {			
			attacks.stream().forEach(attack -> {
				Double poder = attack.getPoder();
				attack.setPoder(poder + (level / 100) * level);				
			});
		} else {
			throw new IllegalArgumentException("El nivel tiene que estar entre 0 y 100");
		}
		this.setHealth(400 + level * 0.5);
	}

	@Override
	public String toString() {
		return this.getName() + "->" + this.getType();
	}

	public final StringProperty nameProperty() {
		return this.name;
	}

	public final String getName() {
		return this.nameProperty().get();
	}

	public final void setName(final String name) {
		this.nameProperty().set(name);
	}

	public final ObjectProperty<PokemonType> typeProperty() {
		return this.type;
	}

	public final PokemonType getType() {
		return this.typeProperty().get();
	}

	public final void setType(final PokemonType type) {
		this.typeProperty().set(type);
	}

	public final IntegerProperty levelProperty() {
		return this.level;
	}

	public final int getLevel() {
		return this.levelProperty().get();
	}

	public final void setLevel(final int level) {
		this.levelProperty().set(level);
	}

	public final ObjectProperty<Image> backProperty() {
		return this.back;
	}

	public final Image getBack() {
		return this.backProperty().get();
	}

	public final void setBack(final Image back) {
		this.backProperty().set(back);
	}

	public final ObjectProperty<Image> frontProperty() {
		return this.front;
	}

	public final Image getFront() {
		return this.frontProperty().get();
	}

	public final void setFront(final Image front) {
		this.frontProperty().set(front);
	}

	public final ListProperty<Ataque> attacksProperty() {
		return this.attacks;
	}

	public final ObservableList<Ataque> getAttacks() {
		return this.attacksProperty().get();
	}

	public final void setAttacks(final ObservableList<Ataque> attacks) {
		this.attacksProperty().set(attacks);
	}

	public final DoubleProperty healthProperty() {
		return this.health;
	}

	public final double getHealth() {
		return this.healthProperty().get();
	}

	public final void setHealth(final double health) {
		this.healthProperty().set(health);
	}

}
