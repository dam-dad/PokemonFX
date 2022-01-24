package dad.pokemonfx.batalla;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.fxml.Initializable;

public class MenuController implements Initializable {

	private BooleanProperty fin = new SimpleBooleanProperty();

	JuegoController juego = new JuegoController();

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
		fin.set(false);

	}

	@FXML
	void onjugarbutton(ActionEvent event) {
		fin.set(true);
	}

	@FXML
	void onsalirbutton(ActionEvent event) {
		System.exit(0);
	}

	public BooleanProperty finProperty() {
		return this.fin;
	}

	public boolean isFin() {
		return this.finProperty().get();
	}

	public void setFin(final boolean fin) {
		this.finProperty().set(fin);
	}

	public BorderPane getView() {
		return view;
	}

}
