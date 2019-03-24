package com.bootcamp.bootcamp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty(message = "<-- Podaj Imie")
    private String name;

    @NotEmpty(message = "<-- Podaj Nazwisko")
    private String surname;


    @Pattern(regexp="(^$|[0-9]{9})", message = "<-- Numer powinien sie skladac z 9 cyfr")
    private String phoneNumber;

    @NotEmpty(message = "<-- Podaj email")
    @Email
    @Column(unique = true)
    private String email;

    @NotEmpty(message = "<-- Podaj Haslo")
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

//    @ManyToOne
//    @JoinColumn(name = "editions_id")
//    private CourseEdition editions;
    /// tego juz nie używam ponieważ zadanie domowe 16.03 mowi o wykasowaniu tej relacji a w zamian mowa jest o stworzeniu oddzielnej tabeli z numerem studenta i z numerem edycji

}
