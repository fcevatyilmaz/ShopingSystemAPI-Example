package com.deneme.controller;

import com.deneme.config.Tokens;
import com.deneme.dao.UserDAO;
import com.deneme.model.Product;
import com.deneme.model.ShoppingCart;
import com.deneme.service.ProductService;
import com.deneme.service.ShoppingService;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@CrossOrigin
@RequestMapping("/shoppingApi")
@Controller
public class ShoppingCartController {
    @Autowired
    private Tokens tokens;

    @Autowired
    private ShoppingService shoppingService;

    @Autowired
    private UserDAO userDAO;
    
    @Autowired
    private ProductService productService;

    private static final Logger logger = Logger.getLogger(ShoppingCartController.class);

    @RequestMapping(value = "/newChart", method = RequestMethod.GET)
    public @ResponseBody long newCart(@CookieValue(value = "token") Long token) {
        ShoppingCart sc = new ShoppingCart();

        long userId = (long) tokens.getTokensMap().get(token);
        sc.setUser(userDAO.getUserById(userId));
        long id = shoppingService.newChart(sc);
        logger.info("Shopping Cart adding. id : " + id);
        return id;
    }

    @RequestMapping(value = "/addShoppingCart/{productId}/{orderAmount}", method = RequestMethod.GET)
    public @ResponseBody String addShoppingCart(@CookieValue(value = "token") Long token,
    		@PathVariable(value = "productId") long productId,
    		@PathVariable(value = "orderAmount") long orderAmount) {
        

        long userId = (long) tokens.getTokensMap().get(token);

        long cartId = shoppingService.getCartIdByUserId(userId);
        
        ShoppingCart sc = shoppingService.getCartByUserId(cartId);
        

        String resultMessage = shoppingService.addShoppingChart(sc,productId,orderAmount);

        logger.info("Shopping Cart adding productId: " + productId);
        return resultMessage;
    }








}
