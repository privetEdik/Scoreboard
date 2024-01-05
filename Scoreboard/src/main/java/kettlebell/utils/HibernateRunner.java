package kettlebell.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateRunner {
	private static Session session;

	private HibernateRunner() {

	}

	public static Session getInstanceSession() {
		if (session == null) {
			Configuration configuration = new Configuration();
			configuration.configure();
			
			SessionFactory sessionFactory = configuration.buildSessionFactory();
			session = sessionFactory.openSession();
		}
		return session;
	}
}
