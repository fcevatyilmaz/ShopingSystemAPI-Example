package com.deneme.service;



import com.deneme.model.Order;
import com.deneme.model.User;

import java.util.List;


public interface UserService {

    long createUser(User user);

    void deleteUser(long userId);

    User updateUser(User user);

    boolean findLoginUser(String username,String password);

    List<User> listAllUser();

    List<Order> getLoginOrders(long userId);
}