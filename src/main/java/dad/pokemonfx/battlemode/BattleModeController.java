package dad.pokemonfx.battlemode;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dad.pokemonfx.batalla.App;
import dad.pokemonfx.batalla.Attack;
import dad.pokemonfx.batalla.Battle;
import dad.pokemonfx.batalla.Pokemon;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.converter.NumberStringConverter;

/**
 * Esta clase es el controlador del modo batalla
 *
 */
public class BattleModeController implements Initializable {

	private ObjectProperty<Battle> battle = new SimpleObjectProperty<>();
	private ObjectProperty<Pokemon> selectedPokemon = new SimpleObjectProperty<>();
	private ObjectProperty<Pokemon> cpuPokemon = new SimpleObjectProperty<>();
	private ListProperty<Pokemon> trainer1 = new SimpleListProperty<>(FXCollections.observableArrayList());
	private ListProperty<Pokemon> trainer2 = new SimpleListProperty<>(FXCollections.observableArrayList());
	private DoubleProperty healthPlayer = new SimpleDoubleProperty();
	private DoubleProperty healthCpu = new SimpleDoubleProperty();
	private StringProperty namePokemonPlayer = new SimpleStringProperty();
	private StringProperty namePokemonCpu = new SimpleStringProperty();
	private BooleanProperty combatEnded = new SimpleBooleanProperty();
	private DoubleProperty maxHealthPlayer = new SimpleDoubleProperty();
	private DoubleProperty maxHealthCpu = new SimpleDoubleProperty();

	@FXML
	private ImageView pokemonImage1;

	@FXML
	private ImageView pokemonImage2;

	@FXML
	private ComboBox<Pokemon> pokemonCombobox;

	@FXML
	private Button attackButton1;

	@FXML
	private Button attackButton2;

	@FXML
	private Button attackButton3;

	@FXML
	private Button attackButton4;

	@FXML
	private Label nameLabelPlayer;

	@FXML
	private Label nameLabelCpu;

	@FXML
	private Label attack1Label;

	@FXML
	private Label attack2Label;

	@FXML
	private Label attack3Label;

	@FXML
	private Label attack4Label;

	@FXML
	private Label attackMessageLabel;

	@FXML
	private Label healthPlayerLabel;

	@FXML
	private Label healthCpuLabel;

	@FXML
	private Label numberPokemonAlivePlayerLabel;

	@FXML
	private Label numberPokemonAliveCpuLabel;

	@FXML
	private ProgressBar healthPlayerProgressbar;

	@FXML
	private ProgressBar healthEnemyProgressbar;

	@FXML
	private Label levelPlayerLabel;

	@FXML
	private Label levelCpuLabel;

	@FXML
	private AnchorPane view;

	public BattleModeController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Juego.fxml"));
		loader.setController(this);
		loader.load();
	}

	public void initialize(URL location, ResourceBundle resources) {
		combatEnded.set(false);
		battle.set(new Battle());
		trainer1.bind(battle.get().trainer1Property());
		trainer2.bind(battle.get().entrenador2Property());
		pokemonCombobox.itemsProperty().bind(trainer1);
		pokemonCombobox.getSelectionModel().selectFirst();
		selectedPokemon.bind(pokemonCombobox.getSelectionModel().selectedItemProperty());
		cpuPokemon.set(trainer2.get(0));
		healthPlayer.bind(selectedPokemon.get().healthProperty());
		healthCpu.bind(cpuPokemon.get().healthProperty());
		maxHealthPlayer.set(healthPlayer.get());
		maxHealthCpu.set(healthCpu.get());
		healthPlayerLabel.textProperty().bindBidirectional(healthPlayer, new NumberStringConverter());
		healthCpuLabel.textProperty().bindBidirectional(healthCpu, new NumberStringConverter());
		healthPlayerProgressbar.progressProperty().bind(healthPlayer.divide(maxHealthPlayer));
		healthEnemyProgressbar.progressProperty().bind(healthCpu.divide(maxHealthCpu));
		nameLabelPlayer.textProperty().bind(namePokemonPlayer);
		namePokemonPlayer.bind(selectedPokemon.get().nameProperty());
		nameLabelCpu.textProperty().bind(namePokemonCpu);
		namePokemonCpu.bind(cpuPokemon.get().nameProperty());
		pokemonImage1.setImage(selectedPokemon.get().getBack());
		pokemonImage2.setImage(cpuPokemon.get().getFront());
		numberPokemonAlivePlayerLabel.setText("" + trainer1.getSize());
		numberPokemonAliveCpuLabel.setText("" + trainer2.getSize());
		levelPlayerLabel.setText("" + selectedPokemon.get().getLevel());
		levelCpuLabel.setText("" + cpuPokemon.get().getLevel());
		cargarBotones(selectedPokemon.get());
		selectedPokemon.addListener((o, ov, nv) -> onpokemonchanged(o, ov, nv));
		cpuPokemon.addListener((o, ov, nv) -> onpokemoncpuchanged(o, ov, nv));
		battle.addListener((o, ov, nv) -> oncombatechanged(o, ov, nv));

	}

	private void oncombatechanged(ObservableValue<? extends Battle> o, Battle ov, Battle nv) {
		if (ov != null) {
			trainer1.unbind();
			trainer2.unbind();
			pokemonCombobox.itemsProperty().unbind();
		}
		if (nv != null) {
			trainer1.bind(nv.trainer1Property());
			trainer2.bind(nv.entrenador2Property());
			pokemonCombobox.itemsProperty().bind(trainer1);
			pokemonCombobox.getSelectionModel().selectFirst();
			cpuPokemon.set(trainer2.get(0));
			numberPokemonAlivePlayerLabel.setText("" + trainer1.getSize());
			numberPokemonAliveCpuLabel.setText("" + trainer2.getSize());
		}

	}

	private void onpokemoncpuchanged(ObservableValue<? extends Pokemon> o, Pokemon ov, Pokemon nv) {
		if (trainer2.get().size() > 0) {
			if (ov != null) {
				healthCpu.unbind();
				namePokemonCpu.unbind();

			}
			if (nv != null) {
				healthCpu.bind(nv.healthProperty());
				namePokemonCpu.bind(nv.nameProperty());
				pokemonImage2.setImage(nv.getFront());
				levelCpuLabel.setText("" + cpuPokemon.get().getLevel());
			}

		}
	}

	private void onpokemonchanged(ObservableValue<? extends Pokemon> o, Pokemon ov, Pokemon nv) {
		if (trainer1.get().size() > 0) {
			if (nv == null) {
				nv = trainer1.get(0);
				pokemonCombobox.getSelectionModel().selectFirst();
			}
			if (ov != null) {
				healthPlayer.unbind();
				namePokemonPlayer.unbind();
				attackButton1.textProperty().unbind();
				attackButton2.textProperty().unbind();
				attackButton3.textProperty().unbind();
				attackButton4.textProperty().unbind();
			}
			if (nv != null) {
				healthPlayer.bind(nv.healthProperty());
				namePokemonPlayer.bind(nv.nameProperty());
				cargarBotones(nv);
				levelPlayerLabel.setText("" + selectedPokemon.get().getLevel());
				pokemonImage1.setImage(nv.getBack());
			}
		}

	}

	@FXML
	void onataque1(ActionEvent event) {
		double vida = selectedPokemon.get().getHealth();
		double vidacpu = cpuPokemon.get().getHealth();
		ataqueCombate(selectedPokemon.get().getAttacks().get(0), cpuPokemon.get());
		ponerMensaje(vidacpu, vida);

	}

	@FXML
	void onataque2(ActionEvent event) {
		double vida = cpuPokemon.get().getHealth();
		double vidacpu = cpuPokemon.get().getHealth();
		ataqueCombate(selectedPokemon.get().getAttacks().get(1), cpuPokemon.get());
		ponerMensaje(vidacpu, vida);
	}

	@FXML
	void onataque3(ActionEvent event) {
		double vida = cpuPokemon.get().getHealth();
		double vidacpu = cpuPokemon.get().getHealth();
		ataqueCombate(selectedPokemon.get().getAttacks().get(2), cpuPokemon.get());
		ponerMensaje(vidacpu, vida);

	}

	@FXML
	void onataque4(ActionEvent event) {
		double vida = cpuPokemon.get().getHealth();
		double vidacpu = cpuPokemon.get().getHealth();
		ataqueCombate(selectedPokemon.get().getAttacks().get(3), cpuPokemon.get());
		ponerMensaje(vidacpu, vida);

	}

	private void ataqueCombate(Attack ataque, Pokemon pk) {

		Battle.playerAttack(ataque, pk);
		/*
		 * if (pk.getVida() < 100) { Pokemoncpu.set(entrenador2.get((int)
		 * Math.floor(Math.random() * entrenador2.getSize()))); }
		 */
		if (pk.getHealth() <= 0) {
			trainer2.get().remove(cpuPokemon.get());
			numberPokemonAliveCpuLabel.setText("" + trainer2.getSize());
			if (trainer2.get().size() > 0) {
				cpuPokemon.set(trainer2.get(0));
			}

		}
		if (trainer2.size() == 0) {
			if (App.confirm("Resultado de combate", "Ganaste", null)) {
				battle.set(new Battle());
			} else {

				combatEnded.set(true);
			}

		} else {
			Battle.cpuAttack(selectedPokemon.get());
		}

		if (selectedPokemon.get().getHealth() <= 0) {
			trainer1.get().remove(selectedPokemon.get());
			numberPokemonAlivePlayerLabel.setText("" + trainer1.getSize());
		}
		if (trainer1.size() == 0) {
			if (App.confirm("Resultado de combate", "Perdiste", null)) {
				battle.set(new Battle());
			} else {

				combatEnded.set(true);
			}

		}

	}

	private void cargarBotones(Pokemon pk) {
		attackButton1.textProperty().bind(pk.getAttacks().get(0).nameProperty());
		attack1Label.setText("" + pk.getAttacks().get(0));
		attackButton2.textProperty().bind(pk.getAttacks().get(1).nameProperty());
		attack2Label.setText("" + pk.getAttacks().get(1));
		attackButton3.textProperty().bind(pk.getAttacks().get(2).nameProperty());
		attack3Label.setText("" + pk.getAttacks().get(2));
		attackButton4.textProperty().bind(pk.getAttacks().get(3).nameProperty());
		attack4Label.setText("" + pk.getAttacks().get(3));
	}

	private void ponerMensaje(double vidacpu, double vida) {
		double damage = vidacpu - cpuPokemon.get().getHealth();
		double damage2 = vida - selectedPokemon.get().getHealth();
		attackMessageLabel.setText("ATAQUE: " + selectedPokemon.get().getName() + " con una \ncantidad de: " + damage
				+ "\nDEFENSA: " + cpuPokemon.get().getName() + " con una \ncantidad de: " + damage2);

	}

	public AnchorPane getView() {
		return view;
	}

	public final BooleanProperty finCombateProperty() {
		return this.combatEnded;
	}

	public final boolean isFinCombate() {
		return this.finCombateProperty().get();
	}

	public final void setFinCombate(final boolean finCombate) {
		this.finCombateProperty().set(finCombate);
	}

	public final ObjectProperty<Battle> combateProperty() {
		return this.battle;
	}

	public final Battle getCombate() {
		return this.combateProperty().get();
	}

	public final void setCombate(final Battle combate) {
		this.combateProperty().set(combate);
	}

}
