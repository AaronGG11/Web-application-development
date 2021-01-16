package com.aagg.wad.controller;

import com.aagg.wad.model.Product;
import com.aagg.wad.model.State;
import com.aagg.wad.model.Town;
import com.aagg.wad.model.User;
import com.aagg.wad.service.ProductService;
import com.aagg.wad.service.StateService;
import com.aagg.wad.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

@Controller
public class AdminController {
    @Autowired
    private ProductService productService;

    @Autowired
    private StateService stateService;

    @Autowired
    private UserService userService;

    @GetMapping(value="/admin/productlist")
    public ModelAndView producList(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        User user = userService.findUserByUserName(auth.getName());

        modelAndView.addObject("productos", productService.getProductsByUser(user.getId()));
        modelAndView.setViewName("admin/product_list");
        return modelAndView;
    }

    @GetMapping(value = "/admin/formProduct")
    public ModelAndView showPage(@ModelAttribute("product") Product product) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("stateList", stateService.findAllStates());
        modelAndView.setViewName("admin/product_form");

        return modelAndView;
    }

    @GetMapping("/admin/getTowns")
    public @ResponseBody String getStates(@RequestParam Integer stateId)
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

    @PostMapping(value = "/admin/saveProduct")
    public ModelAndView createNewProduct(@Valid Product product, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());

        Product aux_product = productService.saveProduct(product);
        productService.saveUserProduct(aux_product.getId(), user.getId());

        modelAndView.addObject("productos", productService.getProductsByUser(user.getId()));
        modelAndView.setViewName("admin/product_list");
        return modelAndView;
    }


}
