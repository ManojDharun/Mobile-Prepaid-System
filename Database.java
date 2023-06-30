package application;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.ResultSet ;
import java.sql.Connection;
public class Database {
	Connection connection;
	public int connect(String name,String pass) throws SQLException {
		int flag = 0;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Mobile?allowPublicKeyRetrieval=true&useSSL=false", "root", "manoj@01");
			String sql = "Select * from Reg";
			PreparedStatement pstmt = connection.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				String name1 = rs.getString("Name");
				String pass1 = rs.getString("Pass");
				System.out.println(name1+"  "+pass1+"  "+name+"   "+pass);
				System.out.println();
				if(name1.equals(name) & pass1.equals(pass)) {
					flag = 1;
					break;
				}
			}
		}catch(Exception e) {
			System.out.println(e);
		}
		System.out.println(flag);
		if(flag==1) {
			String sql1 = "Insert into login(Uname,Pass) values(?,?)";
			PreparedStatement pstmt1 = connection.prepareStatement(sql1);
			pstmt1.setString(1, name);
			pstmt1.setString(2, pass);
			pstmt1.execute();
			System.out.println("HI manoj");
			return 1;
		}
		else {
			return 0;
		}
	}
	public int connect1(String uname,String mob,String Sim) throws SQLException{
		int flag = 0;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Mobile?allowPublicKeyRetrieval=true&useSSL=false", "root", "manoj@01");
			String sql1 = "Select * from Reg where name = ?";
			PreparedStatement pstmt1 = connection.prepareStatement(sql1);
			pstmt1.setString(1, uname);
			ResultSet rs = pstmt1.executeQuery();
			while(rs.next()) {
				String mobile = rs.getString("Mobile");
				System.out.println();
				if(mobile.equals(mob)) {
					flag = 1;
					break;
				}
			}
		}catch(Exception e) {
			System.out.println(e);
		}
		if(flag==1) {
			String sql = "INSERT INTO mob_det(Uname,mob,Sim) VALUES (?,?,?)";
			PreparedStatement pstmt = connection.prepareStatement(
					sql);
			pstmt.setString(1,uname);
			pstmt.setString(2,mob);
			pstmt.setString(3, Sim);
			pstmt.execute();
			return 1;
		}
		else {
			return 0;
		}
	}
}
