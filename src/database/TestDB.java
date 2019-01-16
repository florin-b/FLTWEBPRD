package database;

import java.sql.Connection;
import java.sql.SQLException;

import beans.User;
import utils.Utils;

public class TestDB {

	public static void main(String[] args) throws SQLException {
		
		
		Connection conn = null;

		try {
			conn = DBManager.getProdInstance().getConnection();
		} catch (SQLException e) {
			System.out.println(Utils.getStackTrace(e));
			
		}
		
		User u = new User();
		u.setName("IPERIANU3");
		u.setPassword("8B6tF2");
		
		Account acc = new Account(conn);
		acc.loginUser(u);
		
		

	}

}
