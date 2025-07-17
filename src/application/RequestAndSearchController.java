package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.stage.Stage;
import med.lib.DataHandler;
import med.lib.Item;
import med.lib.MedicalAccessory;
import med.lib.Medicine;

public class RequestAndSearchController implements Initializable{
	@FXML
	Button searchButton;
	@FXML  
	Button orderButton;
	@FXML
	Button menuButton;
	@FXML
	Button loginButton;
	@FXML
	TextField nameTF;
	@FXML
	TextField manTF;
	@FXML
	TextField productIDTF;
	@FXML
	TextField contactTF;
	@FXML
	TextField quantityTF;
	//@FXML TextArea listTA;
	
	 @FXML
	 private TreeView<String> treeView;
	 
	public Stage stage;
	public Parent root;
	public Scene scene;
	@SuppressWarnings("unchecked")
	public void search(ActionEvent event) {
		String name = nameTF.getText();
		String manufacturer = manTF.getText();
		nameTF.clear();
        manTF.clear();
		if(name.isEmpty() || manufacturer.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Please fill up all fields.");
			return ;
		}
		 
         
		ArrayList <Item> items = Main.medPharma.findItems(name, manufacturer);
		
		TreeItem<String> rootItem = new TreeItem<>("All products are: ");
        rootItem.setExpanded(true);
        TreeItem<String> medicineRoot = new TreeItem<>("Medicines: ");
        TreeItem<String> accessoryRoot = new TreeItem<>("Medical Accessories: ");
        int countM = 1;
        int countMA= 1;
        for (Item i : items) {
        	if( i instanceof Medicine) {
        		TreeItem<String> medicineItems = new TreeItem<>(""+countM+ ". Name: " +i.getName() + ", Manufacturer: "+ i.getManufacturer() +", ID: " +i.getId() + ", Unit Price: "+i.getUnitPrice()+    
        				", Quantity: "+i.getQuantity() + ", Dose: "+((Medicine)i).getDose()+ ", Unit: "+((Medicine)i).getUnit()+ ", Expiration Date: "+((Medicine)i).getExpirationDate() +"\n"/*i.toString()*/);
                medicineRoot.getChildren().add(medicineItems);
                countM++;
        	}
        	else {
        		TreeItem<String> accessoryItem = new TreeItem<>(""+countMA+ ". Name: " +i.getName() + ", Manufacturer: "+ i.getManufacturer() +", ID: " +i.getId() + ", Unit Price: "+i.getUnitPrice()+
        				", Model Number: "+ ((MedicalAccessory)i).getModelNo()+ ", Quantity: "+i.getQuantity() + "\n");
                accessoryRoot.getChildren().add(accessoryItem);
                countMA++;
        	}
        }
        rootItem.getChildren().addAll(medicineRoot, accessoryRoot);
        treeView.setRoot(rootItem);
		
        /*listTA.clear();
		for(Item i: items) {
			listTA.appendText(i + "\n");
		}*/
	}
	public void order(ActionEvent event)  throws ClassNotFoundException, IOException{
		try{
			String productID = productIDTF.getText();
			String contactNo = contactTF.getText();
			String quan = quantityTF.getText();
			int quantity = Integer.parseInt(quan);
			productIDTF.clear();
			contactTF.clear();
			quantityTF.clear();
			if(productID.isEmpty() || contactNo.isEmpty()|| quan.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Please fillup all fields.");
				 return;
			}		
			String orderID = Main.medPharma.orderItem(productID, contactNo, quantity);
			DataHandler.saveData(Main.medPharma);
			JOptionPane.showMessageDialog(null, orderID);
		}catch(NumberFormatException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
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
			root = FXMLLoader.load(getClass().getResource("CustomerMenu.fxml"));
			stage =(Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setTitle("Customer Menu");
			stage.setScene(scene);
			stage.show();
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ArrayList <Item> items = Main.medPharma.getItems();
		TreeItem<String> rootItem = new TreeItem<>("All products are: ");
        rootItem.setExpanded(true);
        TreeItem<String> medicineRoot = new TreeItem<>("Medicines: ");
        TreeItem<String> accessoryRoot = new TreeItem<>("Medical Accessories: ");
        int countM = 1;
        int countMA= 1;
        for (Item i : items) {
        	if( i instanceof Medicine) {
        		TreeItem<String> medicineItems = new TreeItem<>(""+countM+ ". Name: " +i.getName() + ", Manufacturer: "+ i.getManufacturer() +", ID: " +i.getId() + ", Unit Price: "+i.getUnitPrice()+    
        				", Quantity: "+i.getQuantity() + ", Dose: "+((Medicine)i).getDose()+ ", Unit: "+((Medicine)i).getUnit()+ ", Expiration Date: "+((Medicine)i).getExpirationDate() +"\n" );
                medicineRoot.getChildren().add(medicineItems);
                countM++;
        	}
        	else {
        		TreeItem<String> accessoryItem = new TreeItem<>(""+countMA+ ". Name: " +i.getName() + ", Manufacturer: "+ i.getManufacturer() +", ID: " +i.getId() + ", Unit Price: "+i.getUnitPrice()+
        				", Model Number: "+ ((MedicalAccessory)i).getModelNo()+ ", Quantity: "+i.getQuantity() + "\n");
                accessoryRoot.getChildren().add(accessoryItem);
                countMA++;
        	}
        	
        }
        rootItem.getChildren().addAll(medicineRoot, accessoryRoot);
        treeView.setRoot(rootItem);
		/*listTA.clear();
		for(Item i: items) {
			listTA.appendText(i + "\n");
		}*/
		
	}
	
	
	
}
