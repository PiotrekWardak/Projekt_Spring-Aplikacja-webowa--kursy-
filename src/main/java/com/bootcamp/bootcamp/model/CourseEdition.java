package com.bootcamp.bootcamp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

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
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Pattern(regexp = "^\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])$", message = "Data musi byc w formacie yyyy-mm-dd")
    private String startDate;

    @NotNull(message = "Podaj date zakonczenia")
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Pattern(regexp = "^\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])$", message = "Data musi byc w formacie yyyy-mm-dd")
    private String endDate;

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
