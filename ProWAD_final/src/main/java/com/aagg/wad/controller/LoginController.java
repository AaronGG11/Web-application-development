package com.aagg.wad.controller;

import com.aagg.wad.model.Role;
import com.aagg.wad.repository.RoleRepository;
import com.aagg.wad.service.RoleService;
import com.aagg.wad.service.PersonService;
import com.aagg.wad.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
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
    private PersonService personService;

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
        Person person = new Person();

        List<Role> roles = roleService.findAllRoles();

        modelAndView.addObject("roles_list", roles);
        modelAndView.addObject("person", person);
        modelAndView.setViewName("registration");

        return modelAndView;
    }

    @PostMapping(value = "/registration")
    public ModelAndView createNewPerson(@Valid Person person, @RequestParam("role_id") Integer role_id, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        Person personExists = personService.findPersonByPersonName(person.getPersonName());
        if (personExists != null) {
            bindingResult
                    .rejectValue("personName", "error.person",
                            "There is already a user registered with the user name provided");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("registration");
        } else {
            Map<Integer, String> roles = new Hashtable<>();

            for(Role r: roleRepository.findAll()){
                roles.put(r.getId(), r.getRole());
            }

            Role personRole = roleRepository.findByRole(roles.get(role_id));
            person.setRoles(new HashSet<Role>(Arrays.asList(personRole)));

            personService.savePerson(person);
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("person", new Person());
            modelAndView.setViewName("registration");

        }
        return modelAndView;
    }
}
