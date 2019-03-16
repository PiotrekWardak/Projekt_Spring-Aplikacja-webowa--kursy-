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

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    @Positive
    private int price;

    @Positive
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

//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User userList;



}
