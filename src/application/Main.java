package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {
	
	private static Stage currentStage;
	
	private static BorderPane borderPane;
	
	private ScreenManager screenManager;
	
	
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		MapManager.createMap();
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