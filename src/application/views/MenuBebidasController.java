package application.views;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.lynden.gmapsfx.GoogleMapView;
import application.Main;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

public class MenuBebidasController {

	@FXML
	JFXButton bebidasButton,logoButton,userButton;
	
	@FXML
	private void initialize() {
	}

	@FXML
	private void openDrinksPage() {
		Main.changeScene("views/BebidasScreen.fxml");
	}
	
	@FXML
	private void openMainPage() {
		Main.changeScene("views/MainScreen.fxml");
	}
	
	@FXML
	private void openUserPage() {
		Main.changeScene("views/UserScreen.fxml");
	}
	
}
