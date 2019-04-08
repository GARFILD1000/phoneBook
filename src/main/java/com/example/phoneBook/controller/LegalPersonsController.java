package com.example.phoneBook.controller;

import com.example.phoneBookDatabase.LegalPerson;
import com.example.phoneBookDatabase.PhoneBook;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.gson.GsonProperties;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.boot.json.JsonParseException;
import org.springframework.boot.json.JsonParser;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import com.example.exceptions.NotFoundException;

import com.example.phoneBookDatabase.PhoneBook;

@RestController
@RequestMapping("/api/legalpersons")
public class LegalPersonsController{
    @GetMapping
    public String list(){
        System.out.println("Get all legal persons");
        String legalPersons = "";
        legalPersons = PhoneBook.getLegalPersons();
        return legalPersons;
    }

    @GetMapping("{id}")
    public String getOne(@PathVariable String id){
        System.out.println("Get legal person with id " + id);
        return getPerson(id);
    }

    private String getPerson(@PathVariable String id){
        String person = "";
        person = PhoneBook.getLegalPersonById(Integer.valueOf(id));
        return person;
    }

    @PostMapping
    public String createPerson(@RequestBody String newPersonJson){
        System.out.println("Create new legal person");
        PhoneBook.addLegalPerson(newPersonJson);
        return newPersonJson;
    }

    @PutMapping("{id}")
    public String updatePerson(@PathVariable String id, @RequestBody String person){
        System.out.println("Update legal person with id " + id);
        PhoneBook.updateLegalPerson(Integer.valueOf(id), person);
        return getPerson(id);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable String id){
        System.out.println("Delete legal person with id " + id);
        PhoneBook.deleteLegalPerson(Integer.valueOf(id));
    }


/*


public List<Map<String, String>> persons = new ArrayList<Map<String, String>>(){{
        add(new HashMap<String, String>(){{ put("id", "1"); put ("fio", "First person"); put ("phone", "82309823059802"); put ("address", "Kolhidskaya 12"); put ("INN", "1928471928471"); }});
        add(new HashMap<String, String>(){{ put("id", "2"); put ("fio", "Second person"); put ("phone", "10240197491827"); put ("address", "Zabalueva 13"); put ("INN", "1928471352351"); }});
        add(new HashMap<String, String>(){{ put("id", "3"); put ("fio", "Third person"); put ("phone", "82309823059802"); put ("address", "Kommunisticheskaya 22"); put ("INN", "1847236238471"); }});
    }};
    private int counter = 4;

    @GetMapping
    public List<Map<String, String>> list(){
        return persons;
    }
    @GetMapping("{id}")
    public Map<String, String> getOne(@PathVariable String id){
        return getPerson(id);
    }

    private Map<String, String> getPerson(@PathVariable String id){
        return persons.stream()
                .filter(person -> person.get("id").equals(id))
                .findFirst()
                .orElseThrow(NotFoundException::new);
    }

    @PostMapping
    public Map<String, String> create(@RequestBody Map<String, String> person){
        person.put("id", String.valueOf(counter++));
        persons.add(person);
        return person;
    }

    @PutMapping("{id}")
    public Map<String, String> update(@PathVariable String id, @RequestBody Map<String, String> person){
        Map<String, String> personFromDatabase = getPerson(person.get("id"));

        personFromDatabase.putAll(person);
        personFromDatabase.put("id", id);
        return personFromDatabase;
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable String id){
        Map<String, String> person = getPerson(id);
        persons.remove(person);
    }*/
}
