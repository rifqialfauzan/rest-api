package com.thelonelydanawa.restAPI.repository;

import com.thelonelydanawa.restAPI.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Person, Long> {
}
