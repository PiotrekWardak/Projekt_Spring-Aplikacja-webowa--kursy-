package com.bootcamp.bootcamp.controllers;

import com.bootcamp.bootcamp.model.Contact;
import com.bootcamp.bootcamp.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ContactController {

    @Autowired
    private ContactService contactService;

    @GetMapping("/kontakt")
    public String contact(Model model) {
        model.addAttribute("contact", new Contact());
        return "contact";
    }
    @PostMapping("/wyslij")
    public String sendMessage(@ModelAttribute Contact contact, Model model)//łapiemy cały model z formularza
    {
        model.addAttribute("isSent",true);
        contactService.addToDB(contact);

        return "contact";
    }

}
