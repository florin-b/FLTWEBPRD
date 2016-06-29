package database;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;



/**
 * Application Lifecycle Listener implementation class SystemListener
 *
 */
@WebListener
public class DBInitializer implements ServletContextListener {

	/**
	 * Default constructor.
	 */
	public DBInitializer() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent arg0) {
		
	}

	/**
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent arg0) {
		//DBManager.getProdInstance();
	}

}
