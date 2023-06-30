package application;
import javafx.scene.control.TextField;
import javafx.scene.control.ButtonBar.ButtonData;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
public class view {
	Connection connection;
	@FXML
	TextField text;
	@FXML
	Button sub;
	public void submit(ActionEvent event) throws ClassNotFoundException, SQLException {
		String data = "";
		String name = text.getText();
		Class.forName("com.mysql.cj.jdbc.Driver");
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Mobile?allowPublicKeyRetrieval=true&useSSL=false", "root", "manoj@01");
		String sql1 = "Select * from Reg where Name=?";
		PreparedStatement pstmt = connection.prepareStatement(sql1);
		pstmt.setString(1, name);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			for(int i = 0;i<rs.getMetaData().getColumnCount();i++) {
				data = data + "Name: " +rs.getString(1)+"\n" +"Mobile: "+rs.getString(2)+"\n"+"Plan: "+rs.getString(3)+"\n"+"Email: "+rs.getString(4)+"\n\n";
			}
		}
		 Dialog<String> dialog = new Dialog<String>();
		 dialog.setTitle("!WARNING!");
		 dialog.setContentText(data);
		 ButtonType btn1 = new ButtonType("OK",ButtonData.OK_DONE);
		 ButtonType btn2 = new ButtonType("Cancel",ButtonData.CANCEL_CLOSE);
		 dialog.getDialogPane().getButtonTypes().add(btn1);
		 dialog.getDialogPane().getButtonTypes().add(btn2);
		 dialog.showAndWait();
	}
}
