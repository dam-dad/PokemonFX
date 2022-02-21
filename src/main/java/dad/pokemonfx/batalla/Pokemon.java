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
	private ListProperty<Attack> attacks = new SimpleListProperty<>(FXCollections.observableArrayList());
	private DoubleProperty health = new SimpleDoubleProperty();
	private StringProperty rutapdf = new SimpleStringProperty();

	public Pokemon(String name, PokemonType type, Integer level, List<Attack> attacks) {
		super();
		this.setRutapdf("images/" + name + "-CPU.png");
		this.setBack(new Image("/images/" + name + ".png"));
		this.setFront(new Image("/images/" + name + "-CPU.png"));
		this.setName(name);
		this.setType(type);
		this.setLevel(level);
		this.attacks.setAll(attacks);
		if (level > 0 && level < 100) {
			attacks.stream().forEach(attack -> {
				Double poder = attack.getDamage();
				attack.setDamage(poder + (level / 100) * level);
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

	public final ListProperty<Attack> attacksProperty() {
		return this.attacks;
	}

	public final ObservableList<Attack> getAttacks() {
		return this.attacksProperty().get();
	}

	public final void setAttacks(final ObservableList<Attack> attacks) {
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

	public StringProperty rutapdfProperty() {
		return this.rutapdf;
	}

	public String getRutapdf() {
		return this.rutapdfProperty().get();
	}

	public void setRutapdf(final String rutapdf) {
		this.rutapdfProperty().set(rutapdf);
	}

}
