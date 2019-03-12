package com.bootcamp.bootcamp.model.repository;

import com.bootcamp.bootcamp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
