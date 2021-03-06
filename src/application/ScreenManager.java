package application;

import java.io.IOException;
import java.nio.channels.SeekableByteChannel;
import java.util.ArrayList;

import application.controllers.LoginScreenController;
import application.controllers.PopUpWindowController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Classe responsavel por gerir as telas e scenes da aplicacao junto com seus controladores.
 * 
 * @author Franco Zalamena e Pedro Oliveira
 *
 */
public class ScreenManager {

	private static VBox vBox;
	
	public static Stage mainWindow;
	
	public static ArrayList<Stage> openStages = new ArrayList<>();

	public ScreenManager() {
	}
	/**
	 * Metodo que inicia o stage inicial (Login Screen).
	 * 
	 * @param primaryStage Janela da aplicacao
	 * 
	 * @see #setScreen(ScreenContainer)
	 * 
	 * @throws IOException a
	 */
	public void start(Stage primaryStage) throws IOException {
		mainWindow = primaryStage;
		openStages.add(mainWindow);
		mainWindow.setTitle("PubFinder");
		mainWindow.getIcons().add(new Image(getClass().getResourceAsStream("views/res/imgs/logoPubFinder.png")));

		vBox = new VBox();
		setScreen(new ScreenContainer(null, "views/LoginScreen.fxml", null, new LoginScreenController()));
		vBox.getStylesheets().add(getClass().getResource("views/layout.css").toExternalForm());
		Scene scene = new Scene(vBox, 335, 600);

		mainWindow.setScene(scene);
		mainWindow.show();

	}
	/**
	 * Define qual o conteudo do scene principal
	 * 
	 * @param screenContainer Ecra que contem o header (se existir) e o content.
	 * 
	 * @see application.ScreenContainer
	 * @see #setContent(String, Object)
	 * @see #setHeader(String, Object)
	 */
	public static void setScreen(ScreenContainer screenContainer) {
		vBox.getChildren().clear();
		if (screenContainer.getHeaderFXML() != null) {
			setHeader(screenContainer.getHeaderFXML(), screenContainer.getHeaderController());
		}
		setContent(screenContainer.getContentFXML(), screenContainer.getContentController());
	}
	/**
	 * Define o Content do ecra e o seu controlador.
	 * @param contentFxmlLocation Localizacao do fxml
	 * @param contentController Localizacao do fxml do  controlador
	 */
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
	/**
	 * Define o header do ecra e o seu controlador.
	 * @param headerFxmlLocation Localizacao do fxml
	 * @param headerController Localizacao do fxml do  controlador
	 */
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
	/**
	 * Cria uma janela de pop up modal linkada com a janela principal. O conteudo e definido pelo controlador do Pop up Window
	 * @param controller controlador do Pop up Window que possui o texto e acoes necessarias da janela
	 */
	public static void createPopupWindow(PopUpWindowController controller) {
		Stage popupWindow = new Stage();
		controller.setStage(popupWindow);
		openStages.add(popupWindow);
		
		FXMLLoader loader = new FXMLLoader(Main.class.getResource("views/PopUpWindow.fxml"));
		loader.setController(controller);
		Parent root= null;
		try {
			root = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}	
		
		Scene scene = new Scene(root);
		
		popupWindow.setScene(scene);
		popupWindow.initOwner(mainWindow);
		popupWindow.initModality(Modality.APPLICATION_MODAL);
		popupWindow.showAndWait();
	}

}
