package application;


import java.io.IOException;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.Button;

import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

import javafx.stage.Stage;
import med.lib.DataHandler;

public class AddMedAccController {
	@FXML
	RadioButton medButton;
	@FXML
	RadioButton accButton;
	@FXML
	Button addB;
	@FXML
	Button menuB;
	@FXML
	Button loginB;
	@FXML
	TextField nameTf;
	@FXML
	TextField manTf;
	@FXML
	TextField quanTf;
	@FXML
	TextField modelTf;
	@FXML
	TextField ddTf;
	@FXML
	TextField mmTf;
	@FXML
	TextField yyTf;
	@FXML
	TextField unitTf;
	@FXML
	TextField doseTf;
	@FXML
	TextField untipriceTf;	
	public Stage stage;
	public Parent root;
	public Scene scene;
	public void initialize() {
		nameTf.setDisable(true);
    	manTf.setDisable(true);
    	modelTf.setDisable(true);
        quanTf.setDisable(true);
        ddTf.setDisable(true);
        mmTf.setDisable(true);
        yyTf.setDisable(true);
        unitTf.setDisable(true);
        doseTf.setDisable(true);
        untipriceTf.setDisable(true);
	}
	public void med(ActionEvent event) {
		nameTf.setDisable(false);
    	manTf.setDisable(false);
    	modelTf.setDisable(true);
        quanTf.setDisable(false);
        ddTf.setDisable(false);
        mmTf.setDisable(false);
        yyTf.setDisable(false);
        unitTf.setDisable(false);
        doseTf.setDisable(false);
        untipriceTf.setDisable(false);
		medButton.setDisable(true);
		accButton.setDisable(false);
		accButton.setSelected(false);
	}
	public void acc(ActionEvent event) {
		nameTf.setDisable(false);
    	manTf.setDisable(false);
    	modelTf.setDisable(false);
    	untipriceTf.setDisable(false);
    	quanTf.setDisable(false);
    	ddTf.setDisable(true);
        mmTf.setDisable(true);
        yyTf.setDisable(true);
        unitTf.setDisable(true);
        doseTf.setDisable(true);
		medButton.setDisable(false);
		medButton.setSelected(false);
		accButton.setDisable(true);
	}
	public void addButton(ActionEvent event) throws ClassNotFoundException, IOException {
		String name= nameTf.getText();
		String man= manTf.getText();
		String unitPrice= untipriceTf.getText();
		String quantity = quanTf.getText();
		if(medButton.isSelected()) {
			String dos = doseTf.getText();
			String unit = unitTf.getText();
			String day= ddTf.getText();
			String mm= mmTf.getText();
			String yy= yyTf.getText();		
			nameTf.clear();
			manTf.clear();
			untipriceTf.clear();
			quanTf.clear();
			doseTf.clear();
			unitTf.clear();
			ddTf.clear();
			mmTf.clear();
			yyTf.clear();			
			if(name.isEmpty() || man.isEmpty()|| unitPrice.isEmpty()|| quantity.isEmpty()|| dos.isEmpty()|| day.isEmpty()|| mm.isEmpty()|| yy.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Please fillup all fields.");
				 return;
			}
			double unitP= Double.parseDouble(unitPrice);
			int amt = Integer.parseInt(quantity);
			double dose = Double.parseDouble(dos);
		    String expDate= day+ "/" +mm+ "/" + yy;
		    
		    String id= Main.medPharma.addItem(name, man, dose,unit,unitP,amt, expDate);
		    DataHandler.saveData(Main.medPharma);
		    JOptionPane.showMessageDialog(null, id);			
		}else if(accButton.isSelected()) {
			String modelNo= modelTf.getText();
			nameTf.clear();
			manTf.clear();
			untipriceTf.clear();
			quanTf.clear();
			modelTf.clear();			
			if(name.isEmpty() || man.isEmpty()|| unitPrice.isEmpty()|| quantity.isEmpty()|| modelNo.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Please fillup all fields.");
				 return;
			}			
			double unitP= Double.parseDouble(unitPrice);
			int amt = Integer.parseInt(quantity);
			String id = Main.medPharma.addItem(name, man, modelNo, unitP, amt);
			DataHandler.saveData(Main.medPharma);
		    JOptionPane.showMessageDialog(null, id);			
		}
	}
	public void login(ActionEvent event) {
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
	public void menu(ActionEvent event) {
		try {
			root = FXMLLoader.load(getClass().getResource("EmployeeMenu.fxml"));
			stage =(Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setTitle("Employee Menu");
			stage.setScene(scene);
			stage.show();
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
}
