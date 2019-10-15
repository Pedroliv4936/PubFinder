package application;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws IOException {
<<<<<<< HEAD
		loginWindow = new Stage();
		
		mainWindow = primaryStage;
		mainWindow.setTitle("PubFinder");
		loginWindow.setTitle("Login");
		
		Pane mainPane = FXMLLoader.load(getClass().getResource("login/MainScreen.fxml"));
		Pane loginPane = FXMLLoader.load(getClass().getResource("login/LoginScreen.fxml"));
		
		Scene loginScene = new Scene(loginPane, 575, 55);
		Scene mainScene = new Scene(mainPane);
		
		loginWindow.setScene(loginScene);
		mainWindow.setScene(mainScene);
		mainWindow.show();
		loginWindow.show();
=======
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
>>>>>>> branch 'master' of https://github.com/Pedroliv4936/PubFinder.git
	}

	public static void main(String[] args) {
		launch(args);
	}
}
