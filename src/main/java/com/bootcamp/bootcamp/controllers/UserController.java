package com.bootcamp.bootcamp.controllers;

import com.bootcamp.bootcamp.model.CourseEdition;
import com.bootcamp.bootcamp.model.EnrolmentDetails;
import com.bootcamp.bootcamp.model.User;
import com.bootcamp.bootcamp.service.EditionService;
import com.bootcamp.bootcamp.service.EnrolmentDetailsService;
import com.bootcamp.bootcamp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user")
public class UserController {


    @Autowired
    UserService userService;

    @Autowired
    EditionService editionService;

    @Autowired
    EnrolmentDetailsService enrolmentDetailsService;


    @GetMapping("")
    public String goToHomePage()
    {
        return "/home";
    }






    @GetMapping("/panel")
    public String userPanel( Model model) {
     return "userPanel/userPanel";
    }

    @GetMapping("/panel/enrolments")
    public String userPanelEnrolments( Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User loggedUser = userService.getLoggedUser(auth.getName());
        List<EnrolmentDetails> allStudentEnrolments = enrolmentDetailsService.getAllStudentEnrolments(loggedUser.getId());
        LinkedList<CourseEdition> editionlist = new LinkedList<>();

        List<CourseEdition> editionList = allStudentEnrolments.stream().map(x -> editionService.getEdition(x.getCourseEdition().getId())).collect(Collectors.toList());
        model.addAttribute("editionList",editionList);


        return "userPanel/userPanelEnrolments";
    }






    @GetMapping(value="/dodano/{id}")
    private String saveUserToCourse(@PathVariable(name = "id") long id, Model model){
        CourseEdition edition = editionService.checkCourse(id);
        List<CourseEdition> editionList = editionService.getAllCoursesByStartDate();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        User loggedUser = userService.getLoggedUser(auth.getName());


        if(enrolmentDetailsService.checkIfEnroled(edition.getId(),loggedUser.getId())>0)
        {
            model.addAttribute("editionList",editionList);
            model.addAttribute("present",true);
            return "courses";
        }
        else
        {
            EnrolmentDetails enrolmentDetails = new EnrolmentDetails();
            enrolmentDetails.setUser(loggedUser);
            enrolmentDetails.setCourseEdition(edition);
            enrolmentDetailsService.addToDB(enrolmentDetails);
            model.addAttribute("editionList",editionList);
            model.addAttribute("isSavedNew",true);

            return "courses";
        }


    }












}
