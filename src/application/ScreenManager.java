package application;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ScreenManager {

	private static BorderPane borderPane;
	
	public static final String MAIN_SCREEN = "views/MainScreenContent.fxml";
	
	public static final String DRINK_SCREEN = "views/BebidasScreen.fxml";

	public ScreenManager() {
	}

	public void start(Stage primaryStage, String cssPath) throws IOException {
		primaryStage.setTitle("Login");
		primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("keyIcon.png")));

		borderPane = new BorderPane();
		setScreen(null, "views/LoginScreen.fxml");
		borderPane.getStylesheets().add(getClass().getResource("views/borderPane.css").toExternalForm());
		Scene scene = new Scene(borderPane, 335, 600);
		
		primaryStage.setScene(scene);
		primaryStage.show();

	}
	
	public void start(Stage primaryStage) throws IOException {
		primaryStage.setTitle("Login");
		primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("keyIcon.png")));

		borderPane = new BorderPane();
		setScreen(null, "views/LoginScreen.fxml");
		borderPane.getStylesheets().add(getClass().getResource("views/layout.css").toExternalForm());
		Scene scene = new Scene(borderPane, 335, 600);
		
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public static void setContent(String fxmlLocation) {
		try {
			Pane contentFXML = FXMLLoader.load(ScreenManager.class.getResource(fxmlLocation));
			borderPane.setCenter(contentFXML);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void setHeader(String fxmlLocation) {
		try {
			Pane headerFXML = FXMLLoader.load(ScreenManager.class.getResource(fxmlLocation));

			borderPane.setTop(headerFXML);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void setScreen(String headerFXML, String contentFXML) {
		if (headerFXML != null) {
		setHeader(headerFXML);
		}else
		{
			borderPane.getChildren().clear();
		}
		setContent(contentFXML);
	}
	
	public static void setScreen(String fxmlLocation) {
		setScreen("views/DefaultHeader.fxml", fxmlLocation);
	}
	

}
