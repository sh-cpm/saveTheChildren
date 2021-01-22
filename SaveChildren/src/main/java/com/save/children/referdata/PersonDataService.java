package com.save.children.referdata;

import org.springframework.stereotype.Service;

import com.save.children.model.Person;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonDataService {
    public static final List<Person> PERSON_DATA = new ArrayList(Arrays.asList(
        new Person("Mary", "Smith"),
        new Person("Brian", "Archer"),
        new Person("Collin", "Brown")
        //new Person("John", "Smith")
    ));
    private final CryptingData crypt;
    
    public PersonDataService(CryptingData crypt) {
        this.crypt = crypt;
    }
    
    public Person findPerson(String lastName, String firstName)throws Exception {
        List<Person> person = PERSON_DATA.stream()
            .filter(p -> p.getFirstName().equalsIgnoreCase(firstName)
                && p.getLastName().equalsIgnoreCase(lastName))
            .collect(Collectors.toList());
        if(person.size() > 0)
            return person.get(0);
        else
            return null;
    }
    
    public Person updatePersonData(Long id, String lastName, String firstName) throws Exception {
    	String personData = null;
    	Person personDetail = null;
        boolean dataExsist = PERSON_DATA.stream()
                .anyMatch(p -> p.getId().equals(id));
        List<Person> person = PERSON_DATA.stream()
                .filter(p -> p.getFirstName().equalsIgnoreCase(firstName)
                    && p.getLastName().equalsIgnoreCase(lastName))
                .collect(Collectors.toList());
        	person.remove(person);
        	int i=id.intValue();  
        	Person newPerson = new Person(id,firstName, lastName);
            PERSON_DATA.set(i, newPerson);
            personDetail = findPerson(lastName,firstName);
            personData = "Person Data updated"+firstName+lastName;
            System.out.println(personData);
        
        return personDetail;
    	
    }

    public List<Person> findPersonWithSurname(String lastName) throws Exception {
        return PERSON_DATA.stream()
                .filter(p -> p.getLastName().equalsIgnoreCase(lastName))
                .collect(Collectors.toList());
    }

    public String savePersonData(String lastName, String firstName) throws Exception {
        String personData = null;
        String encryptPersonData = null;
        boolean dataExsist = PERSON_DATA.stream()
                .anyMatch(p -> p.getFirstName().equalsIgnoreCase(firstName)
                        && p.getLastName().equalsIgnoreCase(lastName));
        if(!dataExsist) {
        	Person newPerson = new Person(crypt.encrypting(firstName), crypt.encrypting(lastName));
            PERSON_DATA.add(newPerson);
            personData = "Person Data Added "+firstName+lastName;
            encryptPersonData = "Person Encrypt Data "+crypt.encrypting(firstName)+crypt.encrypting(lastName);
            System.out.println(personData);
            System.out.println(encryptPersonData);
        }else{
        	personData = "Person Data Exsist"+" "+firstName+lastName;
        	System.out.println(personData);
        }
        return encryptPersonData;
    }
    
    
}

