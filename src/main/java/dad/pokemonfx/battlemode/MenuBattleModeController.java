package dad.pokemonfx.battlemode;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;

import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class MenuBattleModeController implements Initializable {

	Media media;
	MediaPlayer mediaPlayer;

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
		// battlemode song
		/*
		try {
			media = new Media((getClass().getResource("/music/Main_Menu_Battlemode.mp3")).toURI().toString());
		} catch (URISyntaxException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		mediaPlayer = new MediaPlayer(media);
		mediaPlayer.setVolume(0.03);
		mediaPlayer.play();
	*/
	}

	@FXML
	void oncancelarbutton(ActionEvent event) {
		mediaPlayer.stop();
		((Node) (event.getSource())).getScene().getWindow().hide();
	}

	@FXML
	void onsiguientebutton(ActionEvent event) {

	}

	public BorderPane getView() {
		return view;
	}
}
