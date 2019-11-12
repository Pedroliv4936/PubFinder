package application.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class PopUpWindowController {
	@FXML
	private Label popupMessage;
	
	private String message;
	
	private boolean aproved;
	
	private Stage parentStage;
	
	public PopUpWindowController(String message) {
		this.message = message;
	}
	
	@FXML
	public void initialize() {
		popupMessage.setText(message);
	}
	
	@FXML
	public void accept() {
		aproved = true;
		parentStage.hide();
	}
	
	@FXML
	public void cancel() {
		aproved = false;
		parentStage.hide();
	}
	
	public boolean isAproved() {
		return aproved;
	}
	
	public Stage getStage() {
		return parentStage;
	}
	
	public void setStage(Stage stage) {
		this.parentStage = stage;
	}
}
