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

    @NotEmpty(message = "Podaj Imie")
    private String name;

    @NotEmpty(message = "Podaj Nazwisko")
    private String surname;

    @NotEmpty(message = "Podaj numer tel")
    @Pattern(regexp="(^$|[0-9]{9})")
    private String phoneNumber;

    @NotEmpty(message = "Podaj email")
    @Email
    @Column(unique = true)
    private String email;

    @NotEmpty
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @ManyToOne
    @JoinColumn(name = "editions_id")
    private CourseEdition editions;

}
