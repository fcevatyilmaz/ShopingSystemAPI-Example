package com.deneme.dao;

import com.deneme.model.Product;
import com.deneme.model.ShoppingCart;
import com.deneme.model.User;

import java.util.List;

public interface ShoppingDAO {
    long newChart(ShoppingCart cart);

    String addShoppingChart(ShoppingCart cartId,long productId, long orderAmount);

    long getCartIdByUserId(long userId);
    
    ShoppingCart getCartByUserId(long userId);
    
    

}
