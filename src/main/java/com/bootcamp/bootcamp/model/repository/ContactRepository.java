package com.bootcamp.bootcamp.model.repository;

import com.bootcamp.bootcamp.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact,Long> {
}
