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
import java.util.List;

@Configuration
@Profile({"docker", "dev"})
public class DataSeedingConfig implements CommandLineRunner {

    private final UserRepository userRepository;
    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;

    public DataSeedingConfig(UserRepository userRepository, ClientRepository clientRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.clientRepository = clientRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {

        if (userRepository.count() == 0) {
            System.out.println("Criando usuários iniciais (admins)...");
            User admin1 = new User(null, "admin", "admin@email.com", passwordEncoder.encode("123456"));
            admin1.addRole(Role.ROLE_ADMIN);
            User admin2 = new User(null, "maria", "maria.admin@email.com", passwordEncoder.encode("123456"));
            admin2.addRole(Role.ROLE_ADMIN);
            User admin3 = new User(null, "joao", "joao.supervisor@email.com", passwordEncoder.encode("123456"));
            admin3.addRole(Role.ROLE_ADMIN);
            userRepository.saveAll(Arrays.asList(admin1, admin2, admin3));
            System.out.println("Usuários criados com sucesso.");
        } else {
            System.out.println("Usuários já existem. Nenhum usuário foi inserido.");
        }

        if (clientRepository.count() == 0) {
            System.out.println("Criando clientes de exemplo para demonstração...");

            Client c1 = new Client(null, "Ana Silva", "ana.silva@email.com", "11111111111", LocalDate.parse("1990-05-15"), "85911111111");
            Address a1 = new Address(null, "Avenida Beira Mar, 1234", "Fortaleza", "CE");
            c1.setAddress(a1); a1.setClient(c1);

            List<Client> clientsToSave = getClients(c1);
            clientRepository.saveAll(clientsToSave);
            System.out.println("Clientes de exemplo criados com sucesso.");
        } else {
            System.out.println("Nenhum cliente foi inserido, pois os clientes de exemplo já existem. ");
        }
    }

    private static List<Client> getClients(Client c1) {
        Client c2 = new Client(null, "Bruno Costa", "bruno.costa@email.com", "22222222222", LocalDate.parse("1985-11-20"), "85922222222");
        Address a2 = new Address(null, "Rua Monsenhor Tabosa, 500", "Fortaleza", "CE");
        c2.setAddress(a2);
        a2.setClient(c2);

        Client c3 = new Client(null, "Carla Dias", "carla.dias@email.com", "33333333333", LocalDate.parse("2000-01-30"), "11933333333");
        Address a3 = new Address(null, "Avenida Paulista, 1578", "São Paulo", "SP");
        c3.setAddress(a3);
        a3.setClient(c3);

        Client c4 = new Client(null, "Daniel Martins", "daniel.m@email.com", "44444444444", LocalDate.parse("1998-07-22"), "21944444444");
        Address a4 = new Address(null, "Rua Visconde de Pirajá, 414", "Rio de Janeiro", "RJ");
        c4.setAddress(a4);
        a4.setClient(c4);

        Client c5 = new Client(null, "Eduarda Ferreira", "duda.f@email.com", "55555555555", LocalDate.parse("1995-03-10"), "31955555555");
        Address a5 = new Address(null, "Avenida Afonso Pena, 1500", "Belo Horizonte", "MG");
        c5.setAddress(a5);
        a5.setClient(c5);

        Client c6 = new Client(null, "Fábio Souza", "fabio.souza@email.com", "66666666666", LocalDate.parse("1988-09-01"), "41966666666");
        Address a6 = new Address(null, "Rua XV de Novembro, 200", "Curitiba", "PR");
        c6.setAddress(a6);
        a6.setClient(c6);

        Client c7 = new Client(null, "Gabriela Lima", "gabriela.lima@email.com", "77777777777", LocalDate.parse("1992-02-28"), "85977777777");
        Address a7 = new Address(null, "Avenida Santos Dumont, 3000", "Fortaleza", "CE");
        c7.setAddress(a7);
        a7.setClient(c7);

        Client c8 = new Client(null, "Heitor Alves", "heitor.alves@email.com", "88888888888", LocalDate.parse("1980-12-12"), "61988888888");
        Address a8 = new Address(null, "Eixo Monumental, S/N", "Brasília", "DF");
        c8.setAddress(a8);
        a8.setClient(c8);

        List<Client> clientsToSave = Arrays.asList(c1, c2, c3, c4, c5, c6, c7, c8);
        return clientsToSave;
    }
}

