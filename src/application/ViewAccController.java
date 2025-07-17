package application;

import java.net.URL;
import java.time.LocalDate;
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
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import med.lib.DataHandler;
import med.lib.InvalidIOrderException;
import med.lib.InvalidItemException;
import med.lib.Medicine;
import med.lib.Order;


public class ViewAccController implements Initializable {
	@FXML  TextField orderIDTF;
	@FXML public Button menuB;
	@FXML public Button loginB;
	@FXML public Button accorderB;
	@FXML public Button viewNewOrderB;
	//@FXML ListView<String> listView ;
	@FXML TableView<Order> tableView;
	@FXML TableColumn<Order, String> colOrderID;
	@FXML TableColumn<Order, String> colItemID;
	@FXML TableColumn<Order, String> colStatus;
	@FXML TableColumn<Order, Integer> colQuantity;
	@FXML TableColumn<Order, String> colOrderBy;
	@FXML TableColumn<Order, LocalDate> colOrderDate;
	@FXML TableColumn<Order, LocalDate> colDeliveryDate;
	
//	@FXML private TreeView<String> treeView;
	public Stage stage;
	public Parent root;
	public Scene scene;
	ObservableList<Order> displayedItems;
	private  ArrayList<Order> newOrders;
	
	public void viewNewOrders(ActionEvent event) {
		newOrders = Main.medPharma.getNewOrders();
		
		displayedItems = FXCollections.observableArrayList(newOrders);
   	    tableView.setItems(displayedItems);    
		
		
		/*int total = 1;
		TreeItem<String> rootItem = new TreeItem<>("All orders are: ");
		rootItem.setExpanded(true);
		TreeItem<String> ordersRoot = new TreeItem<>("Click: ");*/
		/*if(newOrders.isEmpty()) {
			showInfo("All orders are delivered.");
			//listView.getItems().add("All orders are delivered.\n");
		}*/
		/*for(Order o: newOrders) {
			TreeItem<String> ordersItems = new TreeItem<>(""+ total + ".Order ID: "+ o.getOrderId() + ", Item ID : "+ o.getItemId()+ 
					 ", Order By: "+ o.getOrderBy() + ", Status: "+ o.getStatus() + ", Quantity: "+o.getQuantity() + ", Order Date: "+ o.getOrderDate() + ", Delivery Date: "+o.getDeliveryDate() +"\n");
			ordersRoot.getChildren().add(ordersItems);
		       total++;
			
			/*listView.getItems().clear();
			for(Order o: newOrders) {
				listView.getItems().add("               === New Orders ===   ");
				listView.getItems().add(o.toString());*/

		//rootItem.getChildren().addAll(ordersRoot);
		//treeView.setRoot(rootItem);
	}
	public void acceptOrder(ActionEvent event) throws InvalidItemException, InvalidIOrderException{
		String orderID = orderIDTF.getText();
		try {
			Order fOrder = Main.medPharma.findOrder(orderID);
			DataHandler.saveData(Main.medPharma);
			if(fOrder ==null) {
				showError("No such order is found");
			}
			boolean isAccepted = Main.medPharma.acceptOrderRequest(orderID);
			System.out.println(isAccepted);
			if(isAccepted) {
				//JOptionPane.showMessageDialog(null, "Accepted");
				showInfo("Order has been placed.");
			}else {
				showError("There is not sufficient quantity to place order.");
			}
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}

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
	private void showInfo(String msg) {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Confirmation");
		alert.setContentText(msg);
		alert.showAndWait();
		
	}
	private void showError(String msg) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Error");
		alert.setContentText(msg);
		alert.showAndWait();
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		colOrderID.setCellValueFactory(new PropertyValueFactory<Order,String>("orderId"));
        colDeliveryDate.setCellValueFactory(new PropertyValueFactory<Order,LocalDate>("deliveryDate"));
        colItemID.setCellValueFactory(new PropertyValueFactory<Order,String>("itemId"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<Order,Integer>("quantity"));
        colOrderBy.setCellValueFactory(new PropertyValueFactory<Order,String>("orderBy"));
        colOrderDate.setCellValueFactory(new PropertyValueFactory<Order,LocalDate>("orderDate"));
        colStatus.setCellValueFactory(new PropertyValueFactory<Order,String>("status"));
        newOrders = Main.medPharma.getNewOrders();
		
		displayedItems = FXCollections.observableArrayList(newOrders);
   	    tableView.setItems(displayedItems);   
		
		
		
		/*newOrders = Main.medPharma.getNewOrders();
		int total = 1;
		TreeItem<String> rootItem = new TreeItem<>("All orders are: ");
		rootItem.setExpanded(true);
		TreeItem<String> ordersRoot = new TreeItem<>("Click: ");
		/*if(newOrders.isEmpty()) {
			showInfo("All orders are delivered.");
			//listView.getItems().add("All orders are delivered.\n");
		}*/
			/*for(Order o: newOrders) {
				TreeItem<String> ordersItems = new TreeItem<>(""+ total + ".Order ID: "+ o.getOrderId() + ", Item ID : "+ o.getItemId()+ 
						 ", Order By: "+ o.getOrderBy() + ", Status: "+ o.getStatus() + ", Quantity: "+o.getQuantity() + ", Order Date: "+ o.getOrderDate() + ", Delivery Date: "+o.getDeliveryDate()  +"\n");
				ordersRoot.getChildren().add(ordersItems);
		        total++;
			
			/*listView.getItems().clear();
			for(Order o: newOrders) {
				listView.getItems().add("               === New Orders ===   ");
				listView.getItems().add(o.toString());*/
			

		//rootItem.getChildren().addAll(ordersRoot);
		//treeView.setRoot(rootItem);
		
		
		/*listView.getItems().clear();
		for(Order o: newOrders) {
			listView.getItems().add(o.toString());
		}	*/
    }
}
