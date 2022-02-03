package dad.pokemonfx.battlemode;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dad.pokemonfx.batalla.App;
import dad.pokemonfx.batalla.Pokemon;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ChooseBattleModeController implements Initializable {

	private static ListProperty<Pokemon> listpokemon = new SimpleListProperty<>(FXCollections.observableArrayList());
	private static ListProperty<Pokemon> entrenador = new SimpleListProperty<>(FXCollections.observableArrayList());
	private BooleanProperty botonPulsado = new SimpleBooleanProperty();
	private BooleanProperty listanoLlena = new SimpleBooleanProperty();
	private ObjectProperty<Pokemon> PokemonSeleccionado = new SimpleObjectProperty<>();
	BattleModeController modobatallaController;
	Stage modobatallaStage;

	@FXML
	private ImageView pok1;

	@FXML
	private ImageView pok2;

	@FXML
	private ImageView pok3;

	@FXML
	private ImageView pok4;

	@FXML
	private ImageView pok5;

	@FXML
	private ImageView pok6;

	@FXML
	private Button jugarButton;

	@FXML
	private ListView<Pokemon> listPokemon;

	@FXML
	private AnchorPane view;

	public ChooseBattleModeController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MenuEleccionModoBatalla.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		botonPulsado.set(false);
		listanoLlena.set(true);
		listPokemon.itemsProperty().bind(listpokemon);
		PokemonSeleccionado.bind(listPokemon.getSelectionModel().selectedItemProperty());
		jugarButton.disableProperty().bind(listanoLlena);
	}

	@FXML
	void onClicked(MouseEvent event) {
		if (entrenador.getSize() < 6) {
			entrenador.add(PokemonSeleccionado.get());
			switch (entrenador.getSize()) {
			case 1:
				pok1.setImage(PokemonSeleccionado.get().getFront());
				break;
			case 2:
				pok2.setImage(PokemonSeleccionado.get().getFront());
				break;
			case 3:
				pok3.setImage(PokemonSeleccionado.get().getFront());
				break;
			case 4:
				pok4.setImage(PokemonSeleccionado.get().getFront());
				break;
			case 5:
				pok5.setImage(PokemonSeleccionado.get().getFront());
				break;
			case 6:
				pok6.setImage(PokemonSeleccionado.get().getFront());
				break;

			default:
				break;
			}

			listPokemon.getItems().remove(listPokemon.getSelectionModel().getSelectedIndex());
			if (entrenador.getSize() > 5) {
				listanoLlena.set(false);
			}
		}else {
			App.info("Error", "Ya has elegido 6 Pokemon", null);
		}

	}

	@FXML
	void onjugarButton(ActionEvent event) throws IOException {
		modobatallaController = new BattleModeController();
		Scene scene = new Scene(modobatallaController.getView());
		modobatallaStage = new Stage();
		modobatallaStage.setScene(scene);
		modobatallaStage.show();
	}

	public AnchorPane getView() {
		return view;
	}

	public final ListProperty<Pokemon> listpokemonProperty() {
		return ChooseBattleModeController.listpokemon;
	}
	

	public final ObservableList<Pokemon> getListpokemon() {
		return this.listpokemonProperty().get();
	}
	

	public final void setListpokemon(final ObservableList<Pokemon> listpokemon) {
		this.listpokemonProperty().set(listpokemon);
	}
	

	public final ListProperty<Pokemon> entrenadorProperty() {
		return ChooseBattleModeController.entrenador;
	}
	

	public final ObservableList<Pokemon> getEntrenador() {
		return this.entrenadorProperty().get();
	}
	

	public final void setEntrenador(final ObservableList<Pokemon> entrenador) {
		this.entrenadorProperty().set(entrenador);
	}
	

	public final BooleanProperty botonPulsadoProperty() {
		return this.botonPulsado;
	}
	

	public final boolean isBotonPulsado() {
		return this.botonPulsadoProperty().get();
	}
	

	public final void setBotonPulsado(final boolean botonPulsado) {
		this.botonPulsadoProperty().set(botonPulsado);
	}
	

	public final BooleanProperty listanoLlenaProperty() {
		return this.listanoLlena;
	}
	

	public final boolean isListanoLlena() {
		return this.listanoLlenaProperty().get();
	}
	

	public final void setListanoLlena(final boolean listanoLlena) {
		this.listanoLlenaProperty().set(listanoLlena);
	}
	

	public final ObjectProperty<Pokemon> PokemonSeleccionadoProperty() {
		return this.PokemonSeleccionado;
	}
	

	public final Pokemon getPokemonSeleccionado() {
		return this.PokemonSeleccionadoProperty().get();
	}
	

	public final void setPokemonSeleccionado(final Pokemon PokemonSeleccionado) {
		this.PokemonSeleccionadoProperty().set(PokemonSeleccionado);
	}

}

