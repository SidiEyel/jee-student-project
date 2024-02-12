package com.rimbestprice.rimbestprice;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateTest {

    public static void main(String[] args) {
        // Create a Hibernate configuration instance
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");

        // Build a Hibernate SessionFactory
        try (SessionFactory sessionFactory = configuration.buildSessionFactory()) {
            // Open a session
            try (Session session = sessionFactory.openSession()) {
                System.out.println("Connected to the database successfully!");

                // Perform any test queries or operations here if needed

            } catch (Exception e) {
                System.err.println("Error opening Hibernate session: " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Error building Hibernate SessionFactory: " + e.getMessage());
        }
    }
}
