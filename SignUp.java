package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.gluonhq.charm.glisten.control.TextField;

import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ButtonBar.ButtonData;

public class SignUp{
		@FXML
		TextField Name;
		@FXML
		PasswordField Pass;
		@FXML
		TextField Mobile;
		@FXML
		TextField EmailID;
		@FXML 
		
		Connection connection;
		public void reg(ActionEvent event) throws ClassNotFoundException, SQLException {
			String Name1 = Name.getText();
			String Pass1 = Pass.getText();
			String Mobile1 = Mobile.getText();
			String EmailID1 = EmailID.getText();
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Mobile?allowPublicKeyRetrieval=true&useSSL=false", "root", "manoj@01");
			String sql = "INSERT INTO reg(Name,Pass,Mobile,Email) VALUES (?,?,?,?)";
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(1,Name1);
			pstmt.setString(2, Pass1);
			pstmt.setString(3, Mobile1);
			pstmt.setString(4, EmailID1);
			pstmt.execute();
			Dialog<String> dialog = new Dialog<String>();
			dialog.setTitle("SUCCESSFULLY REGISTERED!");
			dialog.setContentText("Your Registeration process is successful");
			ButtonType btn1 = new ButtonType("Ok",ButtonData.OK_DONE);
			ButtonType btn2 = new ButtonType("Cancel",ButtonData.CANCEL_CLOSE);
			dialog.getDialogPane().getButtonTypes().add(btn1);
			dialog.getDialogPane().getButtonTypes().add(btn2);
			dialog.show();
		}

}
