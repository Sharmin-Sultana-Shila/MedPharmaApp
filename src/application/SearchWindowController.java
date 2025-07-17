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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

import javafx.scene.control.RadioButton;

import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import med.lib.Item;
import med.lib.Medicine;
import med.lib.MedicalAccessory;


public class SearchWindowController implements Initializable{
	@FXML RadioButton medicineRB;
    @FXML RadioButton accessoryRB;
    @FXML RadioButton bothRB;
    @FXML  TextField nameField;
    @FXML  TextField manufacturerField;
    @FXML TextField unitPriceField;
    @FXML  TextField modelNumberField; // For accessories
    @FXML TextField unitField;       // For medicines
    @FXML TextField doseField;       // For medicines
    @FXML TextField dayField;        // Expiration date
    @FXML TextField monthField;      // Expiration date
    @FXML TextField yearField;       // Expiration date
    @FXML Button searchButton;
    @FXML Button menuButton;
    @FXML Button loginButton;
   // @FXML ListView<String> listView ;
    
   /* @FXML TableView<DisplayItem> tableView;
	@FXML TableColumn<Item, String> colName;
	@FXML TableColumn<Item, String> colManuf;
	@FXML TableColumn<Item, Double> colUnitPrice;
	@FXML TableColumn<Item, Integer> colQuantity;
	@FXML TableColumn<Item, String> colUnit;
	@FXML TableColumn<Item, String> colModelNo;
	@FXML TableColumn<Item, Double> colDose;
	@FXML TableColumn<Item, String> colExpDate;*/
	
	@FXML private TreeView<String> treeView;
	//ObservableList<DisplayItem> displayItems ;
	
    private  ArrayList<Medicine> meds;
	private  ArrayList<MedicalAccessory> medAcc;
    private ArrayList<Item> both;
    
    public Stage stage;
	public Parent root;
	public Scene scene;   
    
    public void med(ActionEvent event) {
    	nameField.setDisable(false);
    	unitField.setDisable(false);
        doseField.setDisable(false);
        dayField.setDisable(false);
        monthField.setDisable(false);
        yearField.setDisable(false);
        manufacturerField.setDisable(false);
        unitPriceField.setDisable(false);
    	modelNumberField.setDisable(true);
    	
    	medicineRB.setDisable(true);
    	accessoryRB.setSelected(false);
    	accessoryRB.setDisable(false);
    	bothRB.setSelected(false);
    	bothRB.setDisable(false);
    	
    }
    public void acc(ActionEvent event) {
    	modelNumberField.setDisable(false);
    	nameField.setDisable(false);
    	manufacturerField.setDisable(false);
    	unitPriceField.setDisable(false);
    	unitField.setDisable(true);
        doseField.setDisable(true);
        dayField.setDisable(true);
        monthField.setDisable(true);
        yearField.setDisable(true);
    	
    	accessoryRB.setDisable(true);
    	medicineRB.setDisable(false);
    	medicineRB.setSelected(false);
    	bothRB.setDisable(false);
    	bothRB.setSelected(false);
    	
    }
    public void both(ActionEvent event) {
    	nameField.setDisable(false);
    	manufacturerField.setDisable(false);
    	modelNumberField.setDisable(true);
    	unitField.setDisable(true);
        doseField.setDisable(true);
        dayField.setDisable(true);
        monthField.setDisable(true);
        yearField.setDisable(true);
        unitPriceField.setDisable(true);
    	
        medicineRB.setDisable(false);
    	medicineRB.setSelected(false);
    	accessoryRB.setSelected(false);
    	accessoryRB.setDisable(false);
    	bothRB.setDisable(true);
    }
    
    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    @SuppressWarnings("unchecked")
	public void searchButton(ActionEvent event) throws NumberFormatException,IOException{
    	
    	String name = nameField.getText();
        String manufacturer = manufacturerField.getText();
        
       // displayItems = FXCollections.observableArrayList();
		
		TreeItem<String> rootItem = new TreeItem<>("All products are: ");
        rootItem.setExpanded(true);
        TreeItem<String> medicineRoot = new TreeItem<>("Medicines: ");
        TreeItem<String> accessoryRoot = new TreeItem<>("Medical Accessories: ");
        
        if(medicineRB.isSelected()) {
        	 String unitPriceText = unitPriceField.getText();
             String unit = unitField.getText();
             String doseText = doseField.getText();
             String dayText = dayField.getText();
             String monthText = monthField.getText();
             String yearText = yearField.getText();
             
            /* colModelNo.setEditable(false);
             colDose.setEditable(true);
             colExpDate.setEditable(true);
         	 colUnit.setEditable(true);*/
             
             nameField.clear();
             manufacturerField.clear();
             unitPriceField.clear();
             unitField.clear();
             doseField.clear();
             dayField.clear();
             monthField.clear();
             yearField.clear();
             
             if (name.isEmpty() || manufacturer.isEmpty() || unitPriceText.isEmpty() || unit.isEmpty() || doseText.isEmpty() || dayText.isEmpty() || monthText.isEmpty() || yearText.isEmpty()) {
                     showError("Please fill all required fields for medicine.");
                     return;
             }
             
             double unitPrice = Double.parseDouble(unitPriceText);
             double dose = Double.parseDouble(doseText);
             String expirationDate = dayText + "/" + monthText + "/" + yearText;

             meds = Main.medPharma.findItems(name, manufacturer, dose, unit, unitPrice, expirationDate);
             if(meds.isEmpty()) {
            	 // listView.getItems().add("No such medicine is found.\n");
            	 // tableView.setPlaceholder(new Label("No such medicine is found."));
            	 showError("No such medicine is found!");
            	 return;
             }
            	 
             //medicines = FXCollections.observableArrayList(meds);
             //tableView.setItems(medicines);            	 
             int countMedicine = 1;
             for (Medicine med : meds) {
         		  
         		 TreeItem<String> medicineItems = new TreeItem<>(""+countMedicine+ ". Name: " +med.getName() + ", Manufacturer: "+ med.getManufacturer() +", ID: " +med.getId() + ", Unit Price: "+med.getUnitPrice()+    
         				", Quantity: "+med.getQuantity() + ", Dose: "+med.getDose()+ ", Unit: "+med.getUnit()+ ", Expiration Date: "+med.getExpirationDate() +"\n");
                 medicineRoot.getChildren().add(medicineItems);
                 countMedicine++;
         		  
                 // displayItems.add(new DisplayItem(med.getName(), med.getManufacturer(), med.getUnitPrice(), "", med.getUnit(), med.getDose(), med.getQuantity(),expDate));
             }
             
             
             
            // tableView.setItems(displayItems);
            	 /* listView.getItems().clear();	 	
            	 for(Medicine m: meds) {
            		// listView.getItems().add("    === Medicine ===   \n");
                	// listView.getItems().add(m.toString()+"\n");
            		 tableView.setPlaceholder(new Label("             ===Medicine===     "));
            		 
            		// displayedItems.addAll(meds);
                 }*/       
             
        }else if(accessoryRB.isSelected()) {
        	String modelNumber = modelNumberField.getText();
        	String unitPriceText = unitPriceField.getText();
        	
        	/*colDose.setEditable(false);
        	colExpDate.setEditable(false);
        	colUnit.setEditable(false);
        	colModelNo.setEditable(true);*/
        	
        	nameField.clear();
            manufacturerField.clear();
            unitPriceField.clear();
            modelNumberField.clear();
            
            if (name.isEmpty() || manufacturer.isEmpty() || unitPriceText.isEmpty() || modelNumber.isEmpty()) {
                showError("Please fill all required fields for accessory.");
                return;
            }
            double unitPrice = Double.parseDouble(unitPriceText);
            medAcc = Main.medPharma.findItems(name, manufacturer, modelNumber, unitPrice);
            if(medAcc.isEmpty()) {
               //listView.getItems().add("No such accessory is found.\n");
               //tableView.setPlaceholder(new Label("No such accessory is found."));
            	
            	showError("No such accessory is found!");
           	 	return;
            }
            int countMedicalAccessory= 1;
            for (MedicalAccessory acc : medAcc) {
            	TreeItem<String> accessoryItem = new TreeItem<>(""+countMedicalAccessory+ ". Name: " +acc.getName() + ", Manufacturer: "+ acc.getManufacturer() +", ID: " +acc.getId() + ", Unit Price: "+acc.getUnitPrice()+
        				", Model Number: "+ acc.getModelNo()+ ", Quantity: "+acc.getQuantity() + "\n");
                accessoryRoot.getChildren().add(accessoryItem);
                countMedicalAccessory++;
            	
            	//displayItems.add(new DisplayItem(acc.getName(), acc.getManufacturer(), acc.getUnitPrice(),  acc.getModelNo(),"", 0.0,acc.getQuantity() ,"" ));
            }
            
            
            //tableView.setItems(displayItems);
            	
            	//accessories = FXCollections.observableArrayList(medAcc);
            	//tableView.setItems(accessories);
            	
            	/*	 listView.getItems().clear();
           	 	for(MedicalAccessory acc: medAcc) {
           		 	listView.getItems().add("    === Medical Accessory ===   \n");
               	 	listView.getItems().add(acc.toString());
                }  */
            
        }else if(bothRB.isSelected()){
        	nameField.clear();
            manufacturerField.clear();
            
            
            if (name.isEmpty() || manufacturer.isEmpty()) {
                showError("Please fill all required fields for accessory.");
                return;
            }
            both = Main.medPharma.findItems(name, manufacturer); 
            if(both.isEmpty()) {
              //	 listView.getItems().add("No such item is found.\n");
            	showError("No such item is found!");
           	    return;
            }
            int countMedicine = 1;
            int countMedicalAccessory= 1;
            for (Item item : both) {
                if (item instanceof Medicine) {
                	TreeItem<String> medicineItems = new TreeItem<>(""+countMedicine+ ". Name: " +item.getName() + ", Manufacturer: "+ item.getManufacturer() +", ID: " +item.getId() + ", Unit Price: "+item.getUnitPrice()+    
            				", Quantity: "+item.getQuantity() + ", Dose: "+((Medicine)item).getDose()+ ", Unit: "+((Medicine)item).getUnit()+ ", Expiration Date: "+((Medicine)item).getExpirationDate() +"\n"/*i.toString()*/);
                    medicineRoot.getChildren().add(medicineItems);
                    countMedicine++;
                	
                	
                    //displayItems.add(new DisplayItem(med.getName(), med.getManufacturer(), med.getUnitPrice(), "", med.getUnit(), med.getDose(), med.getQuantity(),expDate));
                } 
                else if (item instanceof MedicalAccessory) {
                	TreeItem<String> accessoryItem = new TreeItem<>(""+countMedicalAccessory+ ". Name: " +item.getName() + ", Manufacturer: "+ item.getManufacturer() +", ID: " +item.getId() + ", Unit Price: "+item.getUnitPrice()+
            				", Model Number: "+ ((MedicalAccessory)item).getModelNo()+ ", Quantity: "+item.getQuantity() + "\n");
                    accessoryRoot.getChildren().add(accessoryItem);
                    countMedicalAccessory++;
                	
                    //MedicalAccessory acc = (MedicalAccessory) item;
                    //displayItems.add(new DisplayItem(acc.getName(), acc.getManufacturer(), acc.getUnitPrice(),  acc.getModelNo(),"", 0.0,acc.getQuantity() ,"" ));
                }
            }
           //tableView.setItems(displayItems);
            
            	   //combined = FXCollections.observableArrayList(both);
            	   //tableView.setItems(combined);
            	   //tableView.setItems(combined);
              	// listView.getItems().clear();
              	//ObservableList<String> newItems = FXCollections.observableArrayList
              	 //for(Item i: both) {
              	//	 listView.getItems().add("    === Both ===   \n");
                 // 	 listView.getItems().add(i.toString());
                  // }
               
        }
        rootItem.getChildren().addAll(medicineRoot, accessoryRoot);
        treeView.setRoot(rootItem);
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


	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		/*colName.setCellValueFactory(new PropertyValueFactory<Item,String>("name"));
        colManuf.setCellValueFactory(new PropertyValueFactory<Item,String>("manufacturer"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<Item,Double>("unitPrice"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<Item,Integer>("quantity"));
        colModelNo.setCellValueFactory(new PropertyValueFactory<Item,String>("modelNo"));
        colUnit.setCellValueFactory(new PropertyValueFactory<Item,String>("unit"));
        colDose.setCellValueFactory(new PropertyValueFactory<Item,Double>("dose"));
        colExpDate.setCellValueFactory(new PropertyValueFactory<Item,String>("expirationDate"));
    	tableView.setItems(displayItems);*/
    	
		ArrayList <Item> items = Main.medPharma.getItems();
		TreeItem<String> rootItem = new TreeItem<>("All products are: ");
        rootItem.setExpanded(true);
        TreeItem<String> medicineRoot = new TreeItem<>("Medicines: ");
        TreeItem<String> accessoryRoot = new TreeItem<>("Medical Accessories: ");
        
        int countMedicine = 1;
        int countMedicalAccessory= 1;
        for (Item item : items) {
            if (item instanceof Medicine) {
            	TreeItem<String> medicineItems = new TreeItem<>(""+countMedicine+ ". Name: " +item.getName() + ", Manufacturer: "+ item.getManufacturer() +", ID: " +item.getId() + ", Unit Price: "+item.getUnitPrice()+    
        				", Quantity: "+item.getQuantity() + ", Dose: "+((Medicine)item).getDose()+ ", Unit: "+((Medicine)item).getUnit()+ ", Expiration Date: "+((Medicine)item).getExpirationDate() +"\n"/*i.toString()*/);
                medicineRoot.getChildren().add(medicineItems);
                countMedicine++;
            } 
            else if (item instanceof MedicalAccessory) {
            	TreeItem<String> accessoryItem = new TreeItem<>(""+countMedicalAccessory+ ". Name: " +item.getName() + ", Manufacturer: "+ item.getManufacturer() +", ID: " +item.getId() + ", Unit Price: "+item.getUnitPrice()+
        				", Model Number: "+ ((MedicalAccessory)item).getModelNo()+ ", Quantity: "+item.getQuantity() + "\n");
                accessoryRoot.getChildren().add(accessoryItem);
                countMedicalAccessory++;
            }
        }
        rootItem.getChildren().addAll(medicineRoot, accessoryRoot);
        treeView.setRoot(rootItem);
        
    	modelNumberField.setDisable(true);
    	unitField.setDisable(true);
        doseField.setDisable(true);
        dayField.setDisable(true);
        monthField.setDisable(true);
        yearField.setDisable(true);
        nameField.setDisable(true);
        manufacturerField.setDisable(true);
        unitPriceField.setDisable(true);
	}
}
