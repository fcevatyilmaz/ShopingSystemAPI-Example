package com.deneme.dao;

import com.deneme.model.Order;
import com.deneme.model.Product;

import java.util.List;

public interface OrderDAO {
    long createOrder(Order orderId);

    void deleteOrder(long orderId);

    Order updateOrder(Order orderId);

    List<Order> listAllOrder();

    long getOrder(Order order);

    void updateOrderAfter(long userId, long cartId);

    void changeOrderAddress(long userId, long cartId, String address);

    void cancelledOrder(long userId);

    String orderTracking(long userId);

	List<Object[]> getProductsInOrder(long userId, long cartId);
	
	
}
