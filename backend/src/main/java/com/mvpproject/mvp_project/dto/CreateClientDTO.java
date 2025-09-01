package com.mvpproject.mvp_project.dto;

import com.mvpproject.mvp_project.entities.Client;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

public class CreateClientDTO {
    private String name;

    private String email;

    private String cpf;

    @Schema(description = "Data de nascimento do cliente", example = "1990-12-31", format = "date")
    private LocalDate birthDate;

    private String phone;

    private AddressDTO address;

    public CreateClientDTO() {
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
}
