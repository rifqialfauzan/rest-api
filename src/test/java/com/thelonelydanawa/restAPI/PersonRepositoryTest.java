package com.thelonelydanawa.restAPI;

import com.thelonelydanawa.restAPI.model.Person;
import com.thelonelydanawa.restAPI.repository.PersonRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class PersonRepositoryTest {

    @Autowired
    PersonRepository personRepository;
    Person person1 = new Person();
    Person person2 = new Person();

    @BeforeEach
    public void init(){
        person1.setName("Kanaya");
        person1.setEmail("kanayailya@gmail.com");
        person1.setPassword("password");
        person2.setName("pitaya");
        person2.setEmail("pitaya@gmail.com");
        person2.setPassword("password");
    }

    @Test
    public void toSaveAndReturnPerson() {
        Person save = personRepository.save(person1);

        Assertions.assertEquals(save, person1);
        Assertions.assertNotNull(save);
    }

    @Test
    void toGetAllPersonAndReturnListOfPerson() {
        List<Person> list = personRepository.saveAll(Arrays.asList(person1, person2));

        List<Person> people = personRepository.findAll();

        Assertions.assertNotNull(people);
        Assertions.assertEquals(people.size(), list.size());
    }

    @Test
    void toGetSinglePersonByIdAndReturnSinglePerson() {
        List<Person> list = personRepository.saveAll(Arrays.asList(person1, person2));

        Person person = personRepository.findById(1L).get();

        Assertions.assertEquals(person.getId(), person1.getId());
        Assertions.assertNotEquals(person.getId(), person2.getId());
        Assertions.assertNotNull(person);
    }

    @Test
    void toUpdatePersonAndReturnUpdatedPerson() {
        List<Person> list = personRepository.saveAll(Arrays.asList(person1, person2));

        Person person = personRepository.findById(1L).get();

        person.setName("Kanaya Ilya");
        person.setPassword(person.getPassword());
        person.setEmail(person.getEmail());

        Person updated = personRepository.save(person);

        Assertions.assertNotNull(updated);
        Assertions.assertEquals("Kanaya Ilya", updated.getName());
        Assertions.assertNotEquals("kanaya", updated.getName());
        Assertions.assertEquals(person.getId(), updated.getId());

    }

    @Test
    void ToDeletePersonByIdAndReturnNothing() {
        List<Person> list = personRepository.saveAll(Arrays.asList(person1, person2));

        personRepository.deleteById(person1.getId());

        Optional<Person> person = personRepository.findById(person1.getId());

        Assertions.assertTrue(person.isEmpty());

    }
}
