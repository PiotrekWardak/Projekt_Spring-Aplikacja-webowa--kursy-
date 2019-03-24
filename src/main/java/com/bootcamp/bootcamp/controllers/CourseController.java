package com.bootcamp.bootcamp.controllers;

import com.bootcamp.bootcamp.model.CourseEdition;
import com.bootcamp.bootcamp.model.EnrolmentDetails;
import com.bootcamp.bootcamp.model.User;
import com.bootcamp.bootcamp.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/kursy")
public class CourseController {

    @Autowired
    EditionService editionService;

    @Autowired
    CourseService courseService;

    @Autowired
    ModeService modeService;

    @Autowired
    UserService userService;

    @Autowired
    EnrolmentDetailsService enrolmentDetailsService;


    @GetMapping("")
    public String getCourses(Model model) {

        model.addAttribute("editionList", editionService.getAllCoursesByStartDate());
        return "courses";
    }

    @GetMapping("/zapisz/{identyfikatorKursu}")
    public String addUserToCourse(@PathVariable(name = "identyfikatorKursu") long id, Model model) {
        CourseEdition edition = editionService.checkCourse(id);
// mozemy zobaczyc czy taka edycja istnieje i potem sprawdzic czy zwrocono null czy nie ale tez mozna zrobic validacje w formularzu(tak jest teraz zrobione)
//        if (edition!= null)
//        {
        model.addAttribute("uzytkownik", new User());
        model.addAttribute("edition", edition);
        return "enrolForm";
//        }
//        else
//        {
//            System.out.println("TUTAJ");
//            return "redirect:/kursy";
//        }
    }


    @RequestMapping(value="/dodano/{id}",params="dodaj",method= RequestMethod.POST)
    private String saveUserToCourse(@Valid @ModelAttribute(name = "uzytkownik") User user, BindingResult result, @PathVariable(name = "id") long id,
                                    Model model){


        CourseEdition edition = editionService.checkCourse(id);
        try {
            if (result.hasErrors()) {

                List<ObjectError> errors = result.getAllErrors();
                errors.forEach(err -> System.out.println(err.getDefaultMessage()));
                model.addAttribute("uzytkownik", user);
                model.addAttribute("edition", edition);
                return "enrolForm";

            } else if (user.getEmail().equals(userService.getLoggedUser(user.getEmail()).getEmail())) {
                System.out.println("Taki user juz istnieje");
                model.addAttribute("exist", true);
                model.addAttribute("uzytkownik", user);
                model.addAttribute("edition", edition);


                return "enrolForm";
            } else {
                userService.addToDB(user);
                return "redirect:/kursy";
            }


        } catch (NullPointerException e) {
            userService.addToDB(user);
            User newUser = userService.getLoggedUser(user.getEmail());
            EnrolmentDetails enrolmentDetails = new EnrolmentDetails();
            enrolmentDetails.setCourseEdition(edition);
            enrolmentDetails.setUser(newUser);
            enrolmentDetailsService.addToDB(enrolmentDetails);
            return "redirect:/kursy";
        }

    }


}
