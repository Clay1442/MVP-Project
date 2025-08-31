package com.mvpproject.mvp_project.dto;

import com.mvpproject.mvp_project.entities.Address;

import javax.swing.text.html.parser.Entity;

public class AddressDTO {


    private Long id;
    private String street;
    private String city;
    private String state;

    public AddressDTO() {
    }

    public AddressDTO(Address entity) {

        this.street = entity.getStreet();
        this.city = entity.getCity();
        this.state = entity.getState();
        this.id = entity.getId();
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

}
