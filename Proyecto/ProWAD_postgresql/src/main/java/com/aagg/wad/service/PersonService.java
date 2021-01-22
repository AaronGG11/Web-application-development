package com.aagg.wad.service;

import com.aagg.wad.repository.RoleRepository;
import com.aagg.wad.repository.PersonRepository;
import com.aagg.wad.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    private PersonRepository personRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public PersonService(PersonRepository personRepository,
                         RoleRepository roleRepository,
                         BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.personRepository = personRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public Person findPersonByEmail(String email) {
        return personRepository.findByEmail(email);
    }

    public Person findPersonByPersonName(String personName) {
        return personRepository.findByPersonName(personName);
    }

    public Person savePerson(Person person) {
        person.setPassword(bCryptPasswordEncoder.encode(person.getPassword()));
        person.setActive(true);
        return personRepository.save(person);
    }

}