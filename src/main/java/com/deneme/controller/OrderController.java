package com.deneme.controller;

import com.deneme.config.Tokens;
import com.deneme.model.Order;
import com.deneme.model.Product;
import com.deneme.service.OrderService;
import com.deneme.service.ProductInCartService;
import com.deneme.service.ProductService;
import com.deneme.service.ShoppingService;

import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/orderApi")
@Controller
public class OrderController {

    private static final Logger logger = Logger.getLogger(OrderController.class);

    @Autowired
    private Tokens tokens;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ShoppingService shoppingService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductInCartService productInCartService;

    //Sipariş ver
    @RequestMapping(value = "/getOrder", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    List<Product> getOrder(@CookieValue(value = "token") Long token, @RequestBody Order order) {

        long userId = (long) tokens.getTokensMap().get(token);

        long cartId = shoppingService.getCartIdByUserId(userId);

        order.setStatus("Sipariş Alındı.");

        long orderId = orderService.getOrder(order);
        
        orderService.updateOrderAfter(userId,cartId);
        
        
        List<Product> listProduct = orderService.getProductsInOrder(userId,cartId);
        
        for (Product product : listProduct) {
			productService.setStockValue(product.getProductId(),cartId);
		}

        logger.info("Order getting. userId : " + userId);
        
        return listProduct;
            }

    @RequestMapping(value = "/changeOrderAddress/{address}", method = RequestMethod.GET)
    public @ResponseBody
    void changeOrderAddress(@CookieValue(value = "token") Long token ,@PathVariable(value = "address") String address) {

        long userId = (long) tokens.getTokensMap().get(token);

        long cartId = shoppingService.getCartIdByUserId(userId);


        orderService.changeOrderAddress(userId,cartId,address);



        logger.info("Address changing. userId : " + userId +  " address : " + address);
    }

    @RequestMapping(value = "/cancelledOrder", method = RequestMethod.GET)
    public @ResponseBody
    void cancelledOrder(@CookieValue(value = "token") Long token) {

        long userId = (long) tokens.getTokensMap().get(token);

        long cartId = shoppingService.getCartIdByUserId(userId);

        List<Product> listProduct = orderService.getProductsInOrder(userId,cartId);
        
        for (Product product : listProduct) {
			productService.setStockValueCancelled(product.getProductId(),cartId);
		}
        
        orderService.cancelledOrder(userId);
        
        productInCartService.clearProductInCart(cartId);
        
        

        logger.info("Order cancelling. userId : " + userId);
    }

    @RequestMapping(value = "/orderTracking", method = RequestMethod.GET)
    public @ResponseBody
    String orderTracking(@CookieValue(value = "token") Long token) {

        long userId = (long) tokens.getTokensMap().get(token);


        String x = orderService.orderTracking(userId);

        logger.info("Order tracking. userId : " + userId + " order status : " + x);

        return x;


    }









}
