package com.amisyp.basicwebapp.service;

import com.amisyp.basicwebapp.model.Person;

import java.util.List;

public interface IPersonSevice {
    List<Person> findAll();
    Person findById(Long id);
    Person save(Person person);
    void deleteById(Long id);
    Person findByName(String name);
}
