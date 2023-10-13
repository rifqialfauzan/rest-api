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
//    Inisiasi Objek person
    Person person1 = new Person();
    Person person2 = new Person();

    @BeforeEach
    public void init(){
//        Inisiasi attribute dari si objek
        person1.setName("Kanaya");
        person1.setEmail("kanayailya@gmail.com");
        person1.setPassword("password");
        person2.setName("pitaya");
        person2.setEmail("pitaya@gmail.com");
        person2.setPassword("password");
    }

    @Test
    public void toSaveAndReturnPerson() {
//        Do Save
        Person save = personRepository.save(person1);

//        Do Assert
        Assertions.assertEquals(save, person1);
        Assertions.assertNotNull(save);
    }

    @Test
    void toGetAllPersonAndReturnListOfPerson() {
//        Do Save
        List<Person> list = personRepository.saveAll(Arrays.asList(person1, person2));

//        Do get all using method "findAll"
        List<Person> people = personRepository.findAll();

//        DO Assert
        Assertions.assertNotNull(people);
        Assertions.assertEquals(people.size(), list.size());
    }

    @Test
    void toGetSinglePersonByIdAndReturnSinglePerson() {
//        Do Save
        personRepository.saveAll(Arrays.asList(person1, person2));

//        Do get by calling findById method
        Person person = personRepository.findById(1L).get();

//        Do Assert
        Assertions.assertEquals(person.getId(), person1.getId());
        Assertions.assertNotEquals(person.getId(), person2.getId());
        Assertions.assertNotNull(person);
    }

    @Test
    void toUpdatePersonAndReturnUpdatedPerson() {
//        Do Save
        personRepository.saveAll(Arrays.asList(person1, person2));

//        To get a person
        Person person = personRepository.findById(1L).get();

//        Update the person attribute
        person.setName("Kanaya Ilya");
        person.setPassword(person.getPassword());
        person.setEmail(person.getEmail());

//        Save Updated person
        Person updated = personRepository.save(person);

//        Do Assert
        Assertions.assertNotNull(updated);
        Assertions.assertEquals("Kanaya Ilya", updated.getName());
        Assertions.assertNotEquals("kanaya", updated.getName());
        Assertions.assertEquals(person.getId(), updated.getId());

    }

    @Test
    void ToDeletePersonByIdAndReturnNothing() {
//        Do Save
        personRepository.saveAll(Arrays.asList(person1, person2));

//        Do Delete
        personRepository.deleteById(person1.getId());

//        Try get deleted person
        Optional<Person> person = personRepository.findById(person1.getId());

//        Do Assert
        Assertions.assertTrue(person.isEmpty());

    }
}
