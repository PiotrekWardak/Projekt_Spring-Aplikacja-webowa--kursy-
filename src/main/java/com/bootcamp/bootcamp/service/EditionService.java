package com.bootcamp.bootcamp.service;

import com.bootcamp.bootcamp.model.CourseEdition;
import com.bootcamp.bootcamp.model.repository.EditionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EditionService {

    @Autowired
    private EditionRepository editionRepository;

    public List<CourseEdition> getAllCourses() {
        return editionRepository.findAllByOrderByPrice();
    }

    public List<CourseEdition> getAllCoursesByStartDate() {

        return editionRepository.findAllByOrderByStartDate();
    }

    public Optional<CourseEdition> getOneCourse(long id) {

        return editionRepository.findById(id);
    }
    public CourseEdition checkCourse(long id) {


        return editionRepository.getEdition(id);
    }

    public CourseEdition getEdition(long id) {
        return editionRepository.getOne(id);
    }

    public void deleteEdition(long id) {
        editionRepository.deleteById(id);
    }

    public void addToDB(CourseEdition courseEdition) {
        courseEdition.setActive(true);
        editionRepository.save(courseEdition);
    }

    public void updateDB(CourseEdition courseEdition) {

        editionRepository.save(courseEdition);
    }



}
