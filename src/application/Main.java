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

	@Override
	public void start(Stage primaryStage) throws IOException {
		primaryStage.setTitle("Login");
		primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("keyIcon.png")));
		Pane root = FXMLLoader.load(getClass().getResource("views/MainScreen.fxml"));

		Scene scene = new Scene(root);
		
		currentStage = primaryStage;

		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public static void createNewWindow(String fxmlLocation, String icon) {
		Stage window = new Stage();
		window.getIcons().add(new Image(Main.class.getResourceAsStream(icon)));
		window.setTitle("Pub Finder");
		Pane root=null;
		try {
			root = FXMLLoader.load(Main.class.getResource("views/MainScreen.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Scene scene = new Scene(root);
		currentStage= window;
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