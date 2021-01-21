package com.aagg.wad.controller;

import com.aagg.wad.model.Product;
import com.aagg.wad.model.Person;
import com.aagg.wad.model.StateTown;
import com.aagg.wad.service.MailSenderService;
import com.aagg.wad.service.ProductService;
import com.aagg.wad.service.StateService;
import com.aagg.wad.service.PersonService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.Hashtable;
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
    public ModelAndView addProduct(@ModelAttribute("product") Product product) {
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

        System.out.println(product);

        if(product.getId() == null){ // new user
            Product product_saved = productService.saveProduct(product);
            productService.savePersonProduct(product_saved.getId(), user.getId());
        }else{ // update user
            productService.updateProduct(product);
        }



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

    @GetMapping(value = "/update/{id}")
    public ModelAndView updateProduct(@PathVariable Integer id, @ModelAttribute("product") Product product) {
        //modelAndView.addObject("product", productService.findProductById(id));
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("stateList", stateService.findAllStates());
        modelAndView.setViewName("seller/product_form");

        return modelAndView;
    }

    @RequestMapping(path = "/pdfReport", method = RequestMethod.GET)
    public ModelAndView report() {
        ModelAndView modelAndView = new ModelAndView();

        return modelAndView;
    }

    @Autowired
    DataSource datasource;


    @GetMapping(value = "/pdf")
    public void imprimir(HttpServletResponse response) throws JRException, IOException, SQLException {
        InputStream jasperStream = this.getClass().getResourceAsStream("/reports/seller_report.jasper");
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, datasource.getConnection());
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "inline; filename=livros.pdf");

        final OutputStream outputStream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
    }




}
