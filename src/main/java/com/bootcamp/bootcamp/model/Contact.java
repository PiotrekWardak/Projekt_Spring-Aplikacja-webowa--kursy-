package com.bootcamp.bootcamp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty(message = "Proszę podać swoje imię")
    private String firstName;

    @NotEmpty(message = "{com.bootcamp.bootcamp.model.Contact.lastName.NotEmpty}")
    private String lastName;

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    private String message;

    private LocalDateTime date;

}
