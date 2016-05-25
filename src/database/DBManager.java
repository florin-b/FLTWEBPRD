package database;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBManager {

	private static DataSource dataSource;

	private DBManager() {

	}

	public static DataSource getProdInstance() {
		if (dataSource == null)
			dataSource =  getProdDataSource();

		return dataSource;
	}

	private static DataSource getProdDataSource() {
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
