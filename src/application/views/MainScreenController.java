package application.views;

import com.jfoenix.controls.JFXTextField;
import com.lynden.gmapsfx.GoogleMapView;
import application.Main;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

public class MainScreenController {

	@FXML
	JFXTextField searchField;
	@FXML
	ImageView template;
	
	@FXML
	GoogleMapView gMaps;
	
	@FXML
	private void initialize() {
		gMaps= new GoogleMapView("en-US", "CViCBkpkN8Zj6SsHkY0zNUG2qBE=");
	}

	@FXML
	private void onClickButton() {
	}
}
