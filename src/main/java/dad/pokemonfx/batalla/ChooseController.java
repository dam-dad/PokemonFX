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
import javafx.scene.layout.GridPane;

public class ChooseController implements Initializable {

	private static final int TOTAL_POKEMONS = 6;

	// model

	private ListProperty<Pokemon> listPokemon = new SimpleListProperty<>(FXCollections.observableArrayList());
	private ListProperty<Pokemon> trainer = new SimpleListProperty<>(FXCollections.observableArrayList());
	private BooleanProperty pulsarJugarButton = new SimpleBooleanProperty();
	private BooleanProperty pulsarCancelarButton = new SimpleBooleanProperty();
	private ObjectProperty<Pokemon> selectedPokemon = new SimpleObjectProperty<>();

	// view

	private List<ImageView> pokemonImages;

	@FXML
	private ImageView imagePokemon1;

	@FXML
	private ImageView imagePokemon2;

	@FXML
	private ImageView imagePokemon3;

	@FXML
	private ImageView imagePokemon4;

	@FXML
	private ImageView imagePokemon5;

	@FXML
	private ImageView imagePokemon6;

	@FXML
	private Button playButton;
	
	@FXML
	private Button cancelButton;

	@FXML
	private ListView<Pokemon> listViewPokemon;

	@FXML
	private GridPane view;

	public ChooseController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MenuEleccionView.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		pulsarJugarButton.set(false);
		pulsarCancelarButton.set(false);

		listViewPokemon.itemsProperty().bind(listPokemon);

		selectedPokemon.bind(listViewPokemon.getSelectionModel().selectedItemProperty());

		playButton.disableProperty().bind(trainer.sizeProperty().isNotEqualTo(TOTAL_POKEMONS));

		pokemonImages = Arrays.asList(imagePokemon1, imagePokemon2, imagePokemon3, imagePokemon4, imagePokemon5, imagePokemon6);

	}

	@FXML
	void onClicked(MouseEvent event) {

		if (selectedPokemon.get() != null) {

			Pokemon selected = selectedPokemon.get();

			if (trainer.getSize() < TOTAL_POKEMONS) {

				listPokemon.remove(selected);
				trainer.add(selected);
				pokemonImages.get(trainer.getSize() - 1).setImage(selected.getFront());

			} else {
				
				App.info("Error", "Ya has elegido " + TOTAL_POKEMONS + " Pokemon", null);
				
			}
		}
	}

	@FXML
	void onPlayButton(ActionEvent event) {
		pulsarJugarButton.set(true);
	}
	
	@FXML
    void onCancelButton(ActionEvent event) {
		pulsarCancelarButton.set(true);
    }

	public GridPane getView() {
		return view;
	}

	public final ListProperty<Pokemon> listPokemonProperty() {
		return this.listPokemon;
	}
	

	public final ObservableList<Pokemon> getListPokemon() {
		return this.listPokemonProperty().get();
	}
	

	public final void setListPokemon(final ObservableList<Pokemon> listPokemon) {
		this.listPokemonProperty().set(listPokemon);
	}
	

	public final ListProperty<Pokemon> trainerProperty() {
		return this.trainer;
	}
	

	public final ObservableList<Pokemon> getTrainer() {
		return this.trainerProperty().get();
	}
	

	public final void setTrainer(final ObservableList<Pokemon> trainer) {
		this.trainerProperty().set(trainer);
	}
	

	public final BooleanProperty pulsarJugarButtonProperty() {
		return this.pulsarJugarButton;
	}
	

	public final boolean isPulsarJugarButton() {
		return this.pulsarJugarButtonProperty().get();
	}
	

	public final void setPulsarJugarButton(final boolean pulsarJugarButton) {
		this.pulsarJugarButtonProperty().set(pulsarJugarButton);
	}
	

	public final BooleanProperty pulsarCancelarButtonProperty() {
		return this.pulsarCancelarButton;
	}
	

	public final boolean isPulsarCancelarButton() {
		return this.pulsarCancelarButtonProperty().get();
	}
	

	public final void setPulsarCancelarButton(final boolean pulsarCancelarButton) {
		this.pulsarCancelarButtonProperty().set(pulsarCancelarButton);
	}
	

	public final ObjectProperty<Pokemon> selectedPokemonProperty() {
		return this.selectedPokemon;
	}
	

	public final Pokemon getSelectedPokemon() {
		return this.selectedPokemonProperty().get();
	}
	

	public final void setSelectedPokemon(final Pokemon selectedPokemon) {
		this.selectedPokemonProperty().set(selectedPokemon);
	}

}
