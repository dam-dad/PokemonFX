package dad.pokemonfx.batalla;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

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
import javafx.util.Duration;
import javafx.fxml.Initializable;

public class MenuController implements Initializable {

	Media media1;
	Media media2;
	MediaPlayer mediaPlayer;

	private BooleanProperty buttonPressed = new SimpleBooleanProperty();
	private BooleanProperty battleButtonPressed = new SimpleBooleanProperty();

	@FXML
	private Button battleButton;

	@FXML
	private Button playButton;

	@FXML
	private Button leaveButton;
	
	@FXML
    private Button enableMusicButton;
	
	@FXML
    private Button muteMusicButton;

	@FXML
	private BorderPane view;

	public MenuController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MenuView.fxml"));
		loader.setController(this);
		loader.load();

	}

	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		buttonPressed.set(false);
		battleButtonPressed.set(false);
		view.sceneProperty().addListener((o, ov, nv) -> {
			startTransition();
		});
		// menu song
		try {
			media1 = new Media((getClass().getResource("/music/Menu_Song.mp3")).toURI().toString());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mediaPlayer = new MediaPlayer(media1);
		mediaPlayer.setVolume(0.03);
		mediaPlayer.play();

	}

	private void startTransition() {
		FadeTransition fade = new FadeTransition();
		fade.setDuration(Duration.seconds(4));
		fade.setFromValue(0.0);
		fade.setToValue(1.0);
		fade.setNode(view);
		fade.play();
	}

	@FXML
	void onPlayButton(ActionEvent event) {
		buttonPressed.set(true);
	}

	@FXML
    void onBattleButton(ActionEvent event) throws IOException {
		battleButtonPressed.set(true);
    }

	@FXML
	void onLeaveButton(ActionEvent event) {
		System.exit(0);
	}
	
	@FXML
    void onEnableMusicButton(ActionEvent event) {
		mediaPlayer.play();
    }
	
	@FXML
    void onMuteMusicButton(ActionEvent event) {
		mediaPlayer.pause();
    }

	public BooleanProperty buttonPressedProperty() {
		return this.buttonPressed;
	}

	public boolean isButtonPressed() {
		return this.buttonPressedProperty().get();
	}

	public void setButtonPressed(final boolean buttonValue) {
		this.buttonPressedProperty().set(buttonValue);
	}

	public BorderPane getView() {
		return view;
	}

	public final BooleanProperty battleButtonPressedProperty() {
		return this.battleButtonPressed;
	}

	public final boolean isBattleButtonPressed() {
		return this.battleButtonPressedProperty().get();
	}

	public final void setBattleButtonPressed(final boolean battleButtonPressed) {
		this.battleButtonPressedProperty().set(battleButtonPressed);
	}

}
