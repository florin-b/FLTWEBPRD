package test;

import java.sql.Connection;
import java.sql.SQLException;

import beans.User;
import beans.UserInfo;
import database.Account;
import database.DBManager;
import model.OperatiiTablete;

public class TestClass {

	public static void main(String[] args) {

		try {
			
			
			Connection conn = null;

			try {
				conn = DBManager.getProdInstance().getConnection();
			} catch (SQLException e) {
				
				
			}
			
			User user = new User();
			user.setName("ASTANISOR1");
			user.setPassword("bzcpZw");
			
			Account account = new Account(conn);
			
			account.loginUser(user);
			
			System.out.println(UserInfo.getInstance());
			
		
			//new OperatiiTablete().adaugaCod("354795051177068", "00124278");
			
			
			//System.out.println(new OperatiiTablete().getTableteSoferi("00124278"));
			
			
			
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
	}

}
