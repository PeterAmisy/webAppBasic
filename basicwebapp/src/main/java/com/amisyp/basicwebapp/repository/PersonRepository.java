package com.amisyp.basicwebapp.repository;

import com.amisyp.basicwebapp.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
  Person findByName(String name);
}