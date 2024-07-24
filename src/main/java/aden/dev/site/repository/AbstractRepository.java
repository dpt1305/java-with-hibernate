package aden.dev.site.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import aden.dev.site.entity.IEntity;
import aden.dev.site.exception.DatabaseOperationException;
import aden.dev.site.exception.NotFoundException;
import aden.dev.site.util.HibernateUtils;

public abstract class AbstractRepository {

    public String create(IEntity entity) {
        Transaction transaction = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.persist(entity);
            session.flush();
            transaction.commit();

            return (String) entity.getId();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DatabaseOperationException("Database Operation Exception", e);
        }
    }

    public IEntity getById(Class<?> clazz, String id) throws NotFoundException {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        IEntity classroom = (IEntity) session.get(clazz, id);
        session.flush();
        transaction.commit();
        return classroom;
    }

    public List<?> getWithConditions(String rawQuery, Class<?> clazz, int page, int size)
            throws DatabaseOperationException {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();

        @SuppressWarnings("unchecked")
        List<?> list = (List<IEntity>) session.createQuery(rawQuery, clazz)
                .setFirstResult((page - 1) * size)
                .setMaxResults(size)
                .getResultList();

        session.getTransaction().commit();
        session.close();
        return list;
    }

    public <T> void delete(Class<?> clazz, String id) throws NotFoundException, DatabaseOperationException {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();

        @SuppressWarnings("unchecked")
        T entity = (T) session.get(clazz, id);

        if (entity != null) {
            session.remove(entity);
            session.getTransaction().commit();
        }

        // close
        session.close();
    }
}
