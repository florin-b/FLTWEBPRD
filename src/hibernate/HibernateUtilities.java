package hibernate;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtilities {

	private static Session session;

	private static Session startSessionFactory() {
		try {
			Configuration configuration = new Configuration().configure();

			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
			SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
			session = sessionFactory.openSession();
		} catch (HibernateException ex) {
			System.out.println(ex.toString());
		}

		return session;
	}

	public static Session getSessionFactoryInstance() {
		if (session == null)
			return startSessionFactory();

		return session;
	}

}
