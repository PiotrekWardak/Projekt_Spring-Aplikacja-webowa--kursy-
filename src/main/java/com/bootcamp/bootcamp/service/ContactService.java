package com.bootcamp.bootcamp.service;

import com.bootcamp.bootcamp.model.Contact;
import com.bootcamp.bootcamp.model.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;
    private Object LocalDateTime;


    public void addToDB(Contact contact){
        contact.setDate(java.time.LocalDateTime.now().plusHours(1));
        contactRepository.save(contact);
    }
}
