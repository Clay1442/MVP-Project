package com.mvpproject.mvp_project.dto;

import com.mvpproject.mvp_project.entities.Client;

import java.time.LocalDate;
import java.time.Period;

public class ClientDTO {

    private Long id;

    private String name;

    private String email;

    private String cpf;

    private LocalDate birthDate;

    private int age;

    private String phone;

    private AddressDTO address;

    public ClientDTO() {
    }

    public ClientDTO(Client entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.email = entity.getEmail();
        this.cpf = entity.getCpf();
        this.birthDate = entity.getBirthDate();
        this.phone = entity.getPhone();
        this.address = new AddressDTO(entity.getAddress());
        this.age = Period.between(entity.getBirthDate(), LocalDate.now()).getYears();

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

}
