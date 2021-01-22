package com.aagg.wad.service;

import com.aagg.wad.model.Role;
import com.aagg.wad.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private PersonService personService;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userName) {
        Person person = personService.findPersonByPersonName(userName);
        List<GrantedAuthority> authorities = getUserAuthority(person.getRoles());
        return buildUserForAuthentication(person, authorities);
    }

    private List<GrantedAuthority> getUserAuthority(Set<Role> personRoles) {
        Set<GrantedAuthority> roles = new HashSet<>();
        for (Role role : personRoles) {
            roles.add(new SimpleGrantedAuthority(role.getRole()));
        }
        return new ArrayList<>(roles);
    }

    private UserDetails buildUserForAuthentication(Person person, List<GrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(person.getPersonName(), person.getPassword(),
                person.getActive(), true, true, true, authorities);
    }
}
