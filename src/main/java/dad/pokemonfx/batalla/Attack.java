package dad.pokemonfx.batalla;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Attack {

	private StringProperty name = new SimpleStringProperty();
	private Double damage;
	private Double accuracy;
	private String attackType;
	
	public Attack(String nameText, Double damage, Double accuracy, String attackType) {
		super();
		name.set(nameText);
		this.damage = damage;
		this.accuracy = accuracy;
		this.attackType = attackType;
	}
	

	public Double getAccuracy() {
		return accuracy;
	}

	public void setAccuracy(Double accuray) {
		this.accuracy = accuray;
	}

	public Double getDamage() {
		return damage;
	}

	public void setDamage(Double damage) {
		this.damage = damage;
	}

	public String getAttackType() {
		return attackType;
	}

	public void setAttackType(String attackType) {
		this.attackType = attackType;
	}

	@Override
	public String toString() {
		return "Poder=" + damage + "\n" + accuracy+"%";
	}

	public StringProperty nameProperty() {
		return this.name;
	}	

	public String getName() {
		return this.nameProperty().get();
	}

	public void setName(final String name) {
		this.nameProperty().set(name);
	}

}
