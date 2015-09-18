package database;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBManager {

	public DataSource getProdDataSource() {
		InitialContext initContext;
		DataSource ds = null;
		try {
			initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			ds = (DataSource) envContext.lookup("jdbc/myoracle_prod");
		} catch (NamingException e) {
			e.printStackTrace();
		}

		return ds;
	}

}
