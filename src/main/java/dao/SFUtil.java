package dao;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SFUtil {
	
	private static Configuration config = new Configuration();
	private static SessionFactory factory;
	
	private SFUtil() {
		// TODO Auto-generated constructor stub
	}
	
	public static SessionFactory getSessionFactory() {
		return factory;
	}
	static {
		if(config!= null) {
			config.configure("hibernate.cfg.xml");
		}
		try {
		 factory = config.buildSessionFactory();
		}
		catch(HibernateException he) {
			he.printStackTrace();
		}
		
	}
}

