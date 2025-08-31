package com.mvpproject.mvp_project.config;

import com.mvpproject.mvp_project.entities.Address;
import com.mvpproject.mvp_project.entities.Client;
import com.mvpproject.mvp_project.repositories.ClientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.LocalDate;


@Profile("test")
@Configuration
public class TestConfig implements CommandLineRunner {

    final ClientRepository clientRepository;

    public TestConfig(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Client client1 = new Client(null, "clay", "clay@email.com", "999.999.999-02", LocalDate.parse("2005-03-31"), "85-98888-8888");

        Address address1 = new Address(null, "amarelo", "Fortaleza", "Ceará");

        client1.setAddress(address1);

        clientRepository.save(client1);
    }


}
