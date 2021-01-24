package com.deneme.dao.impl;

import com.deneme.dao.UserDAO;
import com.deneme.model.Order;
import com.deneme.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {
    Transaction transaction = null;

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public long createUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        return (long) session.save(user);
    }

    @Override
    public void deleteUser(long userId) {
        Session session = sessionFactory.openSession();
        try {
            transaction = session.beginTransaction();
            String hql = "delete User where userId = :id";
            Query q = session.createQuery(hql).setParameter("id", userId);
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
    public User updateUser(User user) {
        Session session = sessionFactory.openSession();
        try {
            transaction = session.beginTransaction();
            session.saveOrUpdate(user);
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
        return user;
    }

    @Override
    public List<User> listAllUser() {
        List<User> list = null;
        Session session = sessionFactory.openSession();
        try {
            transaction = session.beginTransaction();
            String hql = "FROM user";
            list = session.createQuery(hql).list();
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
        return list;

    }

    @Override
    public boolean findLoginUser(String username, String password) {
        List<User> list = null;
        Session session = sessionFactory.openSession();
        boolean b = false;
        try {
            transaction = session.beginTransaction();
            String hql = "select u.userId FROM User u WHERE u.username = :username AND u.password = :password";
            Query q = session.createQuery(hql).setParameter("username", username).setParameter("password",password);
            list = q.list();

            if(list.size() != 0){
                b =  true;
            }

            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
        return b;

    }

    @Override
    public List<User> getLoginUserId(String username, String password) {
        List<User> list = null;
        Session session = sessionFactory.openSession();
        boolean b = false;
        try {
            transaction = session.beginTransaction();
            String hql = "select u.userId FROM User u WHERE u.username = :username AND u.password = :password";
            Query q = session.createQuery(hql).setParameter("username", username).setParameter("password",password);
            list = q.list();


            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }

        return list;
    }



    @Override
    public User getUserById(long id) {
        List<User> list = null;
        Session session = sessionFactory.openSession();
        boolean b = false;
        try {
            transaction = session.beginTransaction();
            String hql = "FROM User u WHERE u.userId = :userId";
            Query q = session.createQuery(hql).setParameter("userId", id);
            list = q.list();


            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }

        return list.get(0);
    }

    @Override
    public List<Order> getLoginOrders(long userId) {
        List<Order> orderList = null;
        Session session = sessionFactory.openSession();
        try {
            transaction = session.beginTransaction();
            String hql = "SELECT o.* FROM User u inner join orders o ON u.userId = o.user_userId where o.user_userId = :uId";
            orderList = session.createSQLQuery(hql).setParameter("uId",userId).list();
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
        return orderList;
    }




}
