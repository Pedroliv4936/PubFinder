package application.controllers;

import application.models.Pub;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
/**
 * Apresenta a informacao do Pub selecionado
 * 
 * @author Franco Zalamena & Pedro Oliveira
 *
 */
public class PubInfoController {

	@FXML
	Label pubName, pubPrice, pubType, open, close;
	
	private String name, price, type,openTime,closeTime;
	
	private Pub pub;
	/**
	 * Construtor que recebe o bar que se deseja apresentar e passa a informacao deste para variaveis desta classe.
	 * 
	 * @param pub Pub do qual se vai buscar a informacao que se deseja apresentar.
	 */
	public PubInfoController(Pub pub) {
		this.pub = pub;
		name = pub.toString();
		price = String.format("%.2f",pub.getPrice());
		type = pub.getType().toString();
		openTime = pub.getOpenTime();
		closeTime = pub.getCloseTime();
	}
	/**
	 * Passa a informacao das variaveis para as Labels.
	 */
	@FXML
	private void initialize() {
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
