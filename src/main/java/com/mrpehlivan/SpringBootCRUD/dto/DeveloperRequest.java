package com.mrpehlivan.SpringBootCRUD.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;


public class DeveloperRequest implements Serializable {

    private String name;

    @JsonProperty("lastName")
    private String lastName;

    private String email;

    private String skill;

    @JsonProperty("phoneNumber")
    private String phoneNumber;

    private boolean active;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
