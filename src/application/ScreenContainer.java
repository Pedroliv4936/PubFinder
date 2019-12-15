package application;

import application.controllers.AddInfoController;
import application.controllers.BarScreenController;
import application.controllers.CheckNewRequestsController;
import application.controllers.DefaultHeaderController;
import application.controllers.LoginScreenController;
import application.controllers.MainScreenContentController;
import application.controllers.MenuBebidasController;
import application.controllers.RegisterScreenController;
import application.controllers.UserHeaderController;
import application.controllers.UserScreenController;
import application.models.DAO.PubDAO;

/**
 * Classe que contem informacoes do header e do conteudo do stage.
 * @author Franco Zalamena & Pedro Oliveira
 *
 */
public class ScreenContainer {

	private String headerFXML, contentFXML;
	private Object headerController, contentController;
	
	public static final ScreenContainer MAIN_SCREEN = new ScreenContainer("views/DefaultHeader.fxml", "views/MainScreenContent.fxml", new DefaultHeaderController(), new MainScreenContentController());
	public static final ScreenContainer USER_SCREEN = new ScreenContainer("views/UserHeader.fxml", "views/UserScreen.fxml", new UserHeaderController(), new UserScreenController());
	public static final ScreenContainer ADD_INFO = new ScreenContainer("views/DefaultHeader.fxml", "views/AddInfo.fxml", new DefaultHeaderController(), new AddInfoController());
	public static final ScreenContainer CHECK_NEW_REQUESTS = new ScreenContainer("views/DefaultHeader.fxml", "views/CheckNewRequests.fxml", new DefaultHeaderController(), new CheckNewRequestsController());
	public static final ScreenContainer REGISTER = new ScreenContainer(null, "views/RegisterScreen.fxml", null, new RegisterScreenController());
	public static final ScreenContainer LOGIN = new ScreenContainer(null, "views/LoginScreen.fxml", null, new LoginScreenController());
	public static final ScreenContainer MENU_BEBIDAS = new ScreenContainer("views/DefaultHeader.fxml", "views/BebidasScreen.fxml", new DefaultHeaderController(), new MenuBebidasController());

	public ScreenContainer(String headerFXML, String contentFXML, Object headerController, Object contentController) {
		this.headerFXML = headerFXML;
		this.contentFXML = contentFXML;
		this.headerController = headerController;
		this.contentController = contentController;
	}

	public String getHeaderFXML() {
		return headerFXML;
	}

	public void setHeaderFXML(String headerFXML) {
		this.headerFXML = headerFXML;
	}

	public String getContentFXML() {
		return contentFXML;
	}

	public void setContentFXML(String contentFXML) {
		this.contentFXML = contentFXML;
	}

	public Object getHeaderController() {
		return headerController;
	}

	public void setHeaderController(Object headerController) {
		this.headerController = headerController;
	}

	public Object getContentController() {
		return contentController;
	}

	public void setContentController(Object contentController) {
		this.contentController = contentController;
	}
}
