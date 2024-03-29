package com.rimbestprice.rimbestprice.dao;

import com.rimbestprice.rimbestprice.models.User;
import jakarta.persistence.Query;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class UserDao {

    private static final SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

    public void addUser(User user) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session != null && session.getTransaction() != null) {
                session.getTransaction().rollback();  // Rollback in case of an exception
            }
            // You might want to log this exception or rethrow it as a custom exception depending on your design
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();  // Ensure session is closed
            }
        }
    }


    public boolean userExists(String username, String email) {
        Session session = sessionFactory.openSession();
        try {
            String hql = "FROM User WHERE username = :username OR email = :email";
            Query query = session.createQuery(hql);
            query.setParameter("username", username);
            query.setParameter("email", email);
            boolean exists = !query.getResultList().isEmpty();
            return exists;
        } finally {
            session.close();
        }
    }

   public User getUserByUsername(String username) {
    try (Session session = sessionFactory.openSession()) {
        String hql = "FROM User WHERE username = :username";
        Query query = session.createQuery(hql);
        query.setParameter("username", username);
        Object result = query.getSingleResult();
        if (result != null) {
            User user = (User) result;
            Hibernate.initialize(user.getReservations());
            return user;
        }
        return null;
    } catch (Exception e) {
        return null;
    }

}}
