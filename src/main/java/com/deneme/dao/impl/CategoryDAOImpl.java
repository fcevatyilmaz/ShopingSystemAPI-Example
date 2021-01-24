package com.deneme.dao.impl;

import com.deneme.dao.CategoryDAO;
import com.deneme.model.Category;
import com.deneme.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryDAOImpl implements CategoryDAO {

    Transaction transaction = null;

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public long createCategory(Category category) {
        Session session = sessionFactory.getCurrentSession();
        return (long) session.save(category);
    }

    @Override
    public void deleteCategory(long categoryId) {
        Session session = sessionFactory.openSession();
        try {
            transaction = session.beginTransaction();
            String hql = "delete Category where categoryId = :id";
            Query q = session.createQuery(hql).setParameter("id", categoryId);
            q.executeUpdate();
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
    }

    @Override
    public Category updateCategory(Category category) {
        Session session = sessionFactory.openSession();
        try {
            transaction = session.beginTransaction();
            session.saveOrUpdate(category);
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
        return category;
    }

    public Category findByIdCategory(long categoryId) {

            Session session = sessionFactory.openSession();
            session.beginTransaction();
            Category c = session.get(Category.class, categoryId);
            session.getTransaction().commit();
            return c;



    }





}
