package com.klef.jfsd.exam;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class ClientDemo {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            String hql = "UPDATE Department SET name = ?1, location = ?2 WHERE departmentId = ?3";
            int updatedRows = session.createQuery(hql)
                                     .setParameter(1, "Computer Science")
                                     .setParameter(2, "Main Campus")
                                     .setParameter(3, 1)
                                     .executeUpdate();

            transaction.commit();

            System.out.println("Rows affected: " + updatedRows);
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        } finally {
            session.close();
            HibernateUtil.shutdown();
        }
    }
}
