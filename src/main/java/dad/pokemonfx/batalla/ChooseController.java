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

public class ChooseController implements Initializable {
	
	private static final int TOTAL_POKEMONS = 6;

	// model
	
	private ListProperty<Pokemon> listPokemon = new SimpleListProperty<>(FXCollections.observableArrayList());
	private ListProperty<Pokemon> trainer = new SimpleListProperty<>(FXCollections.observableArrayList());
	private BooleanProperty pressButton = new SimpleBooleanProperty();
	private BooleanProperty listPokemonFull = new SimpleBooleanProperty();
	private ObjectProperty<Pokemon> selectedPokemon = new SimpleObjectProperty<>();
	
	// view
	
	private List<ImageView> pokemons;

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
	private ListView<Pokemon> listViewPokemon;

	@FXML
	private AnchorPane view;

	public ChooseController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MenuEleccionView.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		pressButton.set(false);
		listViewPokemon.itemsProperty().bind(listPokemon);
		selectedPokemon.bind(listViewPokemon.getSelectionModel().selectedItemProperty());
		playButton.disableProperty().bind(listPokemonFull);
		pokemons = Arrays.asList(imagePokemon1, imagePokemon2, imagePokemon3, imagePokemon4, imagePokemon5, imagePokemon6);
		
		listPokemonFull.bind(listPokemon.sizeProperty().isNotEqualTo(TOTAL_POKEMONS));
		
		
	}

	@FXML
	void onClicked(MouseEvent event) {
		if (trainer.getSize() < TOTAL_POKEMONS) {
			
			trainer.add(selectedPokemon.get());
			
			pokemons.get(trainer.getSize() -1)
				.setImage(selectedPokemon.get().getFront());
			
			listViewPokemon.getItems().remove(listViewPokemon.getSelectionModel().getSelectedIndex());
			
			
		}else {
			App.info("Error", "Ya has elegido " + TOTAL_POKEMONS + " Pokemon", null);
		}

	}

	@FXML
	void onPlayButton(ActionEvent event) {
		pressButton.set(true);
	}

	public AnchorPane getView() {
		return view;
	}

	public final ListProperty<Pokemon> listPokemonProperty() {
		return listPokemon;
	}

	public final ObservableList<Pokemon> getListPokemon() {
		return this.listPokemonProperty().get();
	}

	public final void setListpokemon(final ObservableList<Pokemon> listPokemon) {
		this.listPokemonProperty().set(listPokemon);
	}

	public final BooleanProperty buttonPressedProperty() {
		return this.pressButton;
	}

	public final boolean isButtonPressed() {
		return this.buttonPressedProperty().get();
	}

	public final void setButtonPressed(final boolean buttonPressed) {
		this.buttonPressedProperty().set(buttonPressed);
	}

	public final ListProperty<Pokemon> trainerProperty() {
		return trainer;
	}

	public final ObservableList<Pokemon> getTrainer() {
		return this.trainerProperty().get();
	}

	public final void setTrainer(final ObservableList<Pokemon> trainer) {
		this.trainerProperty().set(trainer);
	}

}
