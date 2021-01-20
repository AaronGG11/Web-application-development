package com.aagg.wad.service;

import com.aagg.wad.repository.RoleRepository;
import com.aagg.wad.repository.PersonRepository;
import com.aagg.wad.model.Person;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.MockitoAnnotations.initMocks;

public class PersonServiceTest {

    @Mock
    private PersonRepository mockPersonRepository;
    @Mock
    private RoleRepository mockRoleRepository;
    @Mock
    private BCryptPasswordEncoder mockBCryptPasswordEncoder;

    private PersonService personServiceUnderTest;
    private Person user;

    @Before
    public void setUp() {
        initMocks(this);
        personServiceUnderTest = new PersonService(mockPersonRepository,
                                               mockRoleRepository,
                                               mockBCryptPasswordEncoder);
        user = Person.builder()
                .id(1)
                .name("Gustavo")
                .lastName("Ponce")
                .email("test@test.com")
                .build();

        Mockito.when(mockPersonRepository.save(any()))
                .thenReturn(user);
        Mockito.when(mockPersonRepository.findByEmail(anyString()))
                .thenReturn(user);
    }

    @Test
    public void testFindUserByEmail() {
        // Setup
        final String email = "test@test.com";

        // Run the test
        final Person result = personServiceUnderTest.findPersonByEmail(email);

        // Verify the results
        assertEquals(email, result.getEmail());
    }

    @Test
    public void testSaveUser() {
        // Setup
        final String email = "test@test.com";

        // Run the test
        Person result = personServiceUnderTest.savePerson(Person.builder().build());

        // Verify the results
        assertEquals(email, result.getEmail());
    }
}
