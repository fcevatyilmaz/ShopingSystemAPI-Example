package com.deneme.controller;

import com.deneme.config.Tokens;
import com.deneme.model.Order;
import com.deneme.model.User;
import com.deneme.service.UserService;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/userApi")
@Controller
public class UserController {

    private static final Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    private Tokens tokens;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "newUser", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    long newUser(@RequestBody User user) {
        long id = userService.createUser(user);
        logger.info("User adding. id : " + id);
        return id;
    }

    @RequestMapping(value = "editUser/{userId}", method = RequestMethod.PUT, consumes = "application/json")
    public @ResponseBody User updateUser(@PathVariable(value = "userId") long id, @RequestBody User user) {
        User userTemp = new User();
        userTemp.setUserId(user.getUserId());
        userTemp.setUsername(user.getUsername());
        userTemp.setPassword(user.getPassword());
        logger.info("User updating. : " + userTemp);
        return userService.updateUser(userTemp);

    }
    @RequestMapping(value = "removeUser/{userId}", method = RequestMethod.DELETE)
    @ResponseBody
    public void deleteUser(@PathVariable(value = "userId") long id) {
        logger.info("User deleting. Id : " + id);
        userService.deleteUser(id);
    }

    @RequestMapping(value = { "getAllUser",
            "/listAllUser" }, method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<User> listAllUser() {
        System.out.println(SecurityController.loginKullaniciID);



        logger.info("All users.");
        List<User> userList = userService.listAllUser();
        return userList;
    }


    @RequestMapping(value = "/getLoginUserOrders", method = RequestMethod.GET,produces = "application/json")
    @ResponseBody
    public List<Order> getLoginOrders(@CookieValue(value = "token") Long token) {

        long userId = (long) tokens.getTokensMap().get(token);
        return userService.getLoginOrders(userId);
    }





}
