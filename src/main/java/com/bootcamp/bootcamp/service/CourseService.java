package com.bootcamp.bootcamp.service;

import com.bootcamp.bootcamp.model.Course;
import com.bootcamp.bootcamp.model.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;


    public List<Course> getAllCourses() {
        return courseRepository.findAllByOrderByName();
    }


    public Optional<Course> getOneCourse(long id) {

        return courseRepository.findById(id);
    }

    public Course getTrainer(long id) {
        return courseRepository.getOne(id);
    }

    public void deleteCourse(long id) {

        courseRepository.deleteById(id);
    }

    public void addToDB(Course course) {

        courseRepository.save(course);
    }

    public void updateDB(Course course) {
        courseRepository.save(course);
    }
}
