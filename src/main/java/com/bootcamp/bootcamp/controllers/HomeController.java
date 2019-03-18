package com.bootcamp.bootcamp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @GetMapping("/")
    public String getHome(@RequestParam(name = "imie", defaultValue = "Jan") String firstName,
                          @RequestParam(name = "nazwisko", required = false) String lastName,
                          @RequestParam(defaultValue = "40") int wiek,
                          Model model) {

        model.addAttribute("wiek",wiek);
        model.addAttribute("dddd", firstName);
        model.addAttribute("lastName", lastName);
        return "home";
    }


    @GetMapping("/logowanie")
    public String loginForm(){

        return "login_form";
    }

//    @GetMapping("/logout")
//    public String logoutForm(){
//
//        return "/home";
//    }

}
