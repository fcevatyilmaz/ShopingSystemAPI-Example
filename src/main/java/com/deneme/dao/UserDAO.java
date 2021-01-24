package com.deneme.dao;

import com.deneme.model.Order;
import com.deneme.model.User;

import java.util.List;

public interface UserDAO {
    long createUser(User user);

    void deleteUser(long userId);

    User updateUser(User user);

    List<User> listAllUser();

    User getUserById(long id);

    boolean findLoginUser(String username, String password);

    List<User> getLoginUserId(String username, String password);

    List<Order> getLoginOrders(long userId);
}
