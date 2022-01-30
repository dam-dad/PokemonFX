package dad.pokemonfx.batalla;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

public class EleccionController implements Initializable {

	private static ListProperty<Pokemon> listpokemon = new SimpleListProperty<>(FXCollections.observableArrayList());
	private static ListProperty<Pokemon> entrenador = new SimpleListProperty<>(FXCollections.observableArrayList());
	private BooleanProperty botonPulsado = new SimpleBooleanProperty();
	private BooleanProperty listanoLlena = new SimpleBooleanProperty();
	private ObjectProperty<Pokemon> PokemonSeleccionado = new SimpleObjectProperty<>();

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

	public EleccionController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MenuEleccionView.fxml"));
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
				pok1.setImage(PokemonSeleccionado.get().getCpu());
				break;
			case 2:
				pok2.setImage(PokemonSeleccionado.get().getCpu());
				break;
			case 3:
				pok3.setImage(PokemonSeleccionado.get().getCpu());
				break;
			case 4:
				pok4.setImage(PokemonSeleccionado.get().getCpu());
				break;
			case 5:
				pok5.setImage(PokemonSeleccionado.get().getCpu());
				break;
			case 6:
				pok6.setImage(PokemonSeleccionado.get().getCpu());
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
	void onjugarButton(ActionEvent event) {
		botonPulsado.set(true);
	}

	public AnchorPane getView() {
		return view;
	}

	public final ListProperty<Pokemon> listpokemonProperty() {
		return EleccionController.listpokemon;
	}

	public final ObservableList<Pokemon> getListpokemon() {
		return this.listpokemonProperty().get();
	}

	public final void setListpokemon(final ObservableList<Pokemon> listpokemon) {
		this.listpokemonProperty().set(listpokemon);
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

	public final ListProperty<Pokemon> entrenadorProperty() {
		return EleccionController.entrenador;
	}

	public final ObservableList<Pokemon> getEntrenador() {
		return this.entrenadorProperty().get();
	}

	public final void setEntrenador(final ObservableList<Pokemon> entrenador) {
		this.entrenadorProperty().set(entrenador);
	}

}
