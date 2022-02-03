package dad.pokemonfx.batalla;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class EleccionController implements Initializable {
	
	private static final int TOTAL_POKEMONS = 6;

	// model
	
	private ListProperty<Pokemon> listpokemon = new SimpleListProperty<>(FXCollections.observableArrayList());
	private ListProperty<Pokemon> entrenador = new SimpleListProperty<>(FXCollections.observableArrayList());
	private BooleanProperty botonPulsado = new SimpleBooleanProperty();
	private BooleanProperty listaLlena = new SimpleBooleanProperty();
	private ObjectProperty<Pokemon> pokemonSeleccionado = new SimpleObjectProperty<>();
	
	// view
	
	private List<ImageView> poks;

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
	private ListView<Pokemon> pokemonListView;

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
		pokemonListView.itemsProperty().bind(listpokemon);
		pokemonSeleccionado.bind(pokemonListView.getSelectionModel().selectedItemProperty());
		jugarButton.disableProperty().bind(listaLlena);
		poks = Arrays.asList(pok1, pok2, pok3, pok4, pok5, pok6);
		
		listaLlena.bind(listpokemon.sizeProperty().isEqualTo(TOTAL_POKEMONS));
		
		
	}

	@FXML
	void onClicked(MouseEvent event) {
		if (entrenador.getSize() < TOTAL_POKEMONS) {
			
			entrenador.add(pokemonSeleccionado.get());
			
			poks.get(entrenador.getSize())
				.setImage(pokemonSeleccionado.get().getFront());
			
			pokemonListView.getItems().remove(pokemonListView.getSelectionModel().getSelectedIndex());
			
			
		}else {
			App.info("Error", "Ya has elegido " + TOTAL_POKEMONS + " Pokemon", null);
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
		return listpokemon;
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
		return entrenador;
	}

	public final ObservableList<Pokemon> getEntrenador() {
		return this.entrenadorProperty().get();
	}

	public final void setEntrenador(final ObservableList<Pokemon> entrenador) {
		this.entrenadorProperty().set(entrenador);
	}

}
