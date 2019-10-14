package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

	Stage loginWindow, mainWindow;
	

	@Override
	public void start(Stage primaryStage) throws IOException {
		loginWindow = new Stage();
		
		mainWindow = primaryStage;
		mainWindow.setTitle("PubFinder");
		loginWindow.setTitle("Login");
		
		Pane mainPane = FXMLLoader.load(getClass().getResource("login/mainStage.fxml"));
		Pane loginPane = FXMLLoader.load(getClass().getResource("login/LoginScreen.fxml"));
		
		Scene loginScene = new Scene(loginPane, 575, 55);
		Scene mainScene = new Scene(mainPane);
		
		loginWindow.setScene(loginScene);
		mainWindow.setScene(mainScene);
		mainWindow.show();
		loginWindow.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
