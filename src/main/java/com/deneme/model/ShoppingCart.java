package com.deneme.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long cartId;

    @OneToOne
    @JsonIgnore
    private User user;


    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "shoppingCart")
    private Set<ProductsInCart> ProductsInCart = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "shoppingCart")
    private Order order;








    public long getCartId() {
        return cartId;
    }

    public void setCartId(long cartId) {
        this.cartId = cartId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }



    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

	private Set<ProductsInCart> getProductsInCart() {
		return ProductsInCart;
	}

	private void setProductsInCart(Set<ProductsInCart> productsInCart) {
		ProductsInCart = productsInCart;
	}
}
