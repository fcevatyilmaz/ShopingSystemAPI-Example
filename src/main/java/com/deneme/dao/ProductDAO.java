package com.deneme.dao;

import com.deneme.model.Category;
import com.deneme.model.Order;
import com.deneme.model.Product;

import java.util.List;

public interface ProductDAO {
     Product createProduct(Product product, Category category);

     void deleteProduct(long productId);

     Product updateProduct(Product product);

     List<Product> listAllProduct();

    void updateProductCartId(long cartId);

    Product getProductById(long productId);

	long getProductStock(long productId);

	void setStockValue(long productId,long cartId);

	void setStockValueCancelled(Long productId, long cartId);


}
