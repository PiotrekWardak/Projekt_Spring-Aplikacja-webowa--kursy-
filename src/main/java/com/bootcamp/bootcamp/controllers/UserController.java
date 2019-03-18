package com.bootcamp.bootcamp.controllers;

import com.bootcamp.bootcamp.model.CourseEdition;
import com.bootcamp.bootcamp.model.User;
import com.bootcamp.bootcamp.service.CourseService;
import com.bootcamp.bootcamp.service.EditionService;
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

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    CourseService courseService;

    @Autowired
    UserService userService;

    @Autowired
    TrainerService trainerService;

    @Autowired
    EditionService editionService;


    @GetMapping("")
    public String goToHomePage()
    {
        return "/home";
    }



    @GetMapping("/zapisz/{identyfikatorKursu}")
    public String addUserToCourse(@PathVariable(name = "identyfikatorKursu") long id, Model model) {
        CourseEdition edition = editionService.checkCourse(id);

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
        if(result.hasErrors())
        {

                List<ObjectError> errors = result.getAllErrors();
                errors.forEach(err -> System.out.println(err.getDefaultMessage()));
                model.addAttribute("uzytkownik", user);
                model.addAttribute("edition", edition);
                return "enrolForm";

        }
        userService.addToDB(user);
        return "redirect:/kursy";

    }

    @GetMapping("/panel")
    public String userPanel( Model model) {
     return "userPanel";
    }


}
