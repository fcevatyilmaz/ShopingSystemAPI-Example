package com.deneme.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.deneme.dao.ProductInCartDAO;

@Repository
public class ProductInCartDAOImpl implements ProductInCartDAO{

	Transaction transaction = null;

    @Autowired
    private SessionFactory sessionFactory;
	
	@Override
	public void clearProductInCart(long shoppingCartId) {
		Session session = sessionFactory.openSession();
        try {
            transaction = session.beginTransaction();
            String hql = "DELETE FROM productsincart pc WHERE pc.shoppingCart_cartId = :scId";
            Query q = session.createSQLQuery(hql).setParameter("scId",shoppingCartId);
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

}
