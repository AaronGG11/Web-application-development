package com.aagg.wad.controller;

import com.aagg.wad.model.Person;
import com.aagg.wad.service.ProductService;
import com.aagg.wad.service.StateService;
import com.aagg.wad.service.PersonService;
import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.*;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private PersonService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    DataSource datasource;

    @GetMapping(value="/home")
    public ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Person user = userService.findPersonByPersonName(auth.getName());
        modelAndView.addObject("userName", "Welcome " + user.getPersonName() + "/" + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
        modelAndView.addObject("adminMessage","Content Available Only for Users with Admin Role");
        modelAndView.setViewName("admin/home");
        return modelAndView;
    }

    @GetMapping(value="/reportList")
    public ModelAndView reportList(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/report_list");
        return modelAndView;
    }


    @GetMapping(value = "/SellerReport")
    public void sellerReport(HttpServletResponse response) throws JRException, IOException, SQLException {
        InputStream jasperStream = this.getClass().getResourceAsStream("/reports/seller_report.jasper");
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, datasource.getConnection());
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "inline; filename=seller_report.pdf");

        final OutputStream outputStream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
    }

    @GetMapping(value = "/ConsumerReport")
    public void consumerReport(HttpServletResponse response) throws JRException, IOException, SQLException {
        InputStream jasperStream = this.getClass().getResourceAsStream("/reports/consumer_report.jasper");
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, datasource.getConnection());
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "inline; filename=consumer_report.pdf");

        final OutputStream outputStream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
    }

    @GetMapping(value = "/graph")
    public ModelAndView graphUsers(){
        ModelAndView modelAndView = new ModelAndView();

        Map<String, Integer> values_to_graph = new HashMap<>();
        values_to_graph.put("seller", productService.countUserTypeByRoleId(2));
        values_to_graph.put("consumer", productService.countUserTypeByRoleId(3));
        values_to_graph.put("all", productService.countAllUsers());

        modelAndView.addObject("params", values_to_graph);
        modelAndView.setViewName("admin/graph_users");

        return modelAndView;
    }

    @RequestMapping(value = "/chart", method=RequestMethod.GET)
    public String chart(Model model) {

        Map<String, Integer> values_to_graph = new HashMap<>();
        values_to_graph.put("seller", productService.countUserTypeByRoleId(2));
        values_to_graph.put("consumer", productService.countUserTypeByRoleId(3));

        model.addAttribute("params", values_to_graph);

        return "admin/graph_users";
    }

}
