package com.thelonelydanawa.restAPI.controller;

import com.thelonelydanawa.restAPI.model.Person;
import com.thelonelydanawa.restAPI.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;

    //create
    @PostMapping
    public Person createUser(@RequestBody Person person){
        return service.saveUser(person);
    }

    //read
    @GetMapping("/{id}")
    public Person getUser(@PathVariable("id") Long id){
        return service.getUser(id);
    }

    @GetMapping
    public List<Person> getAllUsers(){
        return service.getAllUser();
    }

    //update
    @PostMapping("/{id}")
    public Person updateUser(@PathVariable("id") Long id, @RequestBody Person person){
        return service.updateUser(id, person);
    }

    //delete
    @DeleteMapping("/{id}")
    public Person deleteUser(@PathVariable("id") Long id){
        return service.deleteUser(id);
    }

}
