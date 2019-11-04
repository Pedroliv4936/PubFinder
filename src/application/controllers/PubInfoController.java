package application.controllers;

import application.models.Pub;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class PubInfoController {

	@FXML
	Label pubName, pubPrice, pubType;
	
	private String name, price, type;
	
	public PubInfoController(Pub pub) {
		name = pub.toString();
		price = String.format("%.2f",pub.getPrice());
		type = pub.getType();
	}
	
	@FXML
	private void initialize() {
		System.out.println(name + " selecionado");
		pubName.setText(name);
		pubPrice.setText(price);
		pubType.setText(type);
	}
}
