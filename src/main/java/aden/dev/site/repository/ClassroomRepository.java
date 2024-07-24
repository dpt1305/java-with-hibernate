package aden.dev.site.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import aden.dev.site.entity.Classroom;
import aden.dev.site.exception.DatabaseOperationException;
import aden.dev.site.exception.NotFoundException;
import aden.dev.site.util.HibernateUtils;

public class ClassroomRepository {
    public String create(String classrooomName) {
        Classroom classroom = new Classroom(classrooomName);
        Transaction transaction = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.persist(classroom);
            session.flush();
            transaction.commit();

            return classroom.getId();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DatabaseOperationException("Database Operation Exception", e);
        }
    }

    public Classroom getById(String id) throws NotFoundException {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        Classroom classroom = session.get(Classroom.class, id);
        session.flush();
        transaction.commit();
        return classroom;
    }

    public List<Classroom> getAll() {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List<Classroom> classroom = session.createQuery("from Classroom", Classroom.class)
                .getResultList();

        transaction.commit();
        return classroom;
    }

    public List<Classroom> getWithConditions(String condition, int page, int size) throws DatabaseOperationException {
        Session session = HibernateUtils.getSessionFactory().openSession();

        List<Classroom> classrooms = session.createQuery("from Classroom where " + condition, Classroom.class)
                .setFirstResult((page - 1) * size)
                .setMaxResults(size)
                .getResultList();

        session.close();
        return classrooms;
    }

    public void delete(String id) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();

        Classroom classroom = session.get(Classroom.class, id);

        if (classroom != null) {
            session.remove(classroom);
        }

        // close
        session.getTransaction().commit();
        session.close();
    }
}
