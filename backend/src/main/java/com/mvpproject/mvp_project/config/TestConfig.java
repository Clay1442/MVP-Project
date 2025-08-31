package com.mvpproject.mvp_project.config;

import com.mvpproject.mvp_project.entities.Address;
import com.mvpproject.mvp_project.entities.Client;
import com.mvpproject.mvp_project.entities.User;
import com.mvpproject.mvp_project.entities.enums.Role;
import com.mvpproject.mvp_project.repositories.ClientRepository;
import com.mvpproject.mvp_project.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.Arrays;


@Profile("test")
@Configuration
public class TestConfig implements CommandLineRunner {

    private final ClientRepository clientRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public TestConfig(ClientRepository clientRepository,  UserRepository userRepository,  PasswordEncoder passwordEncoder) {
        this.clientRepository = clientRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {

        User user = new User(null, "admin", "admin@email.com", passwordEncoder.encode("123456"));
        user.addRole(Role.ROLE_ADMIN);

        userRepository.save(user);


        Client client1 = new Client(null, "Ana Silva", "ana.silva@email.com", "111.111.111-11", LocalDate.parse("1990-05-15"), "85-91111-1111");
        Client client2 = new Client(null, "Bruno Costa", "bruno.costa@email.com", "222.222.222-22", LocalDate.parse("1985-11-20"), "85-92222-2222");
        Client client3 = new Client(null, "Carla Dias", "carla.dias@email.com", "333.333.333-33", LocalDate.parse("2000-01-30"), "11-93333-3333");
        Client client4 = new Client(null, "Daniel Martins", "daniel.m@email.com", "444.444.444-44", LocalDate.parse("1998-07-22"), "21-94444-4444");
        Client client5 = new Client(null, "Eduarda Ferreira", "duda.f@email.com", "555.555.555-55", LocalDate.parse("1995-03-10"), "31-95555-5555");
        Client client6 = new Client(null, "Fábio Souza", "fabio.souza@email.com", "666.666.666-66", LocalDate.parse("1988-09-01"), "41-96666-6666");


        Address address1 = new Address(null, "Avenida Beira Mar, 1234", "Fortaleza", "CE");
        Address address2 = new Address(null, "Rua Monsenhor Tabosa, 500", "Fortaleza", "CE");
        Address address3 = new Address(null, "Avenida Paulista, 1578", "São Paulo", "SP");
        Address address4 = new Address(null, "Rua Visconde de Pirajá, 414", "Rio de Janeiro", "RJ");
        Address address5 = new Address(null, "Avenida Afonso Pena, 1500", "Belo Horizonte", "MG");
        Address address6 = new Address(null, "Rua XV de Novembro, 200", "Curitiba", "PR");


        client1.setAddress(address1);
        address1.setClient(client1);

        client2.setAddress(address2);
        address2.setClient(client2);

        client3.setAddress(address3);
        address3.setClient(client3);

        client4.setAddress(address4);
        address4.setClient(client4);

        client5.setAddress(address5);
        address5.setClient(client5);

        client6.setAddress(address6);
        address6.setClient(client6);

        clientRepository.saveAll(Arrays.asList(client1, client2, client3, client4, client5, client6));
    }


}
