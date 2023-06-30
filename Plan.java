package application;

import java.io.IOException;
import javafx.scene.control.DatePicker;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;

public class Plan implements Initializable{
	Connection connection;
	@FXML
	DatePicker date;
	@FXML
	TextField txt;
	@FXML
	AnchorPane apane;
	@FXML
	Button get,View;
	public void getAction() {
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("Payment.fxml"));
			Scene scene = new Scene(root,500,500);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void action5(ActionEvent event) {
		String u = "1499";
		UserHolder holder = UserHolder.getInstance();
		holder.setUser(u);
		getAction();
	}
	public void action6(ActionEvent event) {
		String u = "299";
		UserHolder holder = UserHolder.getInstance();
		holder.setUser(u);
		getAction();
	}
	public void action7(ActionEvent event) {
		//UserHolder holder = UserHolder.getInstance();
		//holder.setUser(u);
		getAction();
	}
	public void action8(ActionEvent event) {
		//UserHolder holder = UserHolder.getInstance();
		//holder.setUser(u);
		getAction();
	}
	public void action9(ActionEvent event) {
		//UserHolder holder = UserHolder.getInstance();
		//holder.setUser(u);
		getAction();
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		TableView<FileData> table = new TableView<FileData>();
		 	final ObservableList<FileData> data = FXCollections.observableArrayList(
	         new FileData("1499", "300GB Data RollOver:500GB","Unlimited", "100 SMS/day"),
	         new FileData("999", "200GB Data RollOver:500GB","Unlimited", "100 SMS/day"),
	         new FileData("499", "150GB Data RollOver:200GB", "Unlimited", "100 SMS/day"),
	         new FileData("799", "300GB Data RollOver:500GB", "Unlimited", "100 SMS/day"),
	         new FileData("899", "300GB Data RollOver:500GB", "Unlimited", "100 SMS/day"),
	         new FileData("1000", "300GB Data RollOver:500GB", "Unlimited", "100 SMS/day"),
	         new FileData("678", "300GB Data RollOver:500GB", "Unlimited", "100 SMS/day"),
	         new FileData("500", "300GB Data RollOver:500GB", "Unlimited", "100 SMS/day"),
	         new FileData("400", "300GB Data RollOver:500GB", "Unlimited", "100 SMS/day"),
	         new FileData("399", "300GB Data RollOver:500GB", "Unlimited", "100 SMS/day"),
	         new FileData("299", "300GB Data RollOver:500GB", "Unlimited", "100 SMS/day"),
	         new FileData("199", "300GB Data RollOver:500GB", "Unlimited", "100 SMS/day")
	    );
		table.setItems(data);
	    table.setMinSize(500, 500);
		TableColumn fileNameCol = new TableColumn("AMOUNT");
	    fileNameCol.setCellValueFactory(new PropertyValueFactory<>("fileName"));
	    TableColumn pathCol = new TableColumn("PLAN");
	    pathCol.setCellValueFactory(new PropertyValueFactory<>("path"));
	    TableColumn sizeCol = new TableColumn("SERVICE");
	    sizeCol.setCellValueFactory(new PropertyValueFactory<>("size"));
	    TableColumn dateCol = new TableColumn("SMS");
	    dateCol.setCellValueFactory(new PropertyValueFactory<>("dateModified"));
	    table.getColumns().addAll(fileNameCol, pathCol, sizeCol, dateCol);
	    table.setLayoutX(10);
	    table.setLayoutY(160);
	    apane.getChildren().add(table);
	}
	public void getNow(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {
		String array[] = {"1499","999","499","799","899","1000","678","500","400","399","299","199"};
		String text = txt.getText();
		LocalDate date1 = date.getValue();
		boolean flag = false;
		for(int i = 0;i < 12;i++) {
			if(text.equals(array[i])) {
				userInstance u = userInstance.getInstance();
				u.setUser(date1);
				UserHolder u1 = UserHolder.getInstance();
				u1.setUser(text);
				Dialog<String> dialog = new Dialog<String>();
				dialog.setTitle("ACTIVATED");
				dialog.setContentText("YOUR SERVICE GOT ACTIVATED FROM "+date1);
				ButtonType btn1 = new ButtonType("Ok",ButtonData.OK_DONE);
				ButtonType btn2 = new ButtonType("Cancel",ButtonData.CANCEL_CLOSE);
				dialog.getDialogPane().getButtonTypes().add(btn1);
				dialog.getDialogPane().getButtonTypes().add(btn2);
				dialog.showAndWait();
				Parent root = FXMLLoader.load(getClass().getResource("Payment.fxml"));
				Scene scene = new Scene(root,600,600);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				Stage stage = new Stage();
				stage.setScene(scene);
				stage.show();
				flag = true;
				Class.forName("com.mysql.cj.jdbc.Driver");
				connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Mobile?allowPublicKeyRetrieval=true&useSSL=false", "root", "manoj@01");
				String sql = "Insert Into plan_select values(?)";
				PreparedStatement pstmt = connection.prepareStatement(sql);
				pstmt.setString(1, text);
				pstmt.execute();
			}
		}
		if(!flag) {
				Dialog<String> dialog = new Dialog<String>();
				dialog.setTitle("!WARNING!");
				dialog.setContentText("REQUESTED PLAN IS NOT IN LIST , PLEASE SELECT ANOTHER ONE ");
				ButtonType btn1 = new ButtonType("Ok",ButtonData.OK_DONE);
				ButtonType btn2 = new ButtonType("Cancel",ButtonData.CANCEL_CLOSE);
				dialog.getDialogPane().getButtonTypes().add(btn1);
				dialog.getDialogPane().getButtonTypes().add(btn2);
				dialog.show();
		}
		
	}
	
	public void view(ActionEvent event) throws ClassNotFoundException, SQLException {
		TableView<FileData> table1 = new TableView<>();
		Class.forName("com.mysql.cj.jdbc.Driver");
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Mobile?useSSL=false", "root", "manoj@01");
		String sql = "SELECT * FROM Plans";
		PreparedStatement pstmt = connection.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		//String jtable[][];
		int i = 0;
		for(i=0 ; i<rs.getMetaData().getColumnCount(); i++){
             TableColumn col1 = new TableColumn("NAME");
             col1.setCellValueFactory(new PropertyValueFactory<>("fileName"));
             TableColumn col2 = new TableColumn("MOBILE");
             col2.setCellValueFactory(new PropertyValueFactory<>("path"));
             TableColumn col3 = new TableColumn("SIM");
             col3.setCellValueFactory(new PropertyValueFactory<>("size"));
             TableColumn col4 = new TableColumn("PLAN");
             col4.setCellValueFactory(new PropertyValueFactory<>("dateModified"));
             table1.getColumns().add(col1); 
             table1.getColumns().add(col2); 
             table1.getColumns().add(col3); 
             table1.getColumns().add(col4); 
         }
		FileData file = null;
		while (rs.next()) {
			try {	
               for (i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
            	  //data = FXCollections.observableArrayList(new FileData(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4)));
            	   	file = new FileData(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4));
     
               }
			}catch(Exception e) {
				System.out.println(e);	
			}
			table1.getItems().add(file);
		}
		AnchorPane pane = new AnchorPane();
		Stage stage = new Stage();
		Scene scene = new Scene(pane,300,500);
		stage.setScene(scene);
		stage.show();
		table1.setMinSize(300, 100);
		pane.getChildren().add(table1);
	}
}

