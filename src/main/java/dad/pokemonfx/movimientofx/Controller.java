package dad.pokemonfx.movimientofx;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

public class Controller implements Initializable {
	Image entrenador = new Image("file:src/main/resources/images/boy_down_1.png");
	int movimiento;
	int velocidad=4;
	int posx = 100;
	int posy = 100;
	String direccion = "ABAJO";
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
		GraphicsContext gcc = gc.getGraphicsContext2D();
		Timeline tl = new Timeline(new KeyFrame(Duration.millis(10), e -> run(gcc)));
		tl.setCycleCount(Timeline.INDEFINITE);
		view.requestFocus();
		gc.setFocusTraversable(true);
		gc.requestFocus();
		tl.play();
	}

	private void run(GraphicsContext gcc) {
		//gcc.drawImage(new Image("file:src/main/resources/images/water.png"), 0, 0);
		if (direccion.equals("ARRIBA")) {
			movimiento++;
			if (movimiento % 2 == 0) {
				entrenador = new Image("file:src/main/resources/images/boy_up_1.png");
			} else {
				entrenador = new Image("file:src/main/resources/images/boy_up_2.png");
			}
			posy = posy - velocidad;
			direccion = "";
			gcc.clearRect(posx, posy, posy, posx);
		}
		if (direccion.equals("ABAJO")) {
			movimiento++;
			if (movimiento % 2 == 0) {
				entrenador = new Image("file:src/main/resources/images/boy_down_1.png");
			} else {
				entrenador = new Image("file:src/main/resources/images/boy_down_2.png");
			}
			posy = posy +velocidad;
			direccion = "";
			gcc.clearRect(posx - velocidad, posy - velocidad, posy, posx);
		}
		if (direccion.equals("DERECHA")) {
			movimiento++;
			if (movimiento % 2 == 0) {
				entrenador = new Image("file:src/main/resources/images/boy_right_1.png");
			} else {
				entrenador = new Image("file:src/main/resources/images/boy_right_2.png");
			}
			posx = posx + velocidad;
			direccion = "";
			gcc.clearRect(posx - velocidad, posy - velocidad, posy, posx);
		}
		if (direccion.equals("IZQUIERDA")) {
			movimiento++;
			if (movimiento % 2 == 0) {
				entrenador = new Image("file:src/main/resources/images/boy_left_1.png");
			} else {
				entrenador = new Image("file:src/main/resources/images/boy_left_2.png");
			}
			posx = posx - velocidad;
			direccion = "";
			gcc.clearRect(posx, posy, posy, posx);
		}
		gcc.drawImage(entrenador, posx, posy);

	}

	@FXML
	void onkeyPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.W) {
			direccion = "ARRIBA";
		}
		if (event.getCode() == KeyCode.S) {
			direccion = "ABAJO";
		}
		if (event.getCode() == KeyCode.A) {
			direccion = "IZQUIERDA";
		}
		if (event.getCode() == KeyCode.D) {
			direccion = "DERECHA";
		}
	}

	public BorderPane getView() {
		return view;
	}

}
