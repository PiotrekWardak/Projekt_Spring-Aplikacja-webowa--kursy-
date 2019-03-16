package com.bootcamp.bootcamp.controllers;

import com.bootcamp.bootcamp.model.Course;
import com.bootcamp.bootcamp.model.User;
import com.bootcamp.bootcamp.service.CourseService;
import com.bootcamp.bootcamp.service.TrainerService;
import com.bootcamp.bootcamp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    CourseService courseService;

    @Autowired
    UserService userService;

    @Autowired
    TrainerService trainerService;


    @GetMapping("/zapisz/{identyfikatorKursu}")
    public String addUserToCourse(@PathVariable(name = "identyfikatorKursu") int id, Model model) {
        Optional<Course> oneCourse = courseService.getOneCourse(id);
        if (oneCourse.isPresent())
        {
            model.addAttribute("uzytkownik", new User());
            return "enrolForm";
        }
        else
        {
            return "redirect:";
        }
    }


    @RequestMapping(value="/dodano",params="dodaj",method= RequestMethod.POST)
    private String saveUserToCourse(@Valid @ModelAttribute User user, BindingResult result, Model model){

        if(result.hasErrors())
        {
            List<ObjectError> errors = result.getAllErrors();
            errors.forEach(err -> System.out.println(err.getDefaultMessage()));
            model.addAttribute("uzytkownik", user);
            return "enrolForm";
        }
        userService.addToDB(user);
        return "redirect:/kursy";

    }









}
