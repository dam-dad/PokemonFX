package dad.pokemonfx.batalla;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import dad.pokemonfx.battlemode.BattleModeController;
import dad.pokemonfx.MovimientoFX.MapController;
import dad.pokemonfx.battlemode.MenuBattleModeController;
import dad.pokemonfx.fichero.GenerarPDF;
import dad.pokemonfx.music.MusicThread;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import net.sf.jasperreports.engine.JRException;

public class Controller implements Initializable {
	
	// Musica
	private static MusicThread music;
	
	private MenuController menuController;
	private BattleController battleController;
	private MapController mapController;
	private MenuBattleModeController menuBattleModeController;
	private BattleModeController battleModeController;
	public static ListProperty<Pokemon> listMapPokemon = new SimpleListProperty<>(FXCollections.observableArrayList());
	Media media;
	MediaPlayer mediaPlayer;

	@FXML
	private BorderPane view;

	public Controller() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Controller.fxml"));
		loader.setController(this);
		loader.load();
	}

	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			menuController = new MenuController();
			mapController = new MapController();
			battleController = new BattleController();
			menuBattleModeController = new MenuBattleModeController();
			battleModeController = new BattleModeController();
			view.setCenter(menuController.getView());
		} catch (IOException e) {
			e.printStackTrace();
		}
		menuController.buttonPressedProperty().addListener((o, ov, nv) -> sepulsoboton(o, ov, nv));
		menuController.battleButtonPressedProperty().addListener((o, ov, nv) -> sepulsobotonModoBatalla(o, ov, nv));
		menuController.pdfButtonPressedProperty().addListener((o, ov, nv) -> sepulsobotonPDF(o, ov, nv));
//		mapController.getGameLoop().hayBatallaProperty().addListener((o, ov, nv) -> hayBatalla(o, ov, nv));
		battleController.finCombateProperty().addListener((o, ov, nv) -> finCombate(o, ov, nv));
		battleController.getCombate().chooseController.buttonPressedProperty()
				.addListener((o, ov, nv) -> botonEleccion(o, ov, nv));
		menuBattleModeController.botonIrCombateProperty().addListener((o, ov, nv) -> irModoCombate(o, ov, nv));
		menuBattleModeController.botonVolverProperty().addListener((o, ov, nv) -> battlevolverAtras(o, ov, nv));
		battleModeController.finCombateProperty().addListener((o, ov, nv) -> battlevolverAtras(o, ov, nv));
		// Music menu
		music = new MusicThread("Menu_Song");
		music.play();

	}

	private void sepulsobotonPDF(ObservableValue<? extends Boolean> o, Boolean ov, Boolean nv) {
		try {
			GenerarPDF.generarPdf(battleController.getCombate().getChooseController().getListPokemon());
		} catch (JRException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	private void irModoCombate(ObservableValue<? extends Boolean> o, Boolean ov, Boolean nv) {
		battleModeController.setFinCombate(false);
		view.setCenter(battleModeController.getView());
		// Music batalla
		music.pause();
		music = new MusicThread("Battle_Song");
		music.play();
	}

	private void battlevolverAtras(ObservableValue<? extends Boolean> o, Boolean ov, Boolean nv) {
		menuController.setBattleButtonPressed(false);
		view.setCenter(menuController.getView());
		//Musica menu
		music.pause();
		music = new MusicThread("Menu_Song");
		music.play();
	}

	private void sepulsobotonModoBatalla(ObservableValue<? extends Boolean> o, Boolean ov, Boolean nv) {
		menuBattleModeController.setBotonIrCombate(false);
		menuBattleModeController.setBotonVolver(false);
		view.setCenter(menuBattleModeController.getView());
		// Music battlemode
		music.pause();
		music = new MusicThread("Main_Menu_Battlemode");
		music.play();
	}

	private void botonEleccion(ObservableValue<? extends Boolean> o, Boolean ov, Boolean nv) {
		view.setCenter(mapController.getView());
		listMapPokemon.set(battleController.getCombate().getChooseController().getTrainer());
		// Music mundo
		music.pause();
		music = new MusicThread("Littleroot_Town");
		music.play();
	}

	private void finCombate(ObservableValue<? extends Boolean> o, Boolean ov, Boolean nv) {
//		mapController.getGameLoop().setHayBatalla(false);
		view.setCenter(mapController.getView());
		battleController.setFinCombate(false);
		// Music mundo
		music.pause();
		music = new MusicThread("Littleroot_Town");
		music.play();
	}

	private void hayBatalla(ObservableValue<? extends Boolean> o, Boolean ov, Boolean nv) {
		battleController.setCombate(new Battle());
		view.setCenter(battleController.getView());
		// Music batalla
		music.pause();
		music = new MusicThread("Battle_Song");
		music.play();
	}

	private void sepulsoboton(ObservableValue<? extends Boolean> o, Boolean ov, Boolean nv) {
		view.setCenter(battleController.getCombate().getChooseController().getView());
	}

	public static void curarPokemones() {
		listMapPokemon.stream().forEach(pokemon -> {
			pokemon.setHealth(400 + pokemon.getLevel() * 0.5);
		});
	}

	public BorderPane getView() {
		return view;
	}

}
