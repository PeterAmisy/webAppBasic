package com.amisyp.basicwebapp.controller;

import com.amisyp.basicwebapp.model.Person;
import com.amisyp.basicwebapp.service.ServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PersonController.class)
public class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ServiceImpl personService;

    @Test
    public void testGetAllPersons() throws Exception {
        List<Person> persons = Arrays.asList(new Person(1L, "John", 30, "USA"), new Person(2L, "Jane", 25, "UK"));
        when(personService.findAll()).thenReturn(persons);

        mockMvc.perform(get("/api/v1/person"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("John"))
                .andExpect(jsonPath("$[1].name").value("Jane"));
    }

    @Test
    public void testGetPersonByIdWithoutIdGiven() throws Exception {
        Person person = new Person( "John", 30, "USA");
        when(personService.findById(anyLong())).thenReturn(person);

        mockMvc.perform(get("/api/v1/person/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John"));
    }

    @Test
    public void testCreatePerson() throws Exception {
        Person person = new Person(1L, "John", 30, "USA");
        when(personService.save(any(Person.class))).thenReturn(person);

        mockMvc.perform(post("/api/v1/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"John\",\"age\":30,\"country\":\"USA\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("John"));
    }

    @Test
    public void testDeletePerson() throws Exception {
        mockMvc.perform(delete("/api/v1/person/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testUpdatePerson() throws Exception {
        Person person = new Person(1L, "John", 30, "USA");
        when(personService.findById(anyLong())).thenReturn(person);
        when(personService.save(any(Person.class))).thenReturn(person);

        mockMvc.perform(put("/api/v1/person/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"John\",\"age\":30,\"country\":\"USA\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John"));
    }

    @Test
    public void testGetPersonByName() throws Exception {
        Person person = new Person(1L, "John", 30, "USA");
        when(personService.findByName("John")).thenReturn(person);

        mockMvc.perform(get("/api/v1/person/name/John"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John"));
    }
}