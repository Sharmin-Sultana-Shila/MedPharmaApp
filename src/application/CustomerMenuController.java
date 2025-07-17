package application;

import javafx.scene.Node;

import java.io.IOException;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;
public class CustomerMenuController {
	@FXML
	RadioButton searchAndReqRB;
	@FXML
	RadioButton viewOrderAndPayBillRB;
	@FXML
	Button logoutB;
	@FXML
	Button nextB;
	public Stage stage;
	public Parent root;
	public Scene scene;
	public void searchAndReq(ActionEvent event) {
		searchAndReqRB.setDisable(true);
		viewOrderAndPayBillRB.setDisable(false);
		viewOrderAndPayBillRB.setSelected(false);
	}
	
	public void viewOrderAndPayBill(ActionEvent event) {
		searchAndReqRB.setDisable(false);
		searchAndReqRB.setSelected(false);
		viewOrderAndPayBillRB.setDisable(true);
	}
	
	public void next(ActionEvent event) throws IOException {
		if(searchAndReqRB.isSelected()) {
			root = FXMLLoader.load(getClass().getResource("ReqAndSearch.fxml"));
			stage =(Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setTitle("Request and Search items ");
			stage.setScene(scene);
			stage.show();
		}else {
			root = FXMLLoader.load(getClass().getResource("ViewOrderAndPay.fxml"));
			stage =(Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setTitle("View Order and Pay Bill");
			stage.setScene(scene);
			stage.show();
		}
	}
	
	public void logout(ActionEvent event) {
		try {
			root = FXMLLoader.load(getClass().getResource("Login.fxml"));
			stage =(Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setTitle("Login Window");
			stage.setScene(scene);
			stage.show();
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
}
