package com.deneme.dao.impl;

import com.deneme.controller.OrderController;
import com.deneme.dao.OrderDAO;
import com.deneme.model.Order;
import com.deneme.model.Product;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderDAOImpl implements OrderDAO {

	private static final Logger logger = Logger.getLogger(OrderDAOImpl.class);
	
    Transaction transaction = null;

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public long createOrder(Order order) {
        Session session = sessionFactory.getCurrentSession();
        return (long) session.save(order);
    }

    @Override
    public void deleteOrder(long orderId) {
        Session session = sessionFactory.openSession();
        try {
            transaction = session.beginTransaction();
            String hql = "delete Order where orderId = :id";
            Query q = session.createQuery(hql).setParameter("id", orderId);
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
    public Order updateOrder(Order order) {
        Session session = sessionFactory.openSession();
        try {
            transaction = session.beginTransaction();
            session.saveOrUpdate(order);
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
        return order;
    }

    public List<Order> listAllOrder() {
        List<Order> list = null;
        Session session = sessionFactory.openSession();
        try {
            transaction = session.beginTransaction();
            String hql = "FROM Order";
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
    public long getOrder(Order order) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Order orderTemp = new Order();
        orderTemp.setAddress(order.getAddress());
        orderTemp.setPaymentMethod(order.getPaymentMethod());
        orderTemp.setStatus(order.getStatus());

        long orderId = (long)session.save(orderTemp);
        session.getTransaction().commit();
        return orderId;
         
    }

    @Override
    public void updateOrderAfter(long userId, long cartId) {
        Session session = sessionFactory.openSession();
        try {
            transaction = session.beginTransaction();
            String hql = "UPDATE orders SET user_userId =:id , shoppingCart_cartId = :id2 WHERE shoppingCart_cartId IS NULL AND user_userId IS NULL";
            Query q = session.createSQLQuery(hql).setParameter("id", userId).setParameter("id2",cartId);
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
    public void changeOrderAddress(long userId, long cartId, String address) {
        Session session = sessionFactory.openSession();
        try {
            transaction = session.beginTransaction();
            String hql = "UPDATE orders SET address =:adr WHERE shoppingCart_cartId = :cId AND user_userId = :uId";
            Query q = session.createSQLQuery(hql).setParameter("adr",address).setParameter("cId", cartId).setParameter("uId",userId);
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
    public void cancelledOrder(long userId) {
        Session session = sessionFactory.openSession();
        try {
            transaction = session.beginTransaction();
            String hql = "DELETE FROM orders WHERE user_userId = :uId";
            Query q = session.createSQLQuery(hql).setParameter("uId",userId);
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

    public String orderTracking(long userId){
        List<Order> orderStatus = null;
        Session session = sessionFactory.openSession();
        try {
            transaction = session.beginTransaction();
            String hql = "SELECT o.status FROM User u inner join orders o ON u.userId = o.user_userId where o.user_userId = :uId";
            orderStatus = session.createSQLQuery(hql).setParameter("uId",userId).list();
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
        return orderStatus.toString();
    }

	@Override
	public List<Object[]> getProductsInOrder(long userId, long cartId) {
		List<Object[]> productList = null;
        Session session = sessionFactory.openSession();
        try {
            transaction = session.beginTransaction();
            String hql = "SELECT p.* FROM orders o "
            		+ "INNER JOIN productsincart pc ON o.shoppingCart_cartId = pc.shoppingCart_cartId "
            		+ "INNER JOIN product p ON pc.productId = p.productId "
            		+ "WHERE o.user_userId = :uId AND o.shoppingCart_cartId = :scId";
            productList = session.createSQLQuery(hql).setParameter("uId",userId).setParameter("scId", cartId).list();
           
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
        return productList;
        
	}


}
