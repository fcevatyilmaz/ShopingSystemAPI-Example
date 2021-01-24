package com.deneme.service.impl;

import com.deneme.dao.OrderDAO;
import com.deneme.dao.ProductDAO;
import com.deneme.model.Order;
import com.deneme.model.Product;
import com.deneme.service.OrderService;
import com.deneme.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDAO orderDAO;


    @Override
    public long createOrder(Order order) {
        return orderDAO.createOrder(order);
    }

    @Override
    public void deleteOrder(long orderId) {
        orderDAO.deleteOrder(orderId);
    }

    @Override
    public Order updateOrder(Order order) {
        return orderDAO.updateOrder(order);
    }

    @Override
    public List<Order> listAllOrder() {
        return orderDAO.listAllOrder();
    }

    @Override
    public long getOrder(Order order) {
       return orderDAO.getOrder(order);
    }

    @Override
    public void updateOrderAfter(long userId, long cartId) {
        orderDAO.updateOrderAfter(userId,cartId);
    }

    @Override
    public void changeOrderAddress(long userId, long cartId, String address) {
        orderDAO.changeOrderAddress(userId,cartId,address);
    }

    @Override
    public void cancelledOrder(long userId) {
        orderDAO.cancelledOrder(userId);
    }

    @Override
    public String orderTracking(long userId) {
        return orderDAO.orderTracking(userId);
    }

	@Override
	public List<Product> getProductsInOrder(long userId, long cartId) {
		List<Product> products = new ArrayList<Product>();
		List<Object[]> list = orderDAO.getProductsInOrder(userId,cartId);
		for (Object[] product : list) {
			Product product2 = new Product();

			String stringId = String.valueOf(product[0]);
			Long longId = Long.parseLong(stringId);
			
			String stringStock = String.valueOf(product[2]);
			Integer longStock = Integer.parseInt(stringStock);

			long productId = longId;
			String name = (String) product[1];
			int stock = longStock;

			product2.setProductId(productId);
			product2.setName(name);
			product2.setStock(stock);

			products.add(product2);
		}
		
		return products;
	}

}