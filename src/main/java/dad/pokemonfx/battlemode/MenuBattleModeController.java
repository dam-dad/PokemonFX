package dad.pokemonfx.battlemode;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.FadeTransition;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;

public class MenuBattleModeController implements Initializable {

	/**
	 * Constructor del menu de batalla rapida
	 */
	BooleanProperty botonVolver = new SimpleBooleanProperty();
	BooleanProperty botonIrCombate = new SimpleBooleanProperty();

	@FXML
	private Button cancelarbutton;

	@FXML
	private Button siguientebutton;

	@FXML
	private BorderPane view;

	public MenuBattleModeController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MenuBattleMode.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		botonVolver.set(false);
		botonIrCombate.set(false);
		view.sceneProperty().addListener((o, ov, nv) -> {
			startTransition();
		});
	}
	
	/**
	 * Transicion
	 */
	private void startTransition() {
		FadeTransition fade = new FadeTransition();
		fade.setDuration(Duration.seconds(4));
		fade.setFromValue(0.0);
		fade.setToValue(1.0);
		fade.setNode(view);
		fade.play();
	}

	@FXML
	void oncancelarbutton(ActionEvent event) {
		botonVolver.set(true);
	}

	@FXML
	void onsiguientebutton(ActionEvent event) {
        botonIrCombate.set(true);
	}

	public BorderPane getView() {
		return view;
	}

	public final BooleanProperty botonVolverProperty() {
		return this.botonVolver;
	}

	public final boolean isBotonVolver() {
		return this.botonVolverProperty().get();
	}

	public final void setBotonVolver(final boolean botonVolver) {
		this.botonVolverProperty().set(botonVolver);
	}

	public final BooleanProperty botonIrCombateProperty() {
		return this.botonIrCombate;
	}
	

	public final boolean isBotonIrCombate() {
		return this.botonIrCombateProperty().get();
	}
	

	public final void setBotonIrCombate(final boolean botonIrCombate) {
		this.botonIrCombateProperty().set(botonIrCombate);
	}
	
	

}
