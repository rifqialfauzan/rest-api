package com.thelonelydanawa.restAPI.service;

import com.thelonelydanawa.restAPI.model.Person;
import com.thelonelydanawa.restAPI.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Override
    public Person saveUser(Person person) {
        return repository.save(person);
    }

    @Override
    public List<Person> getAllUser() {
        return repository.findAll();
    }

    @Override
    public Person getUser(Long id) {
        Optional<Person> user = repository.findById(id);
        return user.orElseThrow();
    }

    @Override
    public Person updateUser(Long id, Person person) {
        Optional<Person> getUser = repository.findById(id);

        if (getUser.isPresent()){
            Person updatePerson = getUser.get();
            if (Objects.nonNull(person.getName()) && !person.getName().isBlank()){
                updatePerson.setName(person.getName());
            }
            updatePerson.setEmail(updatePerson.getEmail());
            updatePerson.setPassword(updatePerson.getPassword());
            repository.save(updatePerson);
        }
        return repository.findById(id).get();
    }

    @Override
    public Person deleteUser(Long id) {
        Person person = repository.findById(id).get();
        repository.deleteById(id);
        return person;
    }
}
