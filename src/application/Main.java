package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

	Stage window;
	
	JDBC jdbc;
	@Override
	public void start(Stage primaryStage) throws IOException {
		window = primaryStage;
		window.setTitle("PubFinder");
		
		
		Pane root = FXMLLoader.load(getClass().getResource("login/LoginScreen.fxml"));
		
		Scene scene = new Scene(root, 575, 55);
		
		window.setScene(scene);
		window.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
