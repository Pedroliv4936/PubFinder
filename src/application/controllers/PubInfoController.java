package application.controllers;

import application.models.Pub;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class PubInfoController {

	@FXML
	Label pubName, pubPrice, pubType, open, close;
	
	private String name, price, type,openTime,closeTime;
	
	private Pub pub;
	
	public PubInfoController(Pub pub) {
		this.pub = pub;
		name = pub.toString();
		price = String.format("%.2f",pub.getPrice());
		type = pub.getType();
		openTime = pub.getOpenTime();
		closeTime = pub.getCloseTime();
	}
	
	@FXML
	private void initialize() {
		System.out.println(name + " selecionado");
		pubName.setText(name);
		pubPrice.setText(price);
		pubType.setText(type);
		open.setText(openTime);
		close.setText(closeTime);
	}

	public Pub getPub() {
		return pub;
	}
}
