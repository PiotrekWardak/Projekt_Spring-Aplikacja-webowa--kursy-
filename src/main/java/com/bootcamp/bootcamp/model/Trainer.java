package com.bootcamp.bootcamp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Trainer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Pattern(regexp="^[a-zA-Z]+$", message = "{com.bootcamp.bootcamp.model.Trainer.firstName.NotEmpty}")
    private String firstName;

    @NotEmpty(message = "{com.bootcamp.bootcamp.model.Trainer.lastName.NotEmpty}")
    private String lastName;

    @Min(value =1, message = "{com.bootcamp.bootcamp.model.Trainer.salary.moreThanZero}")
    private int salary;

    @NotEmpty(message = "Musisz podac opis")
    private String description;

    @OneToMany
    private List<CourseEdition> editions;

    @NotEmpty(message = "<-- Podaj email")
    @Email
    @Column(unique = true)
    private String email;

    @NotEmpty(message = "<-- Podaj Haslo")
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @Override
    public String toString() {
        return "Trainer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", salary=" + salary +
                ", description='" + description + '\'' +
                '}';
    }
}
