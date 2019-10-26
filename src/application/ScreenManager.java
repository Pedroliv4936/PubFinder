package application;

import java.io.IOException;

import application.controllers.LoginScreenController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ScreenManager {

	private static BorderPane borderPane;

	public static final String MAIN_SCREEN_CONTENT = "views/MainScreenContent.fxml";

	public static final String DRINK_SCREEN_CONTENT = "views/BebidasScreen.fxml";

	public static final String DEFAULT_HEADER = "views/DefaultHeader.fxml";

	public ScreenManager() {
	}

	public void start(Stage primaryStage) throws IOException {
		primaryStage.setTitle("PubFinder");
		primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("views/res/imgs/logoPubFinder.png")));

		borderPane = new BorderPane();
		setScreen(null, "views/LoginScreen.fxml", null, new LoginScreenController());
		borderPane.getStylesheets().add(getClass().getResource("views/layout.css").toExternalForm());
		Scene scene = new Scene(borderPane, 335, 600);

		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public static void setContent(String contentFxmlLocation, Object contentController) {
		FXMLLoader loader = new FXMLLoader(ScreenManager.class.getResource(contentFxmlLocation));
		try {
			loader.setController(contentController);
			Pane root = loader.load();
			borderPane.setCenter(root);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void setHeader(String headerFxmlLocation, Object headerController) {
		FXMLLoader loader = new FXMLLoader(Main.class.getResource(headerFxmlLocation));
		loader.setController(headerController);
		Pane root = null;
		try {
			root = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		borderPane.setTop(root);
	}

	public static void setScreen(String headerFXML, String contentFXML, Object headerController, Object contentController) {
		if (headerFXML != null) {
			setHeader(headerFXML, headerController);
		} else {
			borderPane.getChildren().clear();
		}
		setContent(contentFXML, contentController);
	}

}
