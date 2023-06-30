package application;

import java.net.URL;
import java.time.LocalDate;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.scene.control.Button;

import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ButtonBar.ButtonData;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import java.sql.Connection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
public class Payment implements Initializable{
	Connection connection;
	@FXML
	Pane apane1,apane2;
	@FXML
	TextField card1,card2;
	@FXML
	PasswordField cvv1,cvv2;
	@FXML
	Button pay1,pay2;
	@FXML 
	DatePicker date1,date2,date11,date22;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		apane1.setVisible(false);
		apane2.setVisible(false);
		
	}
	public void action1(ActionEvent event) {
		
		apane1.setVisible(true);
		apane2.setVisible(false);
	}
	public void action2(ActionEvent event) {
		apane2.setVisible(true);
		apane1.setVisible(false);
	}
	public void action3(ActionEvent event) throws ClassNotFoundException, SQLException {
		UserHolder u = UserHolder.getInstance();
		int money = Integer.parseInt(u.getUser());
		String card_det = card1.getText();
		int cvv_det = Integer.parseInt(cvv1.getText());
		String name="",mob="",Sim="",Plan="";
		Class.forName("com.mysql.cj.jdbc.Driver");
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Mobile?allowPublicKeyRetrieval=true&useSSL=false", "root", "manoj@01");
		String sql = "SELECT * FROM mob_det";
		PreparedStatement pstmt = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, 
                ResultSet.CONCUR_UPDATABLE);
		ResultSet rs = pstmt.executeQuery();	
		while(rs.next()) {
			if(rs.last()) {
				name = rs.getString(1);
				mob = rs.getString(2);
				Sim = rs.getString(3);
			}
		}
		String sql1 = "SELECT * FROM plan_select";
		PreparedStatement pstmt1 = connection.prepareStatement(sql1, ResultSet.TYPE_SCROLL_SENSITIVE, 
                ResultSet.CONCUR_UPDATABLE);
		ResultSet rs1 = pstmt1.executeQuery();
		while(rs1.next()) {
			if(rs1.last()) {
				Plan = rs1.getString(1);
			}
		}
		String sql2 = "Insert into Plans(Uname,mobile,sim,plan) Values(?,?,?,?)";
		PreparedStatement pstmt2 = connection.prepareStatement(sql2, ResultSet.TYPE_SCROLL_SENSITIVE, 
                ResultSet.CONCUR_UPDATABLE);
		pstmt2.setString(1, name);
		pstmt2.setString(2, mob);
		pstmt2.setString(3, Sim);
		pstmt2.setString(4, Plan);
		pstmt2.execute();
		String sql3 = "Truncate login";
		PreparedStatement pstmt3 = connection.prepareStatement(sql3, ResultSet.TYPE_SCROLL_SENSITIVE, 
            ResultSet.CONCUR_UPDATABLE);
		pstmt3.execute();
		String sql4 = "SELECT * FROM acc_det";
		PreparedStatement pstmt4 = connection.prepareStatement(sql4, ResultSet.TYPE_SCROLL_SENSITIVE, 
                ResultSet.CONCUR_UPDATABLE);
		ResultSet rs2 = pstmt4.executeQuery();
		int flag = 0;
		float am1 = 0;
		while(rs2.next()) {
			System.out.println(card_det);
			if(rs2.getString(1).equals(card_det) & rs2.getInt(2)==cvv_det) {
				System.out.println();
				float amount = rs2.getFloat(4);
				String sql5 = "Update acc_det set amount = ? where acc= ?";
				PreparedStatement pstmt5 = connection.prepareStatement(sql5, ResultSet.TYPE_SCROLL_SENSITIVE, 
		                ResultSet.CONCUR_UPDATABLE);
				am1 = amount-money;
				if(am1>=0) {
					flag = 1;
					pstmt5.setFloat(1, (amount-money));
					pstmt5.setString(2,rs2.getString(1));
					pstmt5.execute();
				}
				else {
					break;
				}
			}
		}
		if(flag ==1) {
			Dialog<String> dialog = new Dialog<String>();
			dialog.setTitle("PAYMENT STATUS");
			dialog.setContentText("!Your Payment is Successful!");
			ButtonType btn1 = new ButtonType("Ok",ButtonData.OK_DONE);
			ButtonType btn2 = new ButtonType("Cancel",ButtonData.CANCEL_CLOSE);
			dialog.getDialogPane().getButtonTypes().add(btn1);
			dialog.getDialogPane().getButtonTypes().add(btn2);
			dialog.show();
		}
		else {
			Dialog<String> dialog = new Dialog<String>();
			dialog.setTitle("PAYMENT STATUS");
			dialog.setContentText("!Your Payment is Failed :( !");
			ButtonType btn1 = new ButtonType("Ok",ButtonData.OK_DONE);
			ButtonType btn2 = new ButtonType("Cancel",ButtonData.CANCEL_CLOSE);
			dialog.getDialogPane().getButtonTypes().add(btn1);
			dialog.getDialogPane().getButtonTypes().add(btn2);
			dialog.show();
		}
	}
	public void action4(ActionEvent event) throws ClassNotFoundException, SQLException{
		String name="",mob="",Sim="",Plan="";
		Class.forName("com.mysql.cj.jdbc.Driver");
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Mobile?allowPublicKeyRetrieval=true&useSSL=false", "root", "manoj@01");
		String sql = "SELECT * FROM mob_det";
		PreparedStatement pstmt = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, 
                ResultSet.CONCUR_UPDATABLE);
		ResultSet rs = pstmt.executeQuery();	
		while(rs.next()) {
			if(rs.last()) {
				name = rs.getString(1);
				mob = rs.getString(2);
				Sim = rs.getString(3);
			}
		}
		String sql1 = "SELECT * FROM plan_select";
		PreparedStatement pstmt1 = connection.prepareStatement(sql1, ResultSet.TYPE_SCROLL_SENSITIVE, 
                ResultSet.CONCUR_UPDATABLE);
		ResultSet rs1 = pstmt1.executeQuery();
		while(rs1.next()) {
			if(rs1.last()) {
				Plan = rs1.getString(1);
			}
		}
		String sql2 = "Insert into Plans(Uname,mobile,sim,plan) Values(?,?,?,?)";
		PreparedStatement pstmt2 = connection.prepareStatement(sql2, ResultSet.TYPE_SCROLL_SENSITIVE, 
                ResultSet.CONCUR_UPDATABLE);
		pstmt2.setString(1, name);
		pstmt2.setString(2, mob);
		pstmt2.setString(3, Sim);
		pstmt2.setString(4, Plan);
		pstmt2.execute();
		String sql3 = "Truncate login";
		PreparedStatement pstmt3 = connection.prepareStatement(sql3, ResultSet.TYPE_SCROLL_SENSITIVE, 
            ResultSet.CONCUR_UPDATABLE);
		pstmt3.execute();
		Dialog<String> dialog = new Dialog<String>();
		dialog.setTitle("PAYMENT STATUS");
		dialog.setContentText("!Your Payment is Successful!");
		ButtonType btn1 = new ButtonType("Ok",ButtonData.OK_DONE);
		ButtonType btn2 = new ButtonType("Cancel",ButtonData.CANCEL_CLOSE);
		dialog.getDialogPane().getButtonTypes().add(btn1);
		dialog.getDialogPane().getButtonTypes().add(btn2);
		dialog.show();
	}
	
	public void display(ActionEvent event) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Mobile?useSSL=false", "root", "manoj@01");
		userInstance ins = userInstance.getInstance();
		LocalDate date = ins.getUser();
		UserHolder u = UserHolder.getInstance();
		String money = u.getUser();
		int money1 = Integer.parseInt(money);
		LocalDate date3 = date11.getValue();
		System.out.println(date);
		int days = date3.getDayOfYear() - date.getDayOfYear();
		if(days>30 & days<35) {
			money1 = money1 + 10;
		}
		else if(days>35 & days<40) {
			money1 = money1 + 20;
		}
		else if(days>40) {
			money1 = money1 + 40;
		}
		Dialog<String> dialog = new Dialog<String>();
		dialog.setTitle("DATE BETWEEN THE USAGE");
		dialog.setContentText("You Have used the service for "+days+"\n"+"Your Recharge amount is "+money1);
		ButtonType btn1 = new ButtonType("Ok",ButtonData.OK_DONE);
		ButtonType btn2 = new ButtonType("Cancel",ButtonData.CANCEL_CLOSE);
		dialog.getDialogPane().getButtonTypes().add(btn1);
		dialog.getDialogPane().getButtonTypes().add(btn2);
		dialog.show();
	}
	
	public void dis_am(ActionEvent event) throws ClassNotFoundException, SQLException{
		Class.forName("com.mysql.cj.jdbc.Driver");
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Mobile?useSSL=false", "root", "manoj@01");
		userInstance ins = userInstance.getInstance();
		LocalDate date = ins.getUser();
		UserHolder u = UserHolder.getInstance();
		String money = u.getUser();
		int money1 = Integer.parseInt(money);
		LocalDate date3 = date11.getValue();
		System.out.println(date);
		int days = date3.getDayOfYear() - date.getDayOfYear();
		if(days>30 & days<35) {
			money1 = money1 + 10;
		}
		else if(days>35 & days<40) {
			money1 = money1 + 20;
		}
		else if(days>40) {
			money1 = money1 + 40;
		}
		Dialog<String> dialog = new Dialog<String>();
		dialog.setTitle("DATE BETWEEN THE USAGE");
		dialog.setContentText("You Have used the service for "+days+" days"+"\n"+"Your Recharge amount is "+money1);
		ButtonType btn1 = new ButtonType("Ok",ButtonData.OK_DONE);
		ButtonType btn2 = new ButtonType("Cancel",ButtonData.CANCEL_CLOSE);
		dialog.getDialogPane().getButtonTypes().add(btn1);
		dialog.getDialogPane().getButtonTypes().add(btn2);
		dialog.show();
	}
	
}
