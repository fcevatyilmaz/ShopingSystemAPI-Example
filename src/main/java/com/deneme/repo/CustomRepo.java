package com.deneme.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.deneme.model.User;

@Repository
public interface CustomRepo extends JpaRepository<User,Long>{
	
	@Query(value = "SELECT u.* FROM user u "
            		+ "INNER JOIN orders o ON o.user_userId = u.userId "
            		+ "INNER JOIN productsincart pc ON o.shoppingCart_cartId = pc.shoppingCart_cartId "
            		+ "WHERE pc.productId = :pId",nativeQuery = true)
	List<Object[]> customQuery1(@Param("pId") long productId);

}
