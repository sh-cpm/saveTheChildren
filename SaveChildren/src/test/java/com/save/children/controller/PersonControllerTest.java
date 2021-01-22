package com.save.children.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.save.children.model.Person;
import com.save.children.referdata.PersonDataService;

@RunWith(SpringRunner.class)
@WebMvcTest(PersonController.class)
public class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonDataService personDataService;

    @Test
    public void shouldReturnPersonFromService() throws Exception {
        when(personDataService.findPerson(any(), any())).thenReturn(new Person("Mary", "Smith"));
        this.mockMvc.perform(get("/person/smith/mary"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("id").exists())
            .andExpect(jsonPath("firstName").value("Mary"))
            .andExpect(jsonPath("lastName").value("Smith"));
        	//.andExpect(jsonPath("lastName").value("Sarkar"));
        
    }
    @Test
    public void shouldReturnPersonWithSurname() throws Exception {
        List<Person> personList = new ArrayList<>();
        personList.add(new Person("Mary", "Smith"));
        personList.add(new Person("Brian", "Brown"));
        personList.add(new Person("Collin", "Brown"));
        when(personDataService.findPersonWithSurname(any())).thenReturn(personList);

        this.mockMvc.perform(get("/person/Brown"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[1].lastName").value("Brown"))
                .andExpect(jsonPath("$[2].lastName").value("Brown"))
                //.andExpect(jsonPath("$[2].lastName").value("Sarkar"))
                .andReturn();
    }
    @Test
    public void testSavePersonData() throws Exception {
        this.mockMvc.perform(post("/person/Sarkar/Shyamali"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }


}