package com.deneme.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class ProductsInCart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long productsInCartId;


    @ManyToOne
    @JsonIgnore
    private ShoppingCart shoppingCart;

    
    
    

	private long productId;
    
    private long orderAmount;
    

    public long getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(long orderAmount) {
		this.orderAmount = orderAmount;
	}

	public long getProductsInCartId() {
        return productsInCartId;
    }

    public void setProductsInCartId(long productsInCartId) {
        this.productsInCartId = productsInCartId;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

}
