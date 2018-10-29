package test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import beans.Filiala;
import beans.User;
import beans.UserInfo;
import database.Account;
import database.DBManager;
import database.OperatiiFiliala;
import utils.MailOperations;
import utils.Utils;

public class TestClass {

	public static void main(String[] args) {

		try {
			
			
			Connection conn = null;

			try {
				conn = DBManager.getProdInstance().getConnection();
			} catch (SQLException e) {
				
				
			}
			
			User user = new User();
			user.setName("CSTATACHE");
			user.setPassword("DRm3rx");
			
			Account account = new Account(conn);
			
			account.loginUser(user);
			
			System.out.println(UserInfo.getInstance());
			
		

			
			
			
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
	}

}
