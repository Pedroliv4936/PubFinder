package application.views;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import application.models.Pub;
import javafx.fxml.FXML;

public class AddInfoController {
	@FXML
	JFXTextField preco;
	@FXML
	JFXComboBox<String> barField, tipoField;
	JFXTextArea observacoes;
	JFXButton enviar;
	double precoBebida;
	@FXML
	private void initialize() {
		tipoField.getItems().addAll("Discoteca", "Bar","Salao de Jogos");
		for (Pub pub : application.models.DAO.PubDAO.getPubList()) {
			barField.getItems().add(pub.getName());
		}
		
	}
	@FXML
	private void editableButton() {
		barField.setEditable(!barField.isEditable());
	}
	@FXML
	private void adicionarInfo() {
		precoBebida=Double.parseDouble(preco.getText());
		System.out.println(barField.getValue());
		System.out.println(tipoField.getValue());
		System.out.println(precoBebida);
		System.out.println(observacoes.getText().replaceAll("\n"," "));		
		
	}
}
