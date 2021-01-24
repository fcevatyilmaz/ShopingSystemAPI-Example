package com.deneme.dao.impl;

import com.deneme.dao.ProductDAO;
import com.deneme.model.Category;
import com.deneme.model.Order;
import com.deneme.model.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDAOImpl implements ProductDAO {

    Transaction transaction = null;

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Product createProduct(Product product, Category category) { //Cascade kullanımı
        Session session = sessionFactory.openSession();
        try {
            transaction = session.beginTransaction();
            Product product1 = new Product();
            product1.setName(product.getName());
            product1.setStock(product.getStock());
            product1.setCategory(category);
            session.save(product1);
            transaction.commit();
            return product1;

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }

    return null;


    }

    @Override
    public void deleteProduct(long productId) {
        Session session = sessionFactory.openSession();
        try {
            transaction = session.beginTransaction();
            String hql = "delete Product where productId = :id";
            Query q = session.createQuery(hql).setParameter("id", productId);
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
    public Product updateProduct(Product product) {
        Session session = sessionFactory.openSession();
        try {
            transaction = session.beginTransaction();
            session.saveOrUpdate(product);
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
        return product;
    }

    public List<Product> listAllProduct() {
        List<Product> list = null;
        Session session = sessionFactory.openSession();
        try {
            transaction = session.beginTransaction();
            String hql = "FROM Product";
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
    public void updateProductCartId(long cartId) {
        Session session = sessionFactory.openSession();
        try {
            transaction = session.beginTransaction();
            String hql = "UPDATE Product SET shoppingCart_cartId=:id WHERE shoppingCart_cartId = :id2";
            Query q = session.createQuery(hql).setParameter("id", 0).setParameter("id2",cartId);
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
    public Product getProductById(long productId) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Product p = session.get(Product.class, productId);
        session.getTransaction().commit();
        return p;
    }

	@Override
	public long getProductStock(long productId) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Product product = session.get(Product.class, productId);
		session.getTransaction().commit();
		return product.getStock();
	}

	@Override
	public void setStockValue(long productId,long cartId) {
		 Session session = sessionFactory.openSession();
	        try {
	            transaction = session.beginTransaction();
	            
	            String hql = "UPDATE Product p SET p.stock=p.stock - "
	            		+ "(SELECT pc.orderAmount FROM productsincart pc "
	            		+ "WHERE pc.productId = :pId AND pc.shoppingCart_cartId = :cId) "
	            		+ "WHERE p.productId = :pId"; 

	           Query q = session.createSQLQuery(hql).setParameter("cId", cartId)
	            								  .setParameter("pId",productId);
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
	public void setStockValueCancelled(Long productId, long cartId) {
		Session session = sessionFactory.openSession();
        try {
            transaction = session.beginTransaction();
            
            String hql = "UPDATE Product p SET p.stock=p.stock + "
            		+ "(SELECT pc.orderAmount FROM productsincart pc "
            		+ "WHERE pc.productId = :pId AND pc.shoppingCart_cartId = :cId) "
            		+ "WHERE p.productId = :pId"; 

           Query q = session.createSQLQuery(hql).setParameter("cId", cartId)
            								  .setParameter("pId",productId);
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
