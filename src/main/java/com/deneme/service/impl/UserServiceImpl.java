package com.deneme.service.impl;

import com.deneme.dao.UserDAO;
import com.deneme.model.Order;
import com.deneme.model.User;
import com.deneme.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public long createUser(User user) {
        return userDAO.createUser(user);
    }

    @Override
    public void deleteUser(long userId) {
        userDAO.deleteUser(userId);
    }

    @Override
    public User updateUser(User user) {
        return userDAO.updateUser(user);
    }

    @Override
    public boolean findLoginUser(String username, String password) {
        return userDAO.findLoginUser(username,password);
    }

    @Override
    public List<User> listAllUser() {
        return userDAO.listAllUser();
    }

    @Override
    public List<Order> getLoginOrders(long userId) {
        return userDAO.getLoginOrders(userId);    }
}
