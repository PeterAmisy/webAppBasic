package com.amisyp.basicwebapp.config;

import com.amisyp.basicwebapp.model.Person;
import com.amisyp.basicwebapp.service.ServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {
    private final ServiceImpl personService;

    public BootstrapData(ServiceImpl personService) {
        this.personService = personService;
    }

    @Override
    public void run(String... args) throws Exception {
        Person person1 = new Person("John", 25, "USA");
        Person person2 = new Person("Jane", 22, "UK");
        Person person3 = new Person("Doe", 30, "Canada");

        personService.save(person1);
        personService.save(person2);
        personService.save(person3);

        System.out.println("Data loaded: " + personService.findAll().size());
    }

}
