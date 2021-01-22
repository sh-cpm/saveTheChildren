package com.save.children.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.save.children.model.Person;
import com.save.children.referdata.PersonDataService;



@RestController
public class PersonController {

    private PersonDataService personDataService;

    public PersonController(@Autowired PersonDataService personDataService) {
        this.personDataService = personDataService;
    }

    @GetMapping("/person/{lastName}/{firstName}")
    public Person person(@PathVariable(value="lastName") String lastName,
                         @PathVariable(value="firstName") String firstName) throws Exception {
    	Person person = personDataService.findPerson(lastName, firstName);
    	
        if(person != null)
            return person;
        else {
            System.out.println("The requested person does not exist in the list");
            return null;
        }
    }
    
    @PutMapping("/person/{id}/{lastName}/{firstName}")
    public Person personUpdate(@PathVariable(value="id") Long id,
    					@PathVariable(value="lastName") String lastName,
                         @PathVariable(value="firstName") String firstName) throws Exception {
    	Person person = personDataService.updatePersonData(id,lastName, firstName);
        if(person != null)
            return person;
        else {
            System.out.println("The requested person does not exist in the list");
            return null;
        }
    }
    
    @GetMapping("/person/{lastName}")
    public List<Person> findPersonWithSurname(@PathVariable(value="lastName") String lastName) throws Exception {
        List<Person> personList = personDataService.findPersonWithSurname(lastName);
        if(personList.isEmpty())
            System.out.println("Person Surname does not exist");
        else if(personList.size() == 1)
            System.out.println("One person exist with given Surname");
        else
            System.out.println("Multiple person exists with given Surname");
        return personList;
    }
    
    @PostMapping("/person/{lastName}/{firstName}")
    public String savePersonData(@PathVariable(value="lastName") String lastName,
                          @PathVariable(value="firstName") String firstName) throws Exception {
        String personData = personDataService.savePersonData(lastName, firstName);
        return personData;
    }

}
