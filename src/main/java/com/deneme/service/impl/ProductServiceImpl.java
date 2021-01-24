package com.deneme.service.impl;

import com.deneme.dao.CategoryDAO;
import com.deneme.dao.ProductDAO;
import com.deneme.model.Category;
import com.deneme.model.Order;
import com.deneme.model.Product;
import com.deneme.model.User;
import com.deneme.service.CategoryService;
import com.deneme.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDAO productDAO;

    @Autowired
    private CategoryDAO categoryDAO;


    @Override
    public Product createProduct(Product product,long categoryId) {
        Category category = categoryDAO.findByIdCategory(categoryId);



        return productDAO.createProduct(product,category);
    }

    @Override
    public void deleteProduct(long productId) {
        productDAO.deleteProduct(productId);
    }

    @Override
    public Product updateProduct(Product product) {
        return productDAO.updateProduct(product);
    }

    @Override
    public List<Product> listAllProduct() {
        return productDAO.listAllProduct();
    }

    @Override
    public void addShoppingChart(Product product) {
        productDAO.updateProduct(product);
    }

    @Override
    public void updateProductCartId(long cartId) {
        productDAO.updateProductCartId(cartId);
    }

    @Override
    public Product getProductById(long productId) {
        return productDAO.getProductById(productId);
    }

	@Override
	public long getProductStock(long productId) {
		return productDAO.getProductStock(productId);
	}

	@Override
	public void setStockValue(long productId,long cartId) {
		productDAO.setStockValue(productId,cartId);
		
	}

	@Override
	public void setStockValueCancelled(Long productId, long cartId) {
		productDAO.setStockValueCancelled(productId,cartId);
		
	}
}