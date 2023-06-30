package application;

import java.io.IOException;
import javafx.scene.control.ButtonType;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.animation.RotateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.Dialog;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Login implements Initializable{
	@FXML
	AnchorPane apane;
	Pane pane;
	ImageView menuImg;
	Button button;
	@FXML
	TextField Uname;
	@FXML
	TextField Pass;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	      Box box = new Box();  
	      
	      //Setting the properties of the Box 
	      box.setWidth(150.0); 
	      box.setHeight(150.0);   
	      box.setDepth(150.0);       
	       
	      //Creating the translation transformation 
	      Translate translate = new Translate();       
	      translate.setX(400); 
	      translate.setY(300); 
	      
	      translate.setZ(25);  
	       
	      Rotate rxBox = new Rotate(0, 0, 0, 0, Rotate.X_AXIS); 
	      Rotate ryBox = new Rotate(0, 0, 0, 0, Rotate.Y_AXIS); 
	      Rotate rzBox = new Rotate(0, 0, 0, 0, Rotate.Z_AXIS); 
	      rxBox.setAngle(30); 
	      ryBox.setAngle(50); 
	      rzBox.setAngle(30); 
	      box.getTransforms().addAll(translate,rxBox, ryBox, rzBox);  
	      PhongMaterial material = new PhongMaterial();
	      material.setDiffuseColor(Color.LIGHTYELLOW);
	      box.setMaterial(material);
	      apane.getChildren().add(box);
	      //Setting the properties of the Box 
	      RotateTransition rt = new RotateTransition();
	      rt.setAxis(Rotate.Y_AXIS);
	      
	      rt.setByAngle(360);  
	      rt.setCycleCount(500);  
	      rt.setDuration(Duration.millis(2000));
	      rt.setNode(box);
	      rt.play();
		MenuBar menubar = new MenuBar();
		Menu menu = new Menu("Menu Name");
		MenuItem item = new MenuItem();
		menu.getItems().add(item);
		menubar.getMenus().add(menu);
		
	}
	
	@FXML
	public void action(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
		Database db = new Database();
		String u = Uname.getText();
		String p = Pass.getText();
		if(u == ""||p == "") {
			 Dialog<String> dialog = new Dialog<String>();
			 dialog.setTitle("DialogBox");
			 dialog.setContentText("!!Please fill the required field!!:)");
			 ButtonType btn1 = new ButtonType("OK",ButtonData.OK_DONE);
			 ButtonType btn2 = new ButtonType("Cancel",ButtonData.CANCEL_CLOSE);
			 dialog.getDialogPane().getButtonTypes().add(btn1);
			 dialog.getDialogPane().getButtonTypes().add(btn2);
			 dialog.showAndWait();
		}
		if(u != ""||p != "") {
			System.out.println(u+ " "+p);
			int i = db.connect(u,p);
			if(i==1) {
				Parent root = FXMLLoader.load(getClass().getResource("Mobile_det.fxml"));
				Scene scene = new Scene(root,500,500);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				Stage stage = new Stage();
				UserHolder holder = UserHolder.getInstance();
				holder.setUser(u);
				stage.setScene(scene);
				//stage.setUserData(u);
				stage.show();
			}
			else {
				 Dialog<String> dialog = new Dialog<String>();
				 dialog.setTitle("!WARNING!");
				 dialog.setContentText("!!Please enter your exact login credentials!!:)");
				 ButtonType btn1 = new ButtonType("OK",ButtonData.OK_DONE);
				 ButtonType btn2 = new ButtonType("Cancel",ButtonData.CANCEL_CLOSE);
				 dialog.getDialogPane().getButtonTypes().add(btn1);
				 dialog.getDialogPane().getButtonTypes().add(btn2);
				 dialog.showAndWait();
			}
		}
	}
	
	public void Register(ActionEvent event) throws IOException{
		Parent root = FXMLLoader.load(getClass().getResource("SignUp.fxml"));
		Scene scene = new Scene(root,500,500);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.show();
	}
	
	public void view(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("view_select.fxml"));
		Scene scene = new Scene(root,500,500);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.show();
	}

}
