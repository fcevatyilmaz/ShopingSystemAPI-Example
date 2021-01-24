package com.deneme.dao.impl;

import com.deneme.dao.ShoppingDAO;
import com.deneme.dao.UserDAO;
import com.deneme.model.Product;
import com.deneme.model.ProductsInCart;
import com.deneme.model.ShoppingCart;
import com.deneme.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ShoppingDAOImpl implements ShoppingDAO {
    Transaction transaction = null;

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public long newChart(ShoppingCart cart) {
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        long cart1 = (long) session.save(cart);
        session.getTransaction().commit();
        return cart1;
    }

    public String addShoppingChart(ShoppingCart cart,long productId, long orderAmount){
    	String message=null;
        Session session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        try {
        	ProductsInCart pc = new ProductsInCart();
        	pc.setShoppingCart(cart);
        	pc.setOrderAmount(orderAmount);
        	pc.setProductId(productId);
        	
        	session.save(pc);
        	message = "Başarılı şeklide sepete eklendi";

            transaction.commit();

        } catch (Exception e) {
        	message = "Sepete eklenemedi";
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
    	return message;


    }

    public long getCartIdByUserId(long userId) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        long id = (long) session.createQuery(
                "SELECT sc.cartId FROM ShoppingCart sc where user_userId = :id ").setParameter("id",userId).getSingleResult();
        session.getTransaction().commit();
        return id;

    }
    
    public ShoppingCart getCartByUserId(long cartId) {
    	Session session = sessionFactory.openSession();
		session.beginTransaction();
		ShoppingCart sc = session.get(ShoppingCart.class, cartId);
		session.getTransaction().commit();
		return sc;

    }
    
    
    
    
    
    

}
