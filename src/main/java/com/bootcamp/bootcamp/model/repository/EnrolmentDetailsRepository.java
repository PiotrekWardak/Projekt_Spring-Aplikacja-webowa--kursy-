package com.bootcamp.bootcamp.model.repository;

import com.bootcamp.bootcamp.model.EnrolmentDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrolmentDetailsRepository extends JpaRepository <EnrolmentDetails,Long> {


    @Query("select t from EnrolmentDetails t where user_id=:id")
    List<EnrolmentDetails> getStudentEnrolments(@Param("id") Long id);

    @Query("select count(t) from EnrolmentDetails t where user_id=:userId and course_edition_id=:editonId")
    long checkEdUs(@Param("userId") long userId,@Param("editonId") long editionId);


}
