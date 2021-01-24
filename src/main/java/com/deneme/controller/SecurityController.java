package com.deneme.controller;

import com.deneme.config.Tokens;
import com.deneme.dao.UserDAO;
import com.deneme.model.ShoppingCart;
import com.deneme.model.User;
import com.deneme.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
@CrossOrigin
@Controller
public class SecurityController {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private Tokens tokens;

    static Object loginKullaniciID;

    @RequestMapping(value = "/login/{username}/{password}", method = RequestMethod.GET)
    @ResponseBody
    public void login(HttpServletResponse response, @PathVariable(value = "username") String username ,
                      @PathVariable(value = "password") String password, HttpServletRequest request) {
        boolean b = userDAO.findLoginUser(username,password);
        if(b){

            List<User> loginKullaniciId = userDAO.getLoginUserId(username,password);
            Cookie cookie = new Cookie("token", String.valueOf(loginKullaniciId.get(0)));
            cookie.setPath("/");
            response.addCookie(cookie);
            tokens.getTokensMap().put(loginKullaniciId.get(0),loginKullaniciId.get(0));

            System.out.println("Login oldunuz." + loginKullaniciId.get(0) + " id'li kullanici");
        }
        else
            System.out.println("Login işlemi başarısız.");



    }
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    @ResponseBody
    public void logout() {
        loginKullaniciID=null;
    }

}
