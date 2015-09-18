package database;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import beans.User;

public class Account {

	private Connection conn;
	private String errMessage;

	public Account(Connection conn) {
		this.conn = conn;
	}

	public boolean loginUser(User user) throws SQLException {

		String storedProcedure = "{ call web_pkg.wlogin(?,?,?,?,?,?,?,?,?,?) }";
		CallableStatement callableStatement = null;

		try {
			callableStatement = conn.prepareCall(storedProcedure);
			callableStatement.setString(1, user.getName());
			callableStatement.setString(2, user.getPassword());

			callableStatement.registerOutParameter(3, java.sql.Types.NUMERIC);
			callableStatement.registerOutParameter(4, java.sql.Types.VARCHAR);
			callableStatement.registerOutParameter(5, java.sql.Types.VARCHAR);
			callableStatement.registerOutParameter(6, java.sql.Types.NUMERIC);
			callableStatement.registerOutParameter(7, java.sql.Types.VARCHAR);
			callableStatement.registerOutParameter(8, java.sql.Types.NUMERIC);
			callableStatement.registerOutParameter(9, java.sql.Types.VARCHAR);
			callableStatement.registerOutParameter(10, java.sql.Types.NUMERIC);

			callableStatement.execute();

			if (callableStatement.getInt(3) == 3) {
				user.setFiliala(callableStatement.getString(5));
				user.setUserName(callableStatement.getString(9));
				return true;
			} else {
				setErrMessage(callableStatement.getInt(3));
				return false;
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;

		} finally {
			if (callableStatement != null)
				callableStatement.close();
		}

	}

	private void setErrMessage(int msgId) {

		switch (msgId) {
		case 0:
			errMessage = "Cont inexistent";
			break;

		case 1:
			errMessage = "Cont blocat 60 minute";
			break;

		case 2:
			errMessage = "Parola incorecta";
			break;

		case 4:
			errMessage = "Cont inactiv";
			break;

		}

	}

	public boolean login(String nume, String password) throws SQLException {

		String sql = "select count(*) as count from agenti where nume=? ";

		PreparedStatement stmt = conn.prepareStatement(sql);

		stmt.setString(1, nume);

		ResultSet rs = stmt.executeQuery();

		int count = 0;

		if (rs.next()) {
			count = rs.getInt("count");
		}

		rs.close();

		if (count == 0) {
			return false;
		} else {
			return true;
		}
	}

	public String getErrMessage() {
		return errMessage;
	}

	public void setErrMessage(String errMessage) {
		this.errMessage = errMessage;
	}

}
