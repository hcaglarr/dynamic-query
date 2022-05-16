package com.hcaglar.dynamicquery.dto;

import java.util.UUID;

/**
 * @author Hüseyin ÇAĞLAR
 * @version 1.0
 * @since 16.05.2022
 */
public class PersonDto {

    private UUID id;
    private String firstName;
    private String lastName;
    private Integer age;

    public PersonDto() {
    }

    public PersonDto(String firstName, String lastName, Integer age) {
       this(null,firstName, lastName, age);
    }
    public PersonDto(UUID id, String firstName, String lastName, Integer age) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
