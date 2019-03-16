package com.bootcamp.bootcamp.model.repository;

import com.bootcamp.bootcamp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {
    List<User> findAllByOrderBySurname();
}
