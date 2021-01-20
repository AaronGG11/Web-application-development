package com.aagg.wad.controller;

import com.aagg.wad.model.Product;
import com.aagg.wad.model.Person;
import com.aagg.wad.service.ProductService;
import com.aagg.wad.service.StateService;
import com.aagg.wad.service.PersonService;
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
@RequestMapping("/seller")
public class SellerController {
    @Autowired
    private ProductService productService;

    @Autowired
    private StateService stateService;

    @Autowired
    private PersonService personService;

    @GetMapping(value="/home")
    public ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Person user = personService.findPersonByPersonName(auth.getName());
        modelAndView.addObject("userName", "Welcome " + user.getPersonName() + "/" + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
        modelAndView.addObject("sellerMessage","Content Available Only for Users with Seller Role");
        modelAndView.setViewName("seller/home");
        return modelAndView;
    }

    @GetMapping(value="/productlist")
    public ModelAndView producList(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        Person user = personService.findPersonByPersonName(auth.getName());

        modelAndView.addObject("productos", productService.getProductsByPerson(user.getId()));
        modelAndView.setViewName("seller/product_list");
        return modelAndView;
    }

    @GetMapping(value = "/formProduct")
    public ModelAndView showForm(@ModelAttribute("product") Product product) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("stateList", stateService.findAllStates());
        modelAndView.setViewName("seller/product_form");

        return modelAndView;
    }

    @GetMapping("/getTowns")
    public @ResponseBody
    String getStates(@RequestParam Integer stateId) {
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

    @PostMapping(value = "/saveProduct")
    public ModelAndView createNewProduct(@Valid Product product, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Person user = personService.findPersonByPersonName(auth.getName());

        Product aux_product = productService.saveProduct(product);
        productService.savePersonProduct(aux_product.getId(), user.getId());

        modelAndView.addObject("productos", productService.getProductsByPerson(user.getId()));
        modelAndView.setViewName("seller/product_list");
        return modelAndView;
    }

    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public ModelAndView viewProduct(@PathVariable Integer id){
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("producto", productService.findProductById(id));
        modelAndView.setViewName("seller/product_view");
        return modelAndView;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteProduct(@PathVariable Integer id){
        ModelAndView modelAndView = new ModelAndView();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Person user = personService.findPersonByPersonName(auth.getName());

        productService.deleteProductPersonBy(user.getId(), id);
        productService.deleteProductById(id);

        modelAndView.addObject("productos", productService.getProductsByPerson(user.getId()));
        modelAndView.setViewName("seller/product_list");
        return modelAndView;
    }
}
