package dad.pokemonfx.MovimientoFX;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import dad.pokemonfx.gamelop.GameLoop;
import dad.pokemonfx.gamelop.Player;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;

public class Controller implements Initializable {
	
	@FXML
	private BorderPane view;

	@FXML
	private Canvas gc;

	public Controller() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/JFXMovimientoView.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ArrayList<String> input = new ArrayList<>(); 
		view.setOnKeyPressed(e -> {
			String code = e.getCode().toString();
			if (!input.contains(code)) 
				input.add(code);
		});

		view.setOnKeyReleased(e -> {
			String code = e.getCode().toString();
			input.remove(code);
		});
		view.requestFocus();
		gc.setFocusTraversable(true);
		gc.requestFocus();
		GraphicsContext graphicsContext = gc.getGraphicsContext2D(); 
		Player player = new Player(50, 50, 2);

		GameLoop gameLoop = new GameLoop(input, graphicsContext, player);
		gameLoop.start();
	}

	public BorderPane getView() {
		return view;
	}

}
