package com.andrey4623.discount_cards_api.persistence;

import java.io.FileInputStream;
import java.util.Properties;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();
    private static final String JDBC_CONNECTION_PROPERTIES_FILE = "./jdbc.properties";
    private static final String HIBERNATE_CONFIG_FILE = "hibernate.cfg.xml";

    private static SessionFactory buildSessionFactory() {
        SessionFactory sessionFactory;

        try {
            Properties properties = new Properties();
            FileInputStream file = new FileInputStream(JDBC_CONNECTION_PROPERTIES_FILE);
            properties.load(file);
            file.close();

            Configuration configuration = new Configuration();
            configuration.configure(HIBERNATE_CONFIG_FILE);

            configuration.getProperties().setProperty("hibernate.connection.url",
                    properties.getProperty("hibernate.connection.url"));
            configuration.getProperties().setProperty("hibernate.connection.username",
                    properties.getProperty("hibernate.connection.username"));
            configuration.getProperties().setProperty("hibernate.connection.password",
                    properties.getProperty("hibernate.connection.password"));

            sessionFactory = configuration.buildSessionFactory();

        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed: " + ex);
            throw new ExceptionInInitializerError(ex);
        }

        return sessionFactory;
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        // Close caches and connection pools
        getSessionFactory().close();
    }
}
