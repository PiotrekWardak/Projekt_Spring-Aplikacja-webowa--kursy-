package com.bootcamp.bootcamp.model.repository;

import com.bootcamp.bootcamp.model.CourseEdition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EditionRepository extends JpaRepository<CourseEdition,Long> {
    List<CourseEdition> findAllByOrderByPrice();
    List<CourseEdition> findAllByOrderByStartDate();
    List<CourseEdition> findAllByTrainer_Id(Long id);

    @Query("select t from CourseEdition t where t.id=:id and t.active=1")
    CourseEdition getEdition(@Param("id") Long id);
}
