package com.rimbestprice.rimbestprice.dao;

import com.rimbestprice.rimbestprice.models.Ticket;
import com.rimbestprice.rimbestprice.models.User;
import com.rimbestprice.rimbestprice.models.UserReservation;

import jakarta.servlet.http.HttpServletRequest;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Date;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class UserReservationDao {

    private static final SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

    private void executeInsideTransaction(Consumer<Session> action) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            action.accept(session);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    // Utility method for read operations
    private <T> T executeQuery(Function<Session, T> function) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            return function.apply(session);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public void addUserReservation(UserReservation reservation) {
        executeInsideTransaction(session -> session.save(reservation));
    }

    public List<UserReservation> getAllUserReservations() {
        return executeQuery(session -> {
            String hql = "FROM UserReservation";
            org.hibernate.query.Query<UserReservation> query = session.createQuery(hql, UserReservation.class);
            return query.list();
        });
    }

    public UserReservation getUserReservationById(Long reservationId) {
        return executeQuery(session -> session.get(UserReservation.class, reservationId));
    }

    public void updateUserReservation(UserReservation reservation) {
        executeInsideTransaction(session -> session.update(reservation));
    }

    public void deleteUserReservation(Long reservationId) {
        executeInsideTransaction(session -> {
            UserReservation reservation = session.get(UserReservation.class, reservationId);
            if (reservation != null) {
                session.delete(reservation);
            }
        });
    }
  
    public List<UserReservation> getReservationsByUserId(Long userId) {
        return executeQuery(session -> {
            String hql = "FROM UserReservation WHERE user.id = :userId";
            org.hibernate.query.Query<UserReservation> query = session.createQuery(hql, UserReservation.class);
            query.setParameter("userId", userId);
            return query.list();
        });
    }
   
    
}
