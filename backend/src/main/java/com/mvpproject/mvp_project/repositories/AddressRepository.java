package com.mvpproject.mvp_project.repositories;

import com.mvpproject.mvp_project.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
