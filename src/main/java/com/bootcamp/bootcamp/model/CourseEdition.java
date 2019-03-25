package com.bootcamp.bootcamp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseEdition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "course_id")
    @NotNull
    private Course course;

    @NotNull(message = "Podaj date rozpoczecia")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @NotNull(message = "Podaj date zakonczenia")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    @Positive(message = "Podaj kwotę większą od zera")
    private int price;

    @Positive(message = "Podaj limit miejsc wiekszy od zera")
    private int membersLimit;

    @ManyToOne
    @JoinColumn(name = "mode_id")
    @NotNull
    private Mode mode;

    @ManyToOne
    @JoinColumn(name = "trainer_id")
    @NotNull
    private Trainer trainer;

    private boolean active;

//    @OneToMany
//    @JoinColumn(name = "user_id")
//    private List<User> userList;



}
