package dad.pokemonfx.batalla;

import dad.pokemonfx.music.MusicThread;
import javafx.application.Application;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCombination;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Clase donde se controla la escena del programa hereda de aplicacion
 */
public class App extends Application {

	private static MusicThread music;

	Controller controller;
	private static Stage primaryStage;
	static Scene mainScene;

	public void start(Stage primaryStage) throws Exception {

		/**
		 * La imagen del cursor
		 */
		Image cursorImage = new Image("/images/pokeball-cursor.png");

		App.primaryStage = primaryStage;
		controller = new Controller();
		mainScene = new Scene(controller.getView());
		Font.loadFont(getClass().getResourceAsStream("/font/pokemon_pixel_font.ttf"), 14);
		primaryStage.setTitle("PokemonFX");
		primaryStage.getIcons().add(new Image("/images/pokemonfx-main-icon-32x32.png"));
		primaryStage.setResizable(false);
		primaryStage.setFullScreen(false);
		primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
		primaryStage.setScene(mainScene);

		/**
		 * Añadimos el cursor a la escena
		 */
		primaryStage.getScene()
				.setCursor(new ImageCursor(cursorImage, cursorImage.getWidth() / 2, cursorImage.getHeight() / 2));
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

	public static Stage getPrimaryStage() {
		return primaryStage;
	}

	public static boolean confirm(String title, String header, String content) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.initOwner(primaryStage);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(content);
		return alert.showAndWait().get().equals(ButtonType.OK);
	}

	public static void error(String header, String content) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.initOwner(primaryStage);
		alert.setTitle("Error");
		alert.setHeaderText(header);
		alert.setContentText(content);
		alert.showAndWait();
	}

	public static void info(String title, String header, String content) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.initOwner(primaryStage);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(content);
		alert.showAndWait();
	}

	public static void playMusic(String file) {
		music = new MusicThread(file);
		music.play();
	}

	public static void stopMusic() {
		music.pause();
	}
}
