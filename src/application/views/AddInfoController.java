package application.views;



import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import application.models.Pub;
import javafx.fxml.FXML;


public class AddInfoController {
	@FXML
	JFXTextField tipo,precoField;
	JFXComboBox<Pub> barField;
	JFXComboBox<String> tipoField;
	JFXTextArea observacoes;
	JFXButton enviar;
	String preco;
	@FXML
	private void initialize() {
	
	}
	private void enviar() {
		preco=precoField.getText();
		System.out.print(preco);
	}
}
