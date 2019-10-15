package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws IOException {
		primaryStage.setTitle("Login");

		Pane root = FXMLLoader.load(getClass().getResource("login/LoginScreen.fxml"));

		Scene scene = new Scene(root);

		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void createNewWindow(String fxmlLocation) throws IOException {
		Stage window = new Stage();
		Pane root = FXMLLoader.load(Main.class.getResource(fxmlLocation));
		Scene scene = new Scene(root);

		window.setScene(scene);
		window.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}