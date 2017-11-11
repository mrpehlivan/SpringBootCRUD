package com.mrpehlivan.SpringBootCRUD.dto;

import com.mrpehlivan.SpringBootCRUD.model.DeveloperModel;

import java.io.Serializable;


public class DeveloperResponse implements Serializable {

    private String name;

    private String lastName;

    private String email;

    private String skill;

    private String phoneNumber;

    private boolean active;

    public DeveloperResponse() {
    }

    public DeveloperResponse(DeveloperModel developerModel) {
        this.name = developerModel.getName();
        this.lastName = developerModel.getLastName();
        this.email = developerModel.getEmail();
        this.skill = developerModel.getSkill();
        this.phoneNumber = developerModel.getPhoneNumber();
        this.active = developerModel.isActive();
    }

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
