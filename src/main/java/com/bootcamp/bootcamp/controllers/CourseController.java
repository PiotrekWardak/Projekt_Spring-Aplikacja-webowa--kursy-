package com.bootcamp.bootcamp.controllers;

import com.bootcamp.bootcamp.service.CourseService;
import com.bootcamp.bootcamp.service.EditionService;
import com.bootcamp.bootcamp.service.ModeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CourseController {

    @Autowired
    EditionService editionService;

    @Autowired
    CourseService courseService;

    @Autowired
    ModeService modeService;

    @GetMapping("/kursy")
    public String getCourses(Model model) {

        model.addAttribute("editionList", editionService.getAllCoursesByStartDate());
        return "courses";
    }
}
