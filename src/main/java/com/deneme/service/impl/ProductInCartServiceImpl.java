package com.deneme.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.deneme.dao.OrderDAO;
import com.deneme.dao.ProductInCartDAO;
import com.deneme.service.ProductInCartService;

@Service
@Transactional
public class ProductInCartServiceImpl implements ProductInCartService{

	 @Autowired
	 private ProductInCartDAO productInCartDAO;
	
	
	@Override
	public void clearProductInCart(long shoppingCartId) {
		productInCartDAO.clearProductInCart(shoppingCartId);
		
	}

}
