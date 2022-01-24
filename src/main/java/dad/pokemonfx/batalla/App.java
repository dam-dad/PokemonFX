package dad.pokemonfx.batalla;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class App extends Application {

	Controller controller;
	JuegoController juego;
	private static Stage primaryStage;
	private static Stage secondStage;
	static Scene mainScene;
	static Scene juegoScene;

	public void start(Stage primaryStage) throws Exception {

		App.primaryStage = primaryStage;
		controller = new Controller();
		juego=new JuegoController();
		mainScene = new Scene(controller.getView());
		juegoScene=new Scene(juego.getView());
		primaryStage.setTitle("Batalla Pokemon");
		primaryStage.setFullScreen(true);
		primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
		primaryStage.setScene(mainScene);
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}

	public static Stage getPrimaryStage() {
		return primaryStage;
	}

	public static boolean confirm(String titulo, String cabezero, String Contenido) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.initOwner(primaryStage);
		alert.setTitle(titulo);
		alert.setHeaderText(cabezero);
		alert.setContentText(Contenido);
		return alert.showAndWait().get().equals(ButtonType.OK);
	}

	public static void error(String cabezero, String Contenido) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.initOwner(primaryStage);
		alert.setTitle("Error");
		alert.setHeaderText(cabezero);
		alert.setContentText(Contenido);
		alert.showAndWait();
	}

	public static void info(String titulo, String cabezero, String Contenido) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.initOwner(primaryStage);
		alert.setTitle(titulo);
		alert.setHeaderText(cabezero);
		alert.setContentText(Contenido);
		alert.showAndWait();
	}

	public static void gotoMain(BorderPane pane) {
		secondStage.setScene(new Scene(pane));
		App.primaryStage = App.secondStage;
		

	}
}
