package application;

import javafx.scene.Node;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import med.lib.DataHandler;



public class LoginController {
	@FXML
	// they have only getter and setter methods
	private TextField idTF;
	@FXML
	private Button loginButton;
    @FXML
    private Button exitButton;
	@FXML
	private Stage stage;
	private Parent root;
	private Scene scene;
	public void logIn(ActionEvent event) {
		
		if(idTF.getText().equals("123")) {
			try {
				root = FXMLLoader.load(getClass().getResource("EmployeeMenu.fxml"));
				stage =(Stage)((Node)event.getSource()).getScene().getWindow();
				scene = new Scene(root);
				stage.setTitle("Employee Menu");
				stage.setScene(scene);
				stage.show();
			} catch(Exception e) {
				JOptionPane.showMessageDialog(null,e.getMessage());
			}
		}
		else if(idTF.getText().equals("456")) {
			try {
				root = FXMLLoader.load(getClass().getResource("CustomerMenu.fxml"));
				stage =(Stage)((Node)event.getSource()).getScene().getWindow();
				Scene scene = new Scene(root);
				stage.setTitle("Customer Menu");
				stage.setScene(scene);
				stage.show();
			} catch(Exception e) {
				JOptionPane.showMessageDialog(null,e.getMessage());
			}
		}
		else {
			JOptionPane.showMessageDialog(null, "Invalid Login ID! Try again.");
		}
	}
	public void exit(ActionEvent event) throws FileNotFoundException, IOException {
		JOptionPane.showMessageDialog(null, "Do you want to save before logout?");
		DataHandler.saveData(Main.medPharma);
	}
	
	
}
