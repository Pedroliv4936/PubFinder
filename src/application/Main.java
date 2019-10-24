package application;

import java.io.IOException;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {
	
	private static Stage currentStage;
	private static Scene currentScene;
	
	private static BorderPane borderPane;
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		primaryStage.setTitle("Login");
		primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("keyIcon.png")));
		
		borderPane = new BorderPane(); 
		borderPane.setTop(FXMLLoader.load(Main.class.getResource("views/Header.fxml")));
		Scene scene = new Scene(borderPane,335, 600);
		
		currentScene = scene;
		currentStage = primaryStage;

		primaryStage.setScene(scene);
		primaryStage.show();

	}
	
	public static BorderPane getBorderPane() {
		return borderPane;
	}

	
	public static Stage getCurrentStage() {
		return currentStage;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}