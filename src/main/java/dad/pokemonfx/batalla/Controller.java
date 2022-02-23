package dad.pokemonfx.batalla;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import dad.pokemonfx.battlemode.BattleModeController;
import dad.pokemonfx.MovimientoFX.MapController;
import dad.pokemonfx.battlemode.MenuBattleModeController;
import dad.pokemonfx.fichero.GenerarPDF;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import net.sf.jasperreports.engine.JRException;

public class Controller implements Initializable {
	/**
	 * Clase que se encarga de enlazar todos los controladores
	 * @param controladores
	 */
	
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
		menuController.controlMusicButtonPressedProperty().addListener((o, ov, nv) -> sepulsobotonControlMusica(o, ov, nv));
		mapController.getGameLoop().hayBatallaProperty().addListener((o, ov, nv) -> hayBatalla(o, ov, nv));
		battleController.finCombateProperty().addListener((o, ov, nv) -> finCombate(o, ov, nv));
		battleController.getCombate().chooseController.pulsarJugarButtonProperty()
				.addListener((o, ov, nv) -> botonEleccion(o, ov, nv));
		battleController.getCombate().chooseController.pulsarCancelarButtonProperty()
		.addListener((o, ov, nv) -> botonEleccionAtras(o, ov, nv));
		menuBattleModeController.botonIrCombateProperty().addListener((o, ov, nv) -> battleModeIniciarCombate(o, ov, nv));
		menuBattleModeController.botonVolverProperty().addListener((o, ov, nv) -> battleModeVolverAtras(o, ov, nv));
		battleModeController.finCombateProperty().addListener((o, ov, nv) -> battleModeVolverAtras(o, ov, nv));
		// Music menu
		App.playMusic("Menu_Song");

	}

	private void sepulsobotonControlMusica(ObservableValue<? extends Boolean> o, Boolean ov, Boolean nv) {
		menuController.setControlMusicButtonPressed(false);
		
	}


	private void sepulsobotonPDF(ObservableValue<? extends Boolean> o, Boolean ov, Boolean nv) {
		Task<Void> tarea = new Task<Void>() {
			@Override
			public Void call() {
				try {
					try {
						GenerarPDF.generarPdf(battleController.getCombate().getChooseController().getListPokemon());
					} catch (JRException | IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				} catch (Exception e) {

				}
				return null;
			}
		};
		new Thread(tarea).start();
		
	}

	private void battleModeIniciarCombate(ObservableValue<? extends Boolean> o, Boolean ov, Boolean nv) {
		battleModeController.setFinCombate(false);
		view.setCenter(battleModeController.getView());
		// Music batalla
		App.stopMusic();
		App.playMusic("Battlemode_Battle_Song");
	}

	private void battleModeVolverAtras(ObservableValue<? extends Boolean> o, Boolean ov, Boolean nv) {
		menuController.setBattleButtonPressed(false);
		view.setCenter(menuController.getView());
		//Musica menu
		App.stopMusic();
		App.playMusic("Menu_Song");
	}

	private void sepulsobotonModoBatalla(ObservableValue<? extends Boolean> o, Boolean ov, Boolean nv) {
		menuBattleModeController.setBotonIrCombate(false);
		menuBattleModeController.setBotonVolver(false);
		view.setCenter(menuBattleModeController.getView());
		// Music battlemode
		App.stopMusic();
		App.playMusic("Main_Menu_Battlemode");
	}

	private void botonEleccion(ObservableValue<? extends Boolean> o, Boolean ov, Boolean nv) {
		view.setCenter(mapController.getView());
		listMapPokemon.set(battleController.getCombate().getChooseController().getTrainer());
		// Music mundo
		App.stopMusic();
		App.playMusic("Littleroot_Town");
	}
	
	private void botonEleccionAtras(ObservableValue<? extends Boolean> o, Boolean ov, Boolean nv) {
		menuController.setButtonPressed(false);
		view.setCenter(menuController.getView());
		// Music mundo
		App.stopMusic();
		App.playMusic("Menu_Song");
	}

	private void finCombate(ObservableValue<? extends Boolean> o, Boolean ov, Boolean nv) {
        menuController.setButtonPressed(false);
		view.setCenter(menuController.getView());
		// Music mundo
		App.stopMusic();
		App.playMusic("Littleroot_Town");
	}

	private void hayBatalla(ObservableValue<? extends Boolean> o, Boolean ov, Boolean nv) {
		battleController.setCombate(new Battle());
		view.setCenter(battleController.getView());
		// Music batalla
		App.stopMusic();
		App.playMusic("Battle_Song");
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
