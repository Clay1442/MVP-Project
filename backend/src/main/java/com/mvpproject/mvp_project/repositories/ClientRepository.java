package com.mvpproject.mvp_project.repositories;

import com.mvpproject.mvp_project.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {

}
