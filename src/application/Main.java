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
	
	private ScreenManager screenManager;
	
	
	
	@Override
	public void start(Stage primaryStage) throws IOException {

		screenManager= new ScreenManager();
		screenManager.start(primaryStage);

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