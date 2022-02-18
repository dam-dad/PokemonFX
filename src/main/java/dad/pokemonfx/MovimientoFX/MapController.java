package dad.pokemonfx.MovimientoFX;

import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

import dad.pokemonfx.gameloop.GameLoop;
import dad.pokemonfx.gameloop.Player;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;

public class MapController implements Initializable {

	private GameLoop gameLoop;
	Player player;
	GraphicsContext graphicsContext;

	@FXML
	private BorderPane view;

	@FXML
	private Canvas canvas;

	public MapController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/JFXMovimientoView.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		Set<KeyCode> input = new HashSet<>();
		
		canvas.setOnKeyPressed(e -> input.add(e.getCode()));
		canvas.setOnKeyReleased(e -> input.remove(e.getCode()));
		canvas.setFocusTraversable(true);
		canvas.requestFocus();
		
		graphicsContext = canvas.getGraphicsContext2D();
		
		player = new Player(48, 48, 5);
		
		gameLoop = new GameLoop(input, graphicsContext, player);
		gameLoop.start();

	}


	public BorderPane getView() {
		return view;
	}

}
