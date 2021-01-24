package com.deneme.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.deneme.dao.CustomDAO;
import com.deneme.model.Product;
import com.deneme.model.User;
import com.deneme.service.CustomService;

@Service
@Transactional
public class CustomServiceImpl implements CustomService{

	@Autowired
	private CustomDAO customDAO;
	
	@Override
	public List<User> customQuery1(long productId) {
		List<User> users = new ArrayList<User>();
		List<Object[]> list = customDAO.customQuery1(productId);
		for (Object[] user : list) {
			User user1 = new User();

			String stringId = String.valueOf(user[0]);
			Long userIdLong = Long.parseLong(stringId);
			

			long uId = userIdLong;
			String name = (String) user[2];

			user1.setUserId(uId);
			user1.setUsername(name);

			users.add(user1);
			
			if(users.isEmpty()) {
				return null;
			}
		}
		
		return users;
	
	}

}
