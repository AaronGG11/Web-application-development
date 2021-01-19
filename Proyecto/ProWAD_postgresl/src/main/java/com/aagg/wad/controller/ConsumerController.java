package com.aagg.wad.controller;

import com.aagg.wad.model.User;
import com.aagg.wad.service.ProductService;
import com.aagg.wad.service.StateService;
import com.aagg.wad.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ConsumerController {
    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @GetMapping(value="/consumer/home")
    public ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());
        modelAndView.addObject("userName", "Welcome " + user.getUserName() + "/" + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
        modelAndView.addObject("adminMessage","Content Available Only for Users with Consumer Role");
        modelAndView.setViewName("consumer/home");
        return modelAndView;
    }

    @GetMapping(value="/consumer/productlist")
    public ModelAndView producList(){
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("productos", productService.getAvailableProducts());
        modelAndView.setViewName("consumer/product_list");
        return modelAndView;
    }

    @GetMapping(value = "/consumer/buy/{id}")
    public ModelAndView buyProduct(@PathVariable Integer id){
        ModelAndView modelAndView = new ModelAndView();

        productService.decreaseStock(id);

        modelAndView.addObject("productos", productService.getAvailableProducts());
        modelAndView.setViewName("consumer/product_list");
        return modelAndView;
    }
}
