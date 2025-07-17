package application;


import java.util.ArrayList;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.stage.Stage;
import med.lib.DataHandler;
import med.lib.InvalidIOrderException;
import med.lib.InvalidItemException;
import med.lib.Order;

public class ViewOrderAndPayBillController {
	@FXML
	Button searchB;
	@FXML
	Button payB;
	@FXML
	Button menuB;
	@FXML
	Button loginB;
	@FXML
	TextField orderIDTF;
	@FXML
	TextField contactTF;
	@FXML
	private TreeView<String> treeView;
	public Stage stage;
	public Parent root;
	public Scene scene;
	
	private ArrayList<Order> customerOrders;
	
	
	@SuppressWarnings("unchecked")
	public void search(ActionEvent event) {
		String contactNo = contactTF.getText();
		if(contactNo.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Please fill up Contact field");
			return;
		}
		customerOrders = Main.medPharma.getMyOrder(contactNo);
		if(customerOrders.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Invalid Contact number!");
		}
		int t = 1;
		TreeItem<String> rootItem = new TreeItem<>("Your all orders are: ");
		rootItem.setExpanded(true);
		TreeItem<String> ordersRoot = new TreeItem<>("Here: ");
		for(Order o: customerOrders) {
			TreeItem<String> ordersItems = new TreeItem<>(""+ t + ".Order ID: "+ o.getOrderId() + ", Item ID : "+ o.getItemId()+ 
					 ", Order By: "+ o.getOrderBy() + ", Status: "+ o.getStatus() + ", Quantity: "+o.getQuantity() + ", Order Date: "+ o.getOrderDate() + ", Delivery Date: "+o.getDeliveryDate() +"\n");
			ordersRoot.getChildren().add(ordersItems);
		       t++;
		}
		rootItem.getChildren().addAll(ordersRoot);
		treeView.setRoot(rootItem);
	}
	public void payBill(ActionEvent event) {
		String orderID = orderIDTF.getText();
		if(orderID.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Please fill up OrderID field");
			return ;
		}
		
		try {
			double payAmount = Main.medPharma.payBill(orderID);
			DataHandler.saveData(Main.medPharma);
			JOptionPane.showMessageDialog(null, "Your total cost is: "+payAmount);
		}catch (InvalidIOrderException e) {
			
			JOptionPane.showMessageDialog(null, e.getMessage());			
		}catch (InvalidItemException e) {
			
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
			Scene scene = new Scene(root);
			stage.setTitle("Customer Menu");
			stage.setScene(scene);
			stage.show();
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
}
