package com.deneme.service;

import java.util.List;

import com.deneme.model.Order;
import com.deneme.model.User;

public interface CustomRepoService {
	
	List<User> customQuery1(long productId);

}
