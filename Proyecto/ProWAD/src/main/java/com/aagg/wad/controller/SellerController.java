package com.aagg.wad.controller;

import com.aagg.wad.model.Product;
import com.aagg.wad.model.User;
import com.aagg.wad.service.ProductService;
import com.aagg.wad.service.StateService;
import com.aagg.wad.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class SellerController {
    @Autowired
    private ProductService productService;

    @Autowired
    private StateService stateService;

    @Autowired
    private UserService userService;

    @GetMapping(value="/seller/home")
    public ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());
        modelAndView.addObject("userName", "Welcome " + user.getUserName() + "/" + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
        modelAndView.addObject("sellerMessage","Content Available Only for Users with Seller Role");
        modelAndView.setViewName("seller/home");
        return modelAndView;
    }

    @GetMapping(value="/seller/productlist")
    public ModelAndView producList(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        User user = userService.findUserByUserName(auth.getName());

        modelAndView.addObject("productos", productService.getProductsByUser(user.getId()));
        modelAndView.setViewName("seller/product_list");
        return modelAndView;
    }

    @GetMapping(value = "/seller/formProduct")
    public ModelAndView showPage(@ModelAttribute("product") Product product) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("stateList", stateService.findAllStates());
        modelAndView.setViewName("seller/product_form");

        return modelAndView;
    }

    @GetMapping("/seller/getTowns")
    public @ResponseBody
    String getStates(@RequestParam Integer stateId)
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        String json = null;
        List<Object[]> list = stateService.getTownsByStateId(stateId);
        try {
            json = new ObjectMapper().writeValueAsString(list);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }

    @PostMapping(value = "/seller/saveProduct")
    public ModelAndView createNewProduct(@Valid Product product, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());

        Product aux_product = productService.saveProduct(product);
        productService.saveUserProduct(aux_product.getId(), user.getId());

        modelAndView.addObject("productos", productService.getProductsByUser(user.getId()));
        modelAndView.setViewName("seller/product_list");
        return modelAndView;
    }
}
