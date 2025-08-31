package com.mvpproject.mvp_project.repositories;

import com.mvpproject.mvp_project.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByLogin(String login);

}
