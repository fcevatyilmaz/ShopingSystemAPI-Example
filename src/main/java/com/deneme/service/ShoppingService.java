package com.deneme.service;

import com.deneme.model.Product;
import com.deneme.model.ShoppingCart;
import com.deneme.model.User;

import java.util.List;

;


public interface ShoppingService {

    long newChart(ShoppingCart cart);

    String addShoppingChart(ShoppingCart sc,long productId, long orderAmount);

    long getCartIdByUserId(long userId);
    
    ShoppingCart getCartByUserId(long userId);






}