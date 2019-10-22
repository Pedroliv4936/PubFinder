package application;

import java.io.IOException;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {
	
	private static Stage currentStage;
	private static Scene currentScene;

	@Override
	public void start(Stage primaryStage) throws IOException {
		primaryStage.setTitle("Login");
		primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("keyIcon.png")));
		Pane root = FXMLLoader.load(getClass().getResource("views/LoginScreen.fxml"));

		Scene scene = new Scene(root);
		
		currentScene = scene;
		
		currentStage = primaryStage;

		primaryStage.setScene(scene);
		primaryStage.show();

	}
	
	public static void changeScene(String fxmlLocation){
		Pane root=null;
		try {
			root = FXMLLoader.load(Main.class.getResource(fxmlLocation));
		} catch (IOException e) {
			e.printStackTrace();
		}
		currentScene.setRoot(root);
		currentStage.setScene(currentScene);
	}

	public static void createNewWindow(String fxmlLocation, String icon) {
		Stage window = new Stage();
		window.getIcons().add(new Image(Main.class.getResourceAsStream(icon)));
		window.setTitle("Pub Finder");
		Pane root=null;
		try {
			root = FXMLLoader.load(Main.class.getResource(fxmlLocation));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Scene scene = new Scene(root);
		currentStage= window;
		currentScene = scene;
		window.setScene(scene);
		window.show();
	}
	
	public static Stage getCurrentStage() {
		return currentStage;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}