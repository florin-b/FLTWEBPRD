package database;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import utils.Utils;

public class DBManager {

	private static DataSource dataSourcePrd;
	private static DataSource dataSourceTest;
	
	private static final Logger logger = LogManager.getLogger(DBManager.class);

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
			logger.error(Utils.getStackTrace(e));
		}

		return ds;
	}

	public static DataSource getTestInstance() {
		if (dataSourceTest == null)
			dataSourceTest = getTestDataSource();

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
			logger.error(Utils.getStackTrace(e));
		}

		return ds;
	}

}
