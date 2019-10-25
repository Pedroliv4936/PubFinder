package application.views;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import application.model.DAO.PubDAO;
import application.models.Pub;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;

public class AddInfoController {
	@FXML
	JFXTextField preco;
	@FXML
	JFXComboBox<String> barField;
	JFXComboBox<String> tipoField;
	JFXTextArea observacoes;
	JFXButton enviar;

	@FXML
	private void initialize() {
		for (Pub pub : PubDAO.getPubList()) {
			barField.getItems().add(pub.getName());
		}
		
	}

	@FXML
	private void adicionarInfo() {
		System.out.print("ssds");
		barField.setEditable(!barField.isEditable());
	}
}
