package com.amisyp.basicwebapp.service;


import com.amisyp.basicwebapp.model.Person;
import com.amisyp.basicwebapp.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceImpl implements IPersonSevice {
    private final PersonRepository personRepository;

    public ServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public List<Person> findAll() {
        return personRepository.findAll();
    }

    @Override
    public Person findById(Long id) {
        return personRepository.findById(id).orElse(null);
    }

    @Override
    public Person save(Person person) {
        return personRepository.save(person);
    }

    @Override
    public void deleteById(Long id) {
        personRepository.deleteById(id);
    }
    @Override
    public Person findByName(String name) {
        return personRepository.findByName(name);
    }

}
