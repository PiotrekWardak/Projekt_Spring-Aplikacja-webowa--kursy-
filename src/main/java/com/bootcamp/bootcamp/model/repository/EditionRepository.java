package com.bootcamp.bootcamp.model.repository;

import com.bootcamp.bootcamp.model.CourseEdition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EditionRepository extends JpaRepository<CourseEdition,Long> {
    List<CourseEdition> findAllByOrderByPrice();
    List<CourseEdition> findAllByOrderByStartDate();
}
