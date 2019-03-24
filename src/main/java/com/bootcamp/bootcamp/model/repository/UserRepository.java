package com.bootcamp.bootcamp.model.repository;

import com.bootcamp.bootcamp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {
    List<User> findAllByOrderBySurname();

    @Query("Select t from User t where t.email=:selectedEmail")
    User selectedUser(@Param("selectedEmail") String email);


}
