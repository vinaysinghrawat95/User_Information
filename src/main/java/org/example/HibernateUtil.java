package org.example;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;



public class HibernateUtil {
    private static SessionFactory sessionFactory;

    static
    {
        if(sessionFactory == null)
        {
            try
            {
                Configuration cfg = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(User_Info.class);
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
                sessionFactory = cfg.buildSessionFactory(serviceRegistry);

            }catch (Exception e)
            {
                e.getStackTrace();
                throw new RuntimeException("Session factory not built, something went gone wrong",e);
            }
        }
    }

    public static SessionFactory getSessionFactory()
    {
        return sessionFactory;
    }
}
