package com.deneme.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.deneme.dao.CustomDAO;

@Repository
public class CustomDAOImpl implements CustomDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	
	Transaction transaction = null;
	
	@Override
	public List<Object[]> customQuery1(long productId) {
		List<Object[]> userList = null;
        Session session = sessionFactory.openSession();
        try {
            transaction = session.beginTransaction();
            String hql = "SELECT u.* FROM user u "
            		+ "INNER JOIN orders o ON o.user_userId = u.userId "
            		+ "INNER JOIN productsincart pc ON o.shoppingCart_cartId = pc.shoppingCart_cartId "
            		+ "WHERE pc.productId = :pId";
            userList = session.createSQLQuery(hql).setParameter("pId",productId).list();
           
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
        return userList;
	}

}
