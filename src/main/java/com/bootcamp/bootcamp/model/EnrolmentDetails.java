package com.bootcamp.bootcamp.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnrolmentDetails {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "enrolment_id")
    private long id;

    @OneToOne
    private User user;

    @OneToOne
    private CourseEdition courseEdition;



}
