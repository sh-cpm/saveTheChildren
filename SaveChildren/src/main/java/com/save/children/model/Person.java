package com.save.children.model;

import java.util.concurrent.atomic.AtomicLong;

public class Person {
    private static final AtomicLong counter = new AtomicLong();

    private Long id;
    private String firstName;
    private String lastName;

    private Person() {
        // empty
    }

    public Person(String firstName, String lastName) {
        this.id = counter.incrementAndGet();
        this.firstName = firstName;
        this.lastName = lastName;
    }
    public Person(Long id,String firstName, String lastName) {
        this.id = id+1;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}