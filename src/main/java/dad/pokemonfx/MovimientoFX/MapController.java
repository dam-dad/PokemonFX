package dad.pokemonfx.MovimientoFX;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import dad.pokemonfx.gamelop.GameLoop;
import dad.pokemonfx.gamelop.Player;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;

public class MapController implements Initializable {

	private BooleanProperty hayBatalla = new SimpleBooleanProperty();

	@FXML
	private BorderPane view;

	@FXML
	private Canvas gc;

	public MapController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/JFXMovimientoView.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		hayBatalla.set(false);
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
		gameLoop.hayBatallaProperty().addListener((o, ov, nv) -> hayBatalla(o, ov, nv));
	}

	private void hayBatalla(ObservableValue<? extends Boolean> o, Boolean ov, Boolean nv) {
		hayBatalla.set(true);
	}

	public BorderPane getView() {
		return view;
	}

	public BooleanProperty hayBatallaProperty() {
		return this.hayBatalla;
	}

	public boolean isHayBatalla() {
		return this.hayBatallaProperty().get();
	}

	public void setHayBatalla(final boolean hayBatalla) {
		this.hayBatallaProperty().set(hayBatalla);
	}

}
