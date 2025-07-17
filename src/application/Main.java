package application;
	

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JOptionPane;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import med.lib.DataHandler;
import med.lib.MedPharma;
import javafx.scene.Parent;
import javafx.scene.Scene;



public class Main extends Application {
	public static MedPharma medPharma = new MedPharma("UAP Medical");
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setTitle("Login Window");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	public static void main(String[] args) throws  IOException ,ClassNotFoundException {
		try {
			medPharma = DataHandler.loadData();
		} catch(FileNotFoundException e) {
			DataHandler.saveData(medPharma);
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		} 
		launch(args);
	}
}
