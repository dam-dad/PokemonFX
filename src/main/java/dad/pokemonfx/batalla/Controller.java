package dad.pokemonfx.batalla;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

import dad.pokemonfx.MovimientoFX.MapController;
import dad.pokemonfx.battlemode.MenuBattleModeController;
import dad.pokemonfx.battlemode.ModoBatallaController;
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

public class Controller implements Initializable {
	private MenuController menucontroller;
	private JuegoController juegoController;
	private MapController mapcontroller;
	private MenuBattleModeController menubattlemodeController;
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
			menucontroller = new MenuController();
			mapcontroller = new MapController();
			juegoController = new JuegoController();
			menubattlemodeController=new MenuBattleModeController();
			view.setCenter(menucontroller.getView());
		} catch (IOException e) {
			e.printStackTrace();
		}
		menucontroller.BotonPulsadoProperty().addListener((o, ov, nv) -> sepulsoboton(o, ov, nv));
		menucontroller.botonCombatePulsadoProperty().addListener((o, ov, nv) -> sepulsobotonModoBatalla(o, ov, nv));
		mapcontroller.getGameLoop().hayBatallaProperty().addListener((o, ov, nv) -> hayBatalla(o, ov, nv));
		juegoController.finCombateProperty().addListener((o, ov, nv) -> finCombate(o, ov, nv));
		juegoController.getCombate().eleccionController.botonPulsadoProperty()
				.addListener((o, ov, nv) -> botonEleccion(o, ov, nv));
		
	}

	private void sepulsobotonModoBatalla(ObservableValue<? extends Boolean> o, Boolean ov, Boolean nv) {
		menucontroller.mediaPlayer.stop();
		view.setCenter(menubattlemodeController.getView());
		// battlemode song
		try {
			media = new Media((getClass().getResource("/music/Main_Menu_Battlemode.mp3")).toURI().toString());
		} catch (URISyntaxException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		mediaPlayer = new MediaPlayer(media);
		mediaPlayer.setVolume(0.03);
		mediaPlayer.play();
	}

	private void botonEleccion(ObservableValue<? extends Boolean> o, Boolean ov, Boolean nv) {
		view.setCenter(mapcontroller.getView());
		listMapPokemon.set(juegoController.getCombate().getEleccionController().getEntrenador());
		menucontroller.mediaPlayer.stop();
		// world song
		try {
			media = new Media((getClass().getResource("/music/Littleroot_Town.mp3")).toURI().toString());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		mediaPlayer = new MediaPlayer(media);
		mediaPlayer.setVolume(0.03);
		mediaPlayer.play();

	}

	private void finCombate(ObservableValue<? extends Boolean> o, Boolean ov, Boolean nv) {
		mapcontroller.getGameLoop().setHayBatalla(false);
		view.setCenter(mapcontroller.getView());
		juegoController.setFinCombate(false);
		mediaPlayer.stop();
		// world song
		try {
			media = new Media((getClass().getResource("/music/Littleroot_Town.mp3")).toURI().toString());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		mediaPlayer = new MediaPlayer(media);
		mediaPlayer.setVolume(0.03);
		mediaPlayer.play();

	}

	private void hayBatalla(ObservableValue<? extends Boolean> o, Boolean ov, Boolean nv) {
		juegoController.setCombate(new Combate());
		view.setCenter(juegoController.getView());
		mediaPlayer.stop();
		// battle song
		try {
			media = new Media((getClass().getResource("/music/Battle_Song.mp3")).toURI().toString());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		mediaPlayer = new MediaPlayer(media);
		mediaPlayer.setVolume(0.021);
		mediaPlayer.play();
	}

	private void sepulsoboton(ObservableValue<? extends Boolean> o, Boolean ov, Boolean nv) {
		view.setCenter(juegoController.getCombate().getEleccionController().getView());
	}

	public static void curarPokemones() {
		for (int i = 0; i < listMapPokemon.getSize(); i++) {
			listMapPokemon.get(i).setVida(400 + listMapPokemon.get(i).getNivel() * 0.5);
		}
	}

	public BorderPane getView() {
		return view;
	}

}
