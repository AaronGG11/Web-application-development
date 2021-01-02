package com.aagg.wad.controller;

import com.aagg.wad.model.Role;
import com.aagg.wad.repository.RoleRepository;
import com.aagg.wad.service.RoleService;
import com.aagg.wad.service.UserService;
import com.aagg.wad.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.*;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping(value={"/", "/login"})
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }


    @GetMapping(value="/registration")
    public ModelAndView registration(){
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();

        List<Role> roles = roleService.findAllRoles();

        modelAndView.addObject("roles_list", roles);
        modelAndView.addObject("user", user);
        modelAndView.setViewName("registration");

        return modelAndView;
    }

    @PostMapping(value = "/registration")
    public ModelAndView createNewUser(@Valid User user, @RequestParam("role_id") Integer role_id, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        User userExists = userService.findUserByUserName(user.getUserName());
        if (userExists != null) {
            bindingResult
                    .rejectValue("userName", "error.user",
                            "There is already a user registered with the user name provided");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("registration");
        } else {
            Map<Integer, String> roles = new Hashtable<>();

            for(Role r: roleRepository.findAll()){
                roles.put(r.getId(), r.getRole());
            }

            Role userRole = roleRepository.findByRole(roles.get(role_id));
            user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));

            userService.saveUser(user);
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("registration");

        }
        return modelAndView;
    }


    @GetMapping(value="/admin/home")
    public ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());
        modelAndView.addObject("userName", "Welcome " + user.getUserName() + "/" + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
        modelAndView.addObject("adminMessage","Content Available Only for Users with Admin Role");
        modelAndView.setViewName("admin/home");
        return modelAndView;
    }





}
