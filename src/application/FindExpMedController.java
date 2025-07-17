package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import med.lib.Medicine;

public class FindExpMedController implements Initializable {
	@FXML public Button expB;
	@FXML public Button menuB;
	@FXML public Button loginB;
	//@FXML ListView<String> listView ;
	
	@FXML TableView<Medicine> tableView;
	@FXML TableColumn<Medicine, String> colName;
	@FXML TableColumn<Medicine, String> colManuf;
	@FXML TableColumn<Medicine, Double> colUnitPrice;
	@FXML TableColumn<Medicine, Double> colQuantity;
	@FXML TableColumn<Medicine, String> colUnit;
	@FXML TableColumn<Medicine, Double> colDose;
	@FXML TableColumn<Medicine, String> colExpDate;
	
	private ArrayList<Medicine> expMeds;

	public Stage stage;
	public Parent root;
	public Scene scene;	
	ObservableList<Medicine> displayedItems;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		colName.setCellValueFactory(new PropertyValueFactory<Medicine,String>("name"));
        colManuf.setCellValueFactory(new PropertyValueFactory<Medicine,String>("manufacturer"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<Medicine,Double>("unitPrice"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<Medicine,Double>("quantity"));
        colUnit.setCellValueFactory(new PropertyValueFactory<Medicine,String>("unit"));
        colDose.setCellValueFactory(new PropertyValueFactory<Medicine,Double>("dose"));
        colExpDate.setCellValueFactory(new PropertyValueFactory<Medicine,String>("expirationDate"));
        expMeds = Main.medPharma.findExpiredMeds();
        displayedItems = FXCollections.observableArrayList(expMeds);
   	    tableView.setItems(displayedItems);    
		
	}
	public void initialize() {
    	colName.setCellValueFactory(new PropertyValueFactory<Medicine,String>("name"));
        colManuf.setCellValueFactory(new PropertyValueFactory<Medicine,String>("manufacturer"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<Medicine,Double>("unitPrice"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<Medicine,Double>("quantity"));
        colUnit.setCellValueFactory(new PropertyValueFactory<Medicine,String>("unit"));
        colDose.setCellValueFactory(new PropertyValueFactory<Medicine,Double>("dose"));
        colExpDate.setCellValueFactory(new PropertyValueFactory<Medicine,String>("expirationDate"));
        expMeds = Main.medPharma.findExpiredMeds();
        displayedItems = FXCollections.observableArrayList(expMeds);
   	    tableView.setItems(displayedItems);    
    	
	}
	
	public void expiredMeds(ActionEvent event) {
		expMeds = Main.medPharma.findExpiredMeds();
		if(expMeds.isEmpty()) {     	
        	 showError("No such medicine is found!");
        	 return;
         }else {
          	 displayedItems = FXCollections.observableArrayList(expMeds);
        	 tableView.setItems(displayedItems);       	        	 
         }
		
		/*if(expMeds.isEmpty()) {
        listView.getItems().add("No medicine is expired.");
        }else {
       	 listView.getItems().clear();	 
       	 for(Medicine m: expMeds) {
           	listView.getItems().add(m.toString()+"\n");
            }
        }*/
	}
	public void login(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Exit");
		alert.setContentText("Do you want to go Login page?");
		if(alert.showAndWait().get()==ButtonType.OK) {
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
	public void menu(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Exit");
		alert.setContentText("Do you want to go Employee page?");
		if(alert.showAndWait().get()==ButtonType.OK) {
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
	private void showError(String message) {
	        Alert alert = new Alert(Alert.AlertType.ERROR);
	        alert.setTitle("Error");
	        alert.setHeaderText(null);
	        alert.setContentText(message);
	        alert.showAndWait();
	    }

	
	
}
