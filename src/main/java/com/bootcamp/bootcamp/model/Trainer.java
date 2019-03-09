package com.bootcamp.bootcamp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

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
