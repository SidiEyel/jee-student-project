package com.rimbestprice.rimbestprice.dao;

import com.rimbestprice.rimbestprice.models.CompanyAerienne;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class CompanyAerienneDao {

    private static final SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

    // Utility method to handle session and transaction
    private void executeInsideTransaction(Consumer<Session> action) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            action.accept(session);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    // Utility method for retrieving data
    private <T> T executeQuery(Function<Session, T> function) {
        try (Session session = sessionFactory.openSession()) {
            return function.apply(session);
        }
    }

    // Method to add a CompanyAerienne
    public void addCompanyAerienne(CompanyAerienne companyAerienne) {
        executeInsideTransaction(session -> session.save(companyAerienne));
    }

    // Method to update a CompanyAerienne
    public void updateCompanyAerienne(CompanyAerienne companyAerienne) {
        executeInsideTransaction(session -> session.update(companyAerienne));
    }

    // Method to delete a CompanyAerienne
    public void deleteCompanyAerienne(Long companyId) {
        executeInsideTransaction(session -> {
            CompanyAerienne companyAerienne = session.get(CompanyAerienne.class, companyId);
            if (companyAerienne != null) {
                session.delete(companyAerienne);
            }
        });
    }

    // Method to get all CompanyAeriennes
    public List<CompanyAerienne> getAllCompanyAeriennes() {
        return executeQuery(session -> session.createQuery("from CompanyAerienne", CompanyAerienne.class).list());
    }

    // Method to get CompantAeriennes by Id
    public CompanyAerienne getCompanyAerienneById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(CompanyAerienne.class, id);
        }
    }

}
