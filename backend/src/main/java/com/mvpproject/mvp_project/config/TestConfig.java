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

        clientRepository.deleteAll();
        userRepository.deleteAll();

        // ==================================================
        // CRIANDO USUÁRIOS (3 ADMINS + 1 CLIENT USER)
        // ==================================================
        User admin1 = new User(null, "admin", "admin@email.com", passwordEncoder.encode("123456"));
        admin1.addRole(Role.ROLE_ADMIN);

        User admin2 = new User(null, "maria", "maria.admin@email.com", passwordEncoder.encode("123456"));
        admin2.addRole(Role.ROLE_ADMIN);

        //Role cliente para teste
        User admin3 = new User(null, "joao", "joao.supervisor@email.com", passwordEncoder.encode("123456"));
        admin3.addRole(Role.ROLE_CLIENT);

        userRepository.saveAll(Arrays.asList(admin1, admin2, admin3));


        // ==================================================
        // CRIANDO CLIENTES E ENDEREÇOS PARA TESTE (16 NO TOTAL)
        // ==================================================
        Client c1 = new Client(null, "Ana Silva", "ana.silva@email.com", "11111111111", LocalDate.parse("1990-05-15"), "85911111111");
        Address a1 = new Address(null, "Avenida Beira Mar, 1234", "Fortaleza", "CE");
        c1.setAddress(a1);
        a1.setClient(c1);

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

        Client c9 = new Client(null, "Isabela Rocha", "isabela.r@email.com", "99999999999", LocalDate.parse("2003-06-05"), "71999999999");
        Address a9 = new Address(null, "Largo do Pelourinho, 10", "Salvador", "BA");
        c9.setAddress(a9);
        a9.setClient(c9);

        Client c10 = new Client(null, "Jorge Mendes", "jorge.mendes@email.com", "10101010101", LocalDate.parse("1975-04-18"), "51910101010");
        Address a10 = new Address(null, "Avenida Borges de Medeiros, 2500", "Porto Alegre", "RS");
        c10.setAddress(a10);
        a10.setClient(c10);

        Client c11 = new Client(null, "Laura Campos", "laura.campos@email.com", "11101110110", LocalDate.parse("1999-08-30"), "92911111111");
        Address a11 = new Address(null, "Avenida Djalma Batista, 1661", "Manaus", "AM");
        c11.setAddress(a11);
        a11.setClient(c11);

        Client c12 = new Client(null, "Marcos Andrade", "marcos.a@email.com", "12121212121", LocalDate.parse("1983-10-14"), "81912121212");
        Address a12 = new Address(null, "Rua da Aurora, 1259", "Recife", "PE");
        c12.setAddress(a12);
        a12.setClient(c12);

        Client c13 = new Client(null, "Natália Pereira", "natalia.p@email.com", "13131313131", LocalDate.parse("2001-03-25"), "85913131313");
        Address a13 = new Address(null, "Rua das Gaivotas, 1700", "Fortaleza", "CE");
        c13.setAddress(a13);
        a13.setClient(c13);

        Client c14 = new Client(null, "Ana Clara Marques", "anaclara.m@email.com", "14141414141", LocalDate.parse("1994-07-19"), "11914141414");
        Address a14 = new Address(null, "Rua Oscar Freire, 827", "São Paulo", "SP");
        c14.setAddress(a14);
        a14.setClient(c14);

        Client c15 = new Client(null, "Pedro Henrique Barbosa", "pedro.hb@email.com", "15151515151", LocalDate.parse("1989-05-09"), "21915151515");
        Address a15 = new Address(null, "Avenida Atlântica, 1702", "Rio de Janeiro", "RJ");
        c15.setAddress(a15);
        a15.setClient(c15);

        Client c16 = new Client(null, "Sofia Oliveira", "sofia.oliveira@email.com", "16161616161", LocalDate.parse("1997-11-03"), "85916161616");
        Address a16 = new Address(null, "Avenida Washington Soares, 999", "Fortaleza", "CE");
        c16.setAddress(a16);
        a16.setClient(c16);

        List<Client> clientsToSave = Arrays.asList(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16);
        clientRepository.saveAll(clientsToSave);
    }


}
