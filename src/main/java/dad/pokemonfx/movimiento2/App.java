package dad.pokemonfx.movimiento2;

import java.util.ArrayList;


import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

public class App extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		Group root = new Group();
		Scene scene = new Scene(root);
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("PokemonFX");

		Canvas canvas = new Canvas(720, 480); 
		root.getChildren().add(canvas);

		ArrayList<String> input = new ArrayList<>(); 

		scene.setOnKeyPressed(e -> {
			String code = e.getCode().toString();
			if (!input.contains(code)) 
				input.add(code);
		});

		scene.setOnKeyReleased(e -> {
			String code = e.getCode().toString();
			input.remove(code);
		});

		
		GraphicsContext graphicsContext = canvas.getGraphicsContext2D(); 
		Player player = new Player(50, 50, 2);

		GameLoop gameLoop = new GameLoop(input, graphicsContext, player);
		gameLoop.start();

		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);

	}

}
