package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.control.ButtonBar.ButtonData;

public class Update {
	Connection connection;
	@FXML
	TextField text1,text2,text3,text4,text5;
	
	public void update(ActionEvent event) throws SQLException, ClassNotFoundException {
		try {
		String name1 = text1.getText();
		String name2 = text2.getText();
		String pass = text3.getText();
		String mobile = text4.getText();
		String Sim = text5.getText();
		Class.forName("com.mysql.cj.jdbc.Driver");
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Mobile?useSSL=false", "root", "manoj@01");
		String sql = "UPDATE LOGIN SET Uname=?,Pass=? WHERE Uname = ?";
		String sql1 = "UPDATE MOB_DET SET Uname = ?,mob=?,Sim=? WHERE Uname=?";
		PreparedStatement pstmt = connection.prepareStatement(sql);
		PreparedStatement pstmt1 = connection.prepareStatement(sql1);
		pstmt.setString(1, name2);
		pstmt.setString(2,pass);
		pstmt.setString(3, name1);
		pstmt.execute();
		pstmt1.setString(1, name2);
		pstmt1.setString(2, mobile);
		pstmt1.setString(3,Sim);
		pstmt1.setString(4, name1);
		pstmt1.execute();
		Dialog<String> dialog = new Dialog<String>();
		 dialog.setTitle("!INFORMATION!");
		 dialog.setContentText("!!Details changed Successfully!!:)");
		 ButtonType btn1 = new ButtonType("OK",ButtonData.OK_DONE);
		 ButtonType btn2 = new ButtonType("Cancel",ButtonData.CANCEL_CLOSE);
		 dialog.getDialogPane().getButtonTypes().add(btn1);
		 dialog.getDialogPane().getButtonTypes().add(btn2);
		 dialog.showAndWait();
		}catch(Exception e) {
			System.out.println(e);
		}
	}

}
