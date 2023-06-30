package application;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
public class Mobile implements Initializable{
	@FXML
	AnchorPane pane1;
	@FXML
	ChoiceBox<String> choice;
	@FXML
	TextField mobile;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		 choice.getItems().add("Vodafone");
		 choice.getItems().add("Airtel");
		 choice.getItems().add("Jio");
		 choice.getItems().add("Aircel");
		 choice.getItems().add("BSNL");
	}
	
	public void moveToState(ActionEvent event) throws IOException, SQLException,NumberFormatException {
		Database db = new Database();
		String u = mobile.getText();
		String p = choice.getValue();
		UserHolder holder = UserHolder.getInstance();
		String uname = holder.getUser();
		if(mobile.getText()=="" | p=="") {
			Dialog<String> dialog = new Dialog<String>();
			dialog.setTitle("!WARNING!");
			dialog.setContentText("YOU HAVE NOT ENTERED THE REQUIRED DETAILS! ");
			ButtonType btn1 = new ButtonType("Ok",ButtonData.OK_DONE);
			ButtonType btn2 = new ButtonType("Cancel",ButtonData.CANCEL_CLOSE);
			dialog.getDialogPane().getButtonTypes().add(btn1);
			dialog.getDialogPane().getButtonTypes().add(btn2);
			dialog.show();
		}
		if(u != ""||p != "") {
			System.out.println(u+ " "+p);
			int i = db.connect1(uname,u, p);
			if(i==1) {
				Parent root = FXMLLoader.load(getClass().getResource("State_select.fxml"));
				Scene scene = new Scene(root,500,500);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				Stage stage = new Stage();
				stage.setScene(scene);
				stage.show();
			}
			else {
				 Dialog<String> dialog = new Dialog<String>();
				 dialog.setTitle("!WARNING!");
				 dialog.setContentText("!!Please enter your exact Phone number!!:)");
				 ButtonType btn1 = new ButtonType("OK",ButtonData.OK_DONE);
				 ButtonType btn2 = new ButtonType("Cancel",ButtonData.CANCEL_CLOSE);
				 dialog.getDialogPane().getButtonTypes().add(btn1);
				 dialog.getDialogPane().getButtonTypes().add(btn2);
				 dialog.showAndWait();
			}
		}
	}
	
	public void update(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("Upd_del.fxml"));
		Scene scene = new Scene(root,500,500);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.show();
	}
	
	public void delete(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("Del.fxml"));
		Scene scene = new Scene(root,500,500);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.show();
	}
}
