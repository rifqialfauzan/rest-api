package com.thelonelydanawa.restAPI.service;

import com.thelonelydanawa.restAPI.model.Person;

import java.util.List;

public interface UserService {
    // Create
    Person saveUser(Person person);
    // Read
    List<Person> getAllUser();

    Person getUser(Long id);
    // Update
    Person updateUser(Long id, Person person);
    // Delete
    Person deleteUser(Long id);
}
