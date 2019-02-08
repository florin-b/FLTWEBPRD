package database;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import beans.User;
import beans.UserInfo;
import utils.Utils;

public class Account {

	private Connection conn;
	private String errMessage;

	private static final Logger logger = LogManager.getLogger(Account.class);

	public Account(Connection conn) {
		this.conn = conn;
	}

	public boolean loginUser(User user) throws SQLException {

		String storedProcedure = "{ call web_pkg.wlogin(?,?,?,?,?,?,?,?,?,?) }";
		CallableStatement callableStatement = null;

		try {
			callableStatement = conn.prepareCall(storedProcedure);
			callableStatement.setString(1, user.getName().trim());
			callableStatement.setString(2, user.getPassword().trim());

			callableStatement.registerOutParameter(3, java.sql.Types.NUMERIC);
			callableStatement.registerOutParameter(4, java.sql.Types.VARCHAR);
			callableStatement.registerOutParameter(5, java.sql.Types.VARCHAR);
			callableStatement.registerOutParameter(6, java.sql.Types.NUMERIC);
			callableStatement.registerOutParameter(7, java.sql.Types.VARCHAR);
			callableStatement.registerOutParameter(8, java.sql.Types.NUMERIC);
			callableStatement.registerOutParameter(9, java.sql.Types.VARCHAR);
			callableStatement.registerOutParameter(10, java.sql.Types.NUMERIC);

			callableStatement.execute();

			if (!isAccesPermited(callableStatement.getString(6))) {
				setErrMessage(41);
				return false;
			}

			if (callableStatement.getInt(3) == 3) {
				user.setFiliala(callableStatement.getString(5));
				user.setUserName(callableStatement.getString(9));
				user.setTipAcces(callableStatement.getString(6));

				UserInfo.getInstance().setFiliala(user.getFiliala());
				UserInfo.getInstance().setNume(user.getName());
				UserInfo.getInstance().setTipAcces(callableStatement.getString(6));

				return true;
			} else {
				setErrMessage(callableStatement.getInt(3));
				return false;
			}

		} catch (SQLException e) {
			logger.error(Utils.getStackTrace(e));
			setErrMessage(-1);
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

		case 41:
			errMessage = "Acces interzis";
			break;
		default:
			errMessage = "Eroare conectare bd.";
			break;

		}

	}

	public String getErrMessage() {
		return errMessage;
	}

	public void setErrMessage(String errMessage) {
		this.errMessage = errMessage;
	}

	private boolean isAccesPermited(String codAcces) {
		if (codAcces.equals("11") || codAcces.equals("16") || codAcces.equals("65") || codAcces.equals("20") || codAcces.equals("13") || codAcces.equals("19")
				|| codAcces.equals("140") || codAcces.equals("47") || codAcces.equals("8"))
			return true;
		else
			return false;
	}

}
