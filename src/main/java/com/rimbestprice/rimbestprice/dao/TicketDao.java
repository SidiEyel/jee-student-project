package com.rimbestprice.rimbestprice.dao;

import com.rimbestprice.rimbestprice.models.Ticket;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class TicketDao {

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

    public void addTicket(Ticket ticket) {
        executeInsideTransaction(session -> session.save(ticket));
    }

    public List<Ticket> getAllTickets() {
        return executeQuery(session -> {
            String hql = "FROM Ticket";
            org.hibernate.query.Query<Ticket> query = session.createQuery(hql, Ticket.class);
            return query.list();
        });
    }

    public Ticket getTicketByTicketNumber(String ticketNumber) {
        return executeQuery(session -> session.get(Ticket.class, ticketNumber));
    }

    public void updateTicket(Ticket ticket) {
        executeInsideTransaction(session -> session.update(ticket));
    }

    public void deleteTicket(String ticketNumber) {
        executeInsideTransaction(session -> {
            Ticket ticket = session.get(Ticket.class, ticketNumber);
            if (ticket != null) {
                session.delete(ticket);
            }
        });
    }
}
