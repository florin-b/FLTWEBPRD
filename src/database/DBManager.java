package database;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBManager {

	private static DataSource dataSourcePrd;
	private static DataSource dataSourceTest;

	private DBManager() {

	}

	public static DataSource getProdInstance() {
		if (dataSourcePrd == null)
			dataSourcePrd = getProdDataSource();

		return dataSourcePrd;
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

	public static DataSource getTestInstance() {
		if (dataSourcePrd == null)
			dataSourcePrd = getTestDataSource();

		return dataSourceTest;
	}

	private static DataSource getTestDataSource() {
		InitialContext initContext;
		DataSource ds = null;
		try {
			initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			ds = (DataSource) envContext.lookup("jdbc/myoracle_tes");
		} catch (NamingException e) {
			e.printStackTrace();
		}

		return ds;
	}

}
