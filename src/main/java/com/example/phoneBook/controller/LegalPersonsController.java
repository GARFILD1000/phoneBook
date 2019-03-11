package com.example.phoneBook.controller;

import org.springframework.web.bind.annotation.*;

import java.util.*;
import com.example.exceptions.NotFoundException;

@RestController
@RequestMapping("/api/legalpersons")
public class LegalPersonsController{
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
    }
}
