package com.aagg.wad.controller;

import com.aagg.wad.model.Role;
import com.aagg.wad.repository.RoleRepository;
import com.aagg.wad.service.MailSenderService;
import com.aagg.wad.service.RoleService;
import com.aagg.wad.service.PersonService;
import com.aagg.wad.model.Person;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamSource;

import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import javax.validation.Valid;
import java.io.InputStream;
import java.util.*;

@Controller
@PropertySource("classpath:application.properties")
public class LoginController {

    @Autowired
    private PersonService personService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired(required = true)
    private MailSenderService mailService;

    @Value("${inlineImage}")
    String templateMailBodyImageVal;
    InputStreamSource imageSource = null;


    @GetMapping(value = "/")
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }


    @GetMapping(value="/login")
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
    public ModelAndView createNewPerson(@Valid Person person, @RequestParam("role_id") Integer role_id, BindingResult bindingResult) throws Exception {
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

            Person person_created = personService.savePerson(person);

            MultipartFile image = getImageContent();
            mailService.sendEmail(person_created.getEmail(), "Bievenido al sistema AG soluciones", image, imageSource, person_created);

            modelAndView.addObject("successMessage", "Usuario registrado correctamente, revisa tu correo electrónico o inicia sesión");
            modelAndView.addObject("person", new Person());
            modelAndView.setViewName("registration");

        }
        return modelAndView;
    }

    private MultipartFile getImageContent() throws Exception {
        InputStream imageIs = null;
        byte[] imageByteArray = null;
        MultipartFile multipartFile = null;
        imageIs = this.getClass().getClassLoader().getResourceAsStream("static/images/" + templateMailBodyImageVal);
        imageByteArray = IOUtils.toByteArray(imageIs);
        multipartFile = new MockMultipartFile(imageIs.getClass().getName(), imageIs.getClass().getName(), "image/jpg",
                imageByteArray);
        imageSource = new ByteArrayResource(imageByteArray);
        return multipartFile;
    }
}
