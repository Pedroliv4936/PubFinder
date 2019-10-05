package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;

public class Main extends Application{

	Button button, button2;
	Stage window;
	Scene scene1,scene2;
	@Override
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
		button = new Button();
		button2 = new Button();
		
		button.setText("ClickMe");
		button.setOnAction(e -> window.setScene(scene2));
		
		StackPane layout = new StackPane();
		layout.getChildren().add(button);
		scene1 = new Scene(layout,300,200);

		

		button2.setText("ClickMe to Go back");
		button2.setOnAction(e -> window.setScene(scene1));
		StackPane layout2 = new StackPane();
		layout2.getChildren().add(button2);
		scene2= new Scene(layout2, 500,500);
		
		window.setScene(scene1);
		window.show();
	}
	
	public static void main(String [] args) {
		launch(args);
	}
}
