package com.deneme.controller;

import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.deneme.model.User;
import com.deneme.service.CustomRepoService;

@RequestMapping("/customJpaApi")
@Controller
public class CustomJpaController {
	private static final Logger logger = Logger.getLogger(CustomJpaController.class);

    @Autowired
    private CustomRepoService customRepoService;
	
    @RequestMapping(value = "/customRepoQuery1/{productId}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<User> customQuery1(@PathVariable("productId") long productId) {
    	
    logger.info("productId : " + productId);
        
    return customRepoService.customQuery1(productId);
        
    }

}
