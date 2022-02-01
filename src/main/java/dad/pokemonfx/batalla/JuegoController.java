package dad.pokemonfx.batalla;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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

public class JuegoController implements Initializable {
	
	private ObjectProperty<Combate> combate = new SimpleObjectProperty<>();
	private ObjectProperty<Pokemon> PokemonSeleccionado = new SimpleObjectProperty<>();
	private ObjectProperty<Pokemon> Pokemoncpu = new SimpleObjectProperty<>();
	private ListProperty<Pokemon> entrenador1 = new SimpleListProperty<>(FXCollections.observableArrayList());
	private ListProperty<Pokemon> entrenador2 = new SimpleListProperty<>(FXCollections.observableArrayList());
	private DoubleProperty vida = new SimpleDoubleProperty();
	private DoubleProperty vidacpu = new SimpleDoubleProperty();
	private StringProperty nombrePokemon = new SimpleStringProperty();
	private StringProperty nombrePokemoncpu = new SimpleStringProperty();
	private BooleanProperty finCombate = new SimpleBooleanProperty();
	private DoubleProperty vidaMaxima = new SimpleDoubleProperty();
	private DoubleProperty vidaMaximacpu = new SimpleDoubleProperty();
	private DoubleProperty vidaResultante = new SimpleDoubleProperty();
	private DoubleProperty vidaResultantecpu = new SimpleDoubleProperty();
	

	@FXML
	private ImageView pokemon1;

	@FXML
	private ImageView pokemon2;

	@FXML
	private ComboBox<Pokemon> comboPokemon;

	@FXML
	private Button ataque1;

	@FXML
	private Button ataque2;

	@FXML
	private Button ataque3;

	@FXML
	private Button ataque4;

	@FXML
	private Label labelNCPU;

	@FXML
	private Label labelNPok;

	@FXML
	private Label ataque1Label;

	@FXML
	private Label ataque2Label;

	@FXML
	private Label ataque3Label;

	@FXML
	private Label ataque4Label;

	@FXML
	private Label mensajeataqueLabel;

	@FXML
	private Label vidalabel;

	@FXML
	private Label labelcontPok;

	@FXML
	private Label labelcontPokcpu;

	@FXML
	private Label vida2label;

	@FXML
	private ProgressBar vida1slider;

	@FXML
	private ProgressBar vida2slider;

	@FXML
	private Label nivelcpu;

	@FXML
	private Label nivelpok;
	@FXML
	private AnchorPane view;

	public JuegoController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Juego.fxml"));
		loader.setController(this);
		loader.load();
	}

	public void initialize(URL location, ResourceBundle resources) {
		finCombate.set(false);
		combate.set(new Combate());
		entrenador1.bind(combate.get().entrenador1Property());
		entrenador2.bind(combate.get().entrenador2Property());
		comboPokemon.itemsProperty().bind(entrenador1);
		comboPokemon.getSelectionModel().selectFirst();
		PokemonSeleccionado.bind(comboPokemon.getSelectionModel().selectedItemProperty());
		Pokemoncpu.set(entrenador2.get(0));
		vida.bind(PokemonSeleccionado.get().healthProperty());
		vidacpu.bind(Pokemoncpu.get().healthProperty());
		vidaMaxima.set(vida.get());
		vidaMaximacpu.set(vidacpu.get());
		vidaResultante.bind(vida);
		vidaResultantecpu.bind(vidacpu);
		vidalabel.textProperty().bindBidirectional(vida, new NumberStringConverter());
		vida2label.textProperty().bindBidirectional(vidacpu, new NumberStringConverter());
		vida1slider.progressProperty().bind(vidaResultante.divide(vidaMaxima));		
		vida2slider.progressProperty().bind(vidaResultantecpu.divide(vidaMaximacpu));
		labelNPok.textProperty().bind(nombrePokemon);
		nombrePokemon.bind(PokemonSeleccionado.get().nameProperty());
		labelNCPU.textProperty().bind(nombrePokemoncpu);
		nombrePokemoncpu.bind(Pokemoncpu.get().nameProperty());
		pokemon1.setImage(PokemonSeleccionado.get().getBack());
		pokemon2.setImage(Pokemoncpu.get().getFront());
		labelcontPok.setText("" + entrenador1.getSize());
		labelcontPokcpu.setText("" + entrenador2.getSize());
		nivelpok.setText("" + PokemonSeleccionado.get().getLevel());
		nivelcpu.setText("" + Pokemoncpu.get().getLevel());
		cargarBotones(PokemonSeleccionado.get());
		PokemonSeleccionado.addListener((o, ov, nv) -> onpokemonchanged(o, ov, nv));
		Pokemoncpu.addListener((o, ov, nv) -> onpokemoncpuchanged(o, ov, nv));
		combate.addListener((o, ov, nv) -> oncombatechanged(o, ov, nv));

	}

	private void oncombatechanged(ObservableValue<? extends Combate> o, Combate ov, Combate nv) {
		if (ov != null) {
			entrenador1.clear();
			entrenador2.unbind();
			comboPokemon.itemsProperty().unbind();
		}
		if (nv != null) {
			Controller.curarPokemones();
			entrenador1.get().addAll(Controller.listMapPokemon.get());
			entrenador2.bind(nv.entrenador2Property());
			comboPokemon.itemsProperty().bind(entrenador1);
			comboPokemon.getSelectionModel().selectFirst();
			Pokemoncpu.set(entrenador2.get(0));
			labelcontPok.setText("" + entrenador1.getSize());
			labelcontPokcpu.setText("" + entrenador2.getSize());
		}

	}

	private void onpokemoncpuchanged(ObservableValue<? extends Pokemon> o, Pokemon ov, Pokemon nv) {
		if (entrenador2.get().size() > 0) {
			if (ov != null) {
				vidacpu.unbind();
				nombrePokemoncpu.unbind();

			}
			if (nv != null) {
				vidacpu.bind(nv.healthProperty());
				nombrePokemoncpu.bind(nv.nameProperty());
				pokemon2.setImage(nv.getFront());
				nivelcpu.setText("" + Pokemoncpu.get().getLevel());
			}

		}
	}

	private void onpokemonchanged(ObservableValue<? extends Pokemon> o, Pokemon ov, Pokemon nv) {
		if (entrenador1.get().size() > 0) {
			if (nv == null) {
				nv = entrenador1.get(0);
				comboPokemon.getSelectionModel().selectFirst();
			}
			if (ov != null) {
				vida.unbind();
				nombrePokemon.unbind();
				ataque1.textProperty().unbind();
				ataque2.textProperty().unbind();
				ataque3.textProperty().unbind();
				ataque4.textProperty().unbind();
			}
			if (nv != null) {
				vida.bind(nv.healthProperty());
				nombrePokemon.bind(nv.nameProperty());
				cargarBotones(nv);
				nivelpok.setText("" + PokemonSeleccionado.get().getLevel());
				pokemon1.setImage(nv.getBack());
			}
		}

	}

	@FXML
	void onataque1(ActionEvent event) {
		double vida = PokemonSeleccionado.get().getHealth();
		double vidacpu = Pokemoncpu.get().getHealth();
		ataqueCombate(PokemonSeleccionado.get().getAttacks().get(0), Pokemoncpu.get());
		ponerMensaje(vidacpu, vida);

	}

	@FXML
	void onataque2(ActionEvent event) {
		double vida = Pokemoncpu.get().getHealth();
		double vidacpu = Pokemoncpu.get().getHealth();
		ataqueCombate(PokemonSeleccionado.get().getAttacks().get(1), Pokemoncpu.get());
		ponerMensaje(vidacpu, vida);
	}

	@FXML
	void onataque3(ActionEvent event) {
		double vida = Pokemoncpu.get().getHealth();
		double vidacpu = Pokemoncpu.get().getHealth();
		ataqueCombate(PokemonSeleccionado.get().getAttacks().get(2), Pokemoncpu.get());
		ponerMensaje(vidacpu, vida);

	}

	@FXML
	void onataque4(ActionEvent event) {
		double vida = Pokemoncpu.get().getHealth();
		double vidacpu = Pokemoncpu.get().getHealth();
		ataqueCombate(PokemonSeleccionado.get().getAttacks().get(3), Pokemoncpu.get());
		ponerMensaje(vidacpu, vida);

	}

	private void ataqueCombate(Ataque ataque, Pokemon pk) {

		Combate.ataque(ataque, pk);
		/*if (pk.getVida() < 100) {
			Pokemoncpu.set(entrenador2.get((int) Math.floor(Math.random() * entrenador2.getSize())));
		}
		*/
		if (pk.getHealth() <= 0) {
			entrenador2.get().remove(Pokemoncpu.get());
			labelcontPokcpu.setText("" + entrenador2.getSize());
			if (entrenador2.get().size() > 0) {
				Pokemoncpu.set(entrenador2.get(0));
			}

		}
		if (entrenador2.size() == 0) {
			if (App.confirm("Resultado de combate", "Ganaste", null)) {
				combate.set(new Combate());
			} else {

				finCombate.set(true);
			}

		} else {
			Combate.ataquecpu(PokemonSeleccionado.get());
		}

		if (PokemonSeleccionado.get().getHealth() <= 0) {
			entrenador1.get().remove(PokemonSeleccionado.get());
			labelcontPok.setText("" + entrenador1.getSize());
		}
		if (entrenador1.size() == 0) {
			if (App.confirm("Resultado de combate", "Perdiste", null)) {
				combate.set(new Combate());
			} else {

				finCombate.set(true);
			}

		}

	}

	private void cargarBotones(Pokemon pk) {
		ataque1.textProperty().bind(pk.getAttacks().get(0).nombreProperty());
		ataque1Label.setText("" + pk.getAttacks().get(0));
		ataque2.textProperty().bind(pk.getAttacks().get(1).nombreProperty());
		ataque2Label.setText("" + pk.getAttacks().get(1));
		ataque3.textProperty().bind(pk.getAttacks().get(2).nombreProperty());
		ataque3Label.setText("" + pk.getAttacks().get(2));
		ataque4.textProperty().bind(pk.getAttacks().get(3).nombreProperty());
		ataque4Label.setText("" + pk.getAttacks().get(3));
	}

	private void ponerMensaje(double vidacpu, double vida) {
		double daño = vidacpu - Pokemoncpu.get().getHealth();
		double daño2 = vida - PokemonSeleccionado.get().getHealth();
		mensajeataqueLabel.setText("ATAQUE: " + PokemonSeleccionado.get().getName() + " con una \ncantidad de: "
				+ daño + "\nDEFENSA: " + Pokemoncpu.get().getName() + " con una \ncantidad de: " + daño2);

	}

	public AnchorPane getView() {
		return view;
	}

	public final BooleanProperty finCombateProperty() {
		return this.finCombate;
	}

	public final boolean isFinCombate() {
		return this.finCombateProperty().get();
	}

	public final void setFinCombate(final boolean finCombate) {
		this.finCombateProperty().set(finCombate);
	}

	public final ObjectProperty<Combate> combateProperty() {
		return this.combate;
	}

	public final Combate getCombate() {
		return this.combateProperty().get();
	}

	public final void setCombate(final Combate combate) {
		this.combateProperty().set(combate);
	}

}
