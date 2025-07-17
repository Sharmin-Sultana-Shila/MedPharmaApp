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
public class EmployeeMenuController {
	@FXML
	public RadioButton addItemRB;
	@FXML
	public RadioButton viewAccRB;
	@FXML
	public RadioButton searchRB;
	@FXML
	public RadioButton expMedRB;
	@FXML
	public Button nextB;
	@FXML
	public Button logoutB;
	public Stage stage;
	public Parent root;
	public Scene scene;
	public void addItems(ActionEvent event) {	
		addItemRB.setDisable(true);
		viewAccRB.setDisable(false);
		viewAccRB.setSelected(false);
		searchRB.setDisable(false);
		searchRB.setSelected(false);
		expMedRB.setDisable(false);
		expMedRB.setSelected(false);
	}
	public void view_AccOrder(ActionEvent event) {
		addItemRB.setDisable(false);
		addItemRB.setSelected(false);
		viewAccRB.setDisable(true);
		searchRB.setDisable(false);
		searchRB.setSelected(false);
		expMedRB.setDisable(false);
		expMedRB.setSelected(false);
	}
	public void searchItem(ActionEvent event) {
		addItemRB.setDisable(false);
		addItemRB.setSelected(false);
		viewAccRB.setDisable(false);
		viewAccRB.setSelected(false);
		searchRB.setDisable(true);
		expMedRB.setDisable(false);
		expMedRB.setSelected(false);
	}
	public void expMed(ActionEvent event) {
		addItemRB.setDisable(false);
		addItemRB.setSelected(false);
		viewAccRB.setDisable(false);
		viewAccRB.setSelected(false);
		searchRB.setDisable(false);
		searchRB.setSelected(false);
		expMedRB.setDisable(true);
	}
	public void next(ActionEvent event) throws IOException {
		if(addItemRB.isSelected()) {
			root = FXMLLoader.load(getClass().getResource("AddMedAcc.fxml"));
			stage =(Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setTitle("Add Medicines & Medical Accessory");
			stage.setScene(scene);
			stage.show();
			
		}else if(viewAccRB.isSelected()) {
			root = FXMLLoader.load(getClass().getResource("ViewAcc.fxml"));
			stage =(Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setTitle("View & Accept new orders");
			stage.setScene(scene);
			stage.show();
		}else if(searchRB.isSelected()) {
			root = FXMLLoader.load(getClass().getResource("SearchWindow.fxml"));
			stage =(Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setTitle("Search Medicine & Medical Accessory");
			stage.setScene(scene);
			stage.show();
		}else if(expMedRB.isSelected()){
			root = FXMLLoader.load(getClass().getResource("FindExpMed.fxml"));
			stage =(Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setTitle("Expired Medicines");
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
