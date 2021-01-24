package com.deneme.controller;

import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.deneme.config.Tokens;
import com.deneme.model.User;
import com.deneme.service.CustomService;
import com.deneme.service.ShoppingService;

@RequestMapping("/customApi")
@Controller
public class CustomController {
	private static final Logger logger = Logger.getLogger(CustomController.class);
	
	@Autowired
    private Tokens tokens;

    @Autowired
    private ShoppingService shoppingService;

    @Autowired
    private CustomService customService;
	
    @RequestMapping(value = "/customQuery1/{productId}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<User> customQuery1(@PathVariable("productId") long productId) {
    	logger.info("productId : " + productId);
        
        return customService.customQuery1(productId);
        
        }

}
