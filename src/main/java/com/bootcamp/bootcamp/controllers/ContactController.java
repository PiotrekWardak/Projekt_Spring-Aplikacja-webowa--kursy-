package com.bootcamp.bootcamp.controllers;

import com.bootcamp.bootcamp.model.Contact;
import com.bootcamp.bootcamp.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

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
    public String sendMessage(@Valid @ModelAttribute Contact contact, BindingResult result, Model model)//łapiemy cały model z formularza
    {
        if (result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            errors.forEach(err -> System.out.println(err.getDefaultMessage()));
        }
        else {
            model.addAttribute("isSent",true);
            contactService.addToDB(contact);
        }

       // model.addAttribute("contact",new Contact());


        return "contact";
    }

}
