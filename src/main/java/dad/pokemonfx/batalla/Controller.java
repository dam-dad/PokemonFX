package dad.pokemonfx.batalla;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

public class Controller implements Initializable {
	private MenuController menucontroller;
	private JuegoController juegoController;
	
	@FXML
	private BorderPane view;

	public Controller() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Controller.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			menucontroller=new MenuController();
			view.setCenter(menucontroller.getView());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		menucontroller.finProperty().addListener((o, ov, nv) -> sepulsoboton(o, ov, nv));
		

	}

	private void sepulsoboton(ObservableValue<? extends Boolean> o, Boolean ov, Boolean nv) {
		try {
			juegoController=new JuegoController();
			view.setCenter(juegoController.getView());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public BorderPane getView() {
		return view;
	}


}
