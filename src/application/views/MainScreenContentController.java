package application.views;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import application.ScreenManager;
import application.models.DAO.loginDAO;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class MainScreenContentController {

	@FXML
	JFXTextField searchField;
	@FXML
	VBox vbox;
	
	@FXML
	JFXButton checkNewRequests;
	
	@FXML
	private void initialize() {
		if(!loginDAO.getAdminList().contains(loginDAO.getLogedinUser())) {
			vbox.getChildren().remove(checkNewRequests);
		}
	}
	
	@FXML
	private void checkNewRequests() {
		
	}
	
	@FXML
	private void addInformation() {
	ScreenManager.setScreen("views/AddInformationScreen.fxml");
	}
	
}
