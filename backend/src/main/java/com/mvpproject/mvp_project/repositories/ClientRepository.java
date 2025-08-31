package com.mvpproject.mvp_project.repositories;

import com.mvpproject.mvp_project.entities.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {

    Page<Client> findByNameContainingIgnoreCase(String name, Pageable pageable);

    Page<Client> findByEmail(String email, Pageable pageable);

    Page<Client> findByCpf(String cpf, Pageable pageable);


}
