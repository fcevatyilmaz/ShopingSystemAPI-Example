package com.deneme.service;

import com.deneme.model.Category;
import com.deneme.model.Order;
import com.deneme.model.Product;

import java.util.List;


public interface OrderService {

    long createOrder(Order order);

    void deleteOrder(long orderId);

    Order updateOrder(Order order);

    List<Order> listAllOrder();

    long getOrder(Order order);

    void updateOrderAfter(long userId,long cartId);

    void changeOrderAddress(long userId,long cartId,String address);

    void cancelledOrder(long userId);
    

    String orderTracking(long userId);

    List<Product> getProductsInOrder(long userId, long cartId);
}