package dad.pokemonfx.movimiento1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;

public class App extends Application {

	private static Stage primaryStage;
   Controller controler;

	public void start(Stage primaryStage) throws Exception {
		controler=new Controller();
		App.primaryStage = primaryStage;
		Scene scene = new Scene(controler.getView());
		primaryStage.setTitle("MovimientoFX");
		primaryStage.setFullScreen(false);
		primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
		primaryStage.setScene(scene);
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

}
