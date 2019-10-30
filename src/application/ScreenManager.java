package application;

import java.io.IOException;

import application.controllers.LoginScreenController;
import application.views.ScreenContainer;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ScreenManager {

	private static VBox vBox;

	public ScreenManager() {
	}

	public void start(Stage primaryStage) throws IOException {
		primaryStage.setTitle("PubFinder");
		primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("views/res/imgs/logoPubFinder.png")));

		vBox = new VBox();
		setScreen(new ScreenContainer(null, "views/LoginScreen.fxml", null, new LoginScreenController()));
		vBox.getStylesheets().add(getClass().getResource("views/layout.css").toExternalForm());
		Scene scene = new Scene(vBox, 335, 600);

		primaryStage.setScene(scene);
		primaryStage.show();

	}

	private static void setContent(String contentFxmlLocation, Object contentController) {
		FXMLLoader loader = new FXMLLoader(ScreenManager.class.getResource(contentFxmlLocation));
		try {
			loader.setController(contentController);
			Parent root = loader.load();
			vBox.getChildren().add(root);
			VBox.setVgrow(root, Priority.ALWAYS);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void setHeader(String headerFxmlLocation, Object headerController) {
		FXMLLoader loader = new FXMLLoader(Main.class.getResource(headerFxmlLocation));
		loader.setController(headerController);
		Pane root = null;
		try {
			root = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		vBox.getChildren().add(root);
	}

	public static void setScreen(ScreenContainer screenContainer) {
		vBox.getChildren().clear();
		if (screenContainer.getHeaderFXML() != null) {
			setHeader(screenContainer.getHeaderFXML(), screenContainer.getHeaderController());
		}
		setContent(screenContainer.getContentFXML(), screenContainer.getContentController());
	}

}
