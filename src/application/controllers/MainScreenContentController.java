package application.controllers;

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
		ScreenManager.setScreen(ScreenManager.DEFAULT_HEADER, "views/CheckNewRequests.fxml", new DefaultHeaderController(), new CheckNewRequestsController());
	}
	
	@FXML
	private void addInformation() {
	ScreenManager.setScreen(ScreenManager.DEFAULT_HEADER, "views/AddInformationScreen.fxml", new DefaultHeaderController(), new AddInfoController());
	}
	
}
