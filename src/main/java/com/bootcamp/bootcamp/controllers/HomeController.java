package com.bootcamp.bootcamp.controllers;

import com.bootcamp.bootcamp.model.User;
import com.bootcamp.bootcamp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class HomeController {


    @Autowired
    UserService userService;

//    @GetMapping("/")
//    public String getHome(@RequestParam(name = "imie", defaultValue = "Jan") String firstName,
//                          @RequestParam(name = "nazwisko", required = false) String lastName,
//                          @RequestParam(defaultValue = "40") int wiek,
//                          Model model) {
//
//        model.addAttribute("wiek",wiek);
//        model.addAttribute("dddd", firstName);
//        model.addAttribute("lastName", lastName);
//        return "home";
//    }
@GetMapping("/")
public String getHome(
                      Model model) {

    Authentication auth =  SecurityContextHolder.getContext().getAuthentication();

    System.out.println();


    if(!"anonymousUser".equals(auth.getPrincipal())){
        System.out.println("Weszło do potwierdzenia");

        User loggedUser = userService.getLoggedUser(auth.getName());

    }

    return "home";
}


    @GetMapping("/logowanie")
    public String loginForm(){

        return "login_form";
    }


    @GetMapping("/add")
    public String newUser(Model model){

        model.addAttribute("user",new User());

        return "setUpAnUserAccount";
    }

    @PostMapping("/added")
    public String addNewUserToDB(@Valid @ModelAttribute User user, BindingResult result, Model model){

        if(result.hasErrors())
        {
            List<ObjectError> allErrors = result.getAllErrors();
            allErrors.stream().forEach(error -> System.out.println(error.getDefaultMessage()) );
            return "setUpAnUserAccount";
        }
        else if(user.getEmail().equals(userService.getLoggedUser(user.getEmail()).getEmail())){
            System.out.println("Taki user juz istnieje");
            model.addAttribute("exist",true);
            model.addAttribute("user", user);

            return "setUpAnUserAccount";
        }

        else{userService.addToDB(user);
        return "redirect:/";}
    }


}
