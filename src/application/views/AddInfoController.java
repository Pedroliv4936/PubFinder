package application.views;



import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import application.models.Pub;
import javafx.fxml.FXML;


public class AddInfoController {
	@FXML
	JFXTextField tipo,preço;
	JFXComboBox<Pub> barField;
	JFXComboBox<String> tipoField;
	JFXTextArea observacoes;
	JFXButton enviar;
	
	String nomeBar,tipoBebida,preçoEntrada,observações;
	@FXML
	private void initialize() {
	
	}
}
