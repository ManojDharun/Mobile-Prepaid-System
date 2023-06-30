package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.gluonhq.charm.glisten.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.fxml.FXML;

public class delete {
@FXML
TextField User;
@FXML
PasswordField Pass;

	
	public void delete() throws SQLException, ClassNotFoundException {
			try {
			String name = User.getText();
			String pass = Pass.getText();
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Mobile?useSSL=false", "root", "manoj@01");
			String sql = "DELETE FROM LOGIN WHERE Uname = ?";
			String sql1 = "DELETE FROM MOB_DET WHERE Uname=?";
			PreparedStatement pstmt = connection.prepareStatement(sql);
			PreparedStatement pstmt1 = connection.prepareStatement(sql1);
			pstmt.setString(1, name);
			pstmt1.setString(1, name);
			pstmt.execute();
			pstmt1.execute();
			}catch(Exception e) {
				System.out.println(e);
			}
	}
}
