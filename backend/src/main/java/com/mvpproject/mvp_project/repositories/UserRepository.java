package com.mvpproject.mvp_project.repositories;

import com.mvpproject.mvp_project.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
