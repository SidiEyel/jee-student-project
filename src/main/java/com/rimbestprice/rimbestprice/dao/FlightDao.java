package com.rimbestprice.rimbestprice.dao;

import com.rimbestprice.rimbestprice.models.Flight;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import jakarta.persistence.Query;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class FlightDao {

    private static final SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

    // Utility method to handle session and transaction for write operations
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

    public void addFlight(Flight flight) {
        executeInsideTransaction(session -> session.save(flight));
    }

    public List<Flight> getAllFlights() {
        return executeQuery(session -> {
            String hql = "FROM Flight";
            Query query = session.createQuery(hql);
            return query.getResultList();
        });
    }

    public List<Flight> getByArgs(String departureCity) {

        return executeQuery(session -> {
            String hql = "FROM Flight f where  f.departureCity = :dep";
            Query query = session.createQuery(hql)
                    .setParameter("dep", departureCity);

            return query.getResultList();
        });

    }

    public Flight getFlightByFlightNumber(String flightNumber) {
        return executeQuery(session -> session.get(Flight.class, flightNumber));
    }

    public void updateFlight(Flight flight) {
        executeInsideTransaction(session -> session.update(flight));
    }

    public void deleteFlight(String flightNumber) {
        executeInsideTransaction(session -> {
            Flight flight = session.get(Flight.class, flightNumber);
            if (flight != null) {
                session.delete(flight);
            }
        });
    }

}
