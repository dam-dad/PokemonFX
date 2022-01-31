package dad.pokemonfx.batalla;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

import dad.pokemonfx.battlemode.MenuBattleModeController;
import javafx.animation.FadeTransition;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.fxml.Initializable;

public class MenuController implements Initializable {

	Media media;
	MediaPlayer mediaPlayer;
	Media media2;

	private BooleanProperty botonPulsado = new SimpleBooleanProperty();
	MenuBattleModeController battlemodeController;
	Stage battlemodeStage;

	@FXML
	private Button batallabutton;

	@FXML
	private Button jugarbutton;

	@FXML
	private Button salirbutton;

	@FXML
	private BorderPane view;

	public MenuController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MenuView.fxml"));
		loader.setController(this);
		loader.load();

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		botonPulsado.set(false);
		botonCombatePulsado.set(false);
		view.sceneProperty().addListener((o, ov, nv) -> {
			iniciarTransicion();
		});
		// menu song
		try {
			media = new Media((getClass().getResource("/music/Menu_Song.mp3")).toURI().toString());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mediaPlayer = new MediaPlayer(media);
		mediaPlayer.setVolume(0.03);
		mediaPlayer.play();

	}

	private void iniciarTransicion() {
		FadeTransition fade = new FadeTransition();
		fade.setDuration(Duration.seconds(4));
		fade.setFromValue(0.0);
		fade.setToValue(1.0);
		fade.setNode(view);
		fade.play();
	}

	@FXML
	void onjugarbutton(ActionEvent event) {
		botonPulsado.set(true);
	}

	@FXML
    void onbatallabutton(ActionEvent event) throws IOException {
		mediaPlayer.stop();
		battlemodeController = new MenuBattleModeController();
		Scene scene = new Scene(battlemodeController.getView());
		battlemodeStage = new Stage();
		battlemodeStage.setScene(scene);
		battlemodeStage.show();
    }

	@FXML
	void onsalirbutton(ActionEvent event) {
		System.exit(0);
	}

	public BooleanProperty BotonPulsadoProperty() {
		return this.botonPulsado;
	}

	public boolean isBotonPulsado() {
		return this.BotonPulsadoProperty().get();
	}

	public void setBotonPulsado(final boolean BotonValue) {
		this.BotonPulsadoProperty().set(BotonValue);
	}

	public BorderPane getView() {
		return view;
	}

	public final BooleanProperty botonCombatePulsadoProperty() {
		return this.botonCombatePulsado;
	}

	public final boolean isBotonCombatePulsado() {
		return this.botonCombatePulsadoProperty().get();
	}

	public final void setBotonCombatePulsado(final boolean botonCombatePulsado) {
		this.botonCombatePulsadoProperty().set(botonCombatePulsado);
	}

}
