package com.bootcamp.bootcamp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Podaj nazwę kursu!")
    private String name;

    @NotBlank(message = "Podaj opis kursu!")
    private String description;

    @NotBlank(message = "Podaj zakres technologiczny kursu!")
    private String technology;

    @OneToMany
    private List<CourseEdition> editions;

}
