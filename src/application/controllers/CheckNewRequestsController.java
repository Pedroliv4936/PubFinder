package application.controllers;

import application.models.Pub;
import application.models.DAO.PubDAO;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

public class CheckNewRequestsController {

	@FXML
	private ListView<Pub> pendingPubRequestsLV;
	
	@FXML
	private void initialize() {
		pendingPubRequestsLV.setCellFactory(param -> new ListCell<Pub>() {
			@Override
            protected void updateItem(Pub item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null || item.getName() == null) {
                    setText(null);
                } else {
                    setText(item.getName());
                }
            }
        });
		pendingPubRequestsLV.setItems(PubDAO.getPendingPubs());
	}
	
	@FXML
	private void accept() {
		PubDAO.getPubList().addAll(pendingPubRequestsLV.getSelectionModel().getSelectedItems());
		PubDAO.getPendingPubs().removeAll(pendingPubRequestsLV.getSelectionModel().getSelectedItems());
		pendingPubRequestsLV.setItems(PubDAO.getPendingPubs());
	}
}
