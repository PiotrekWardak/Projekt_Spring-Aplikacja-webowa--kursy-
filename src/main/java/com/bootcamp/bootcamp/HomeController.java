package com.bootcamp.bootcamp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
//@RequestMapping(value = "/bootcamp",method = RequestMethod.GET)
public class HomeController {

//    @RequestMapping(value = "/",method = RequestMethod.GET)
    @GetMapping("/")
    public String gethome(@RequestParam(name = "imie") String firstName) {

        System.out.println("Witaj " + firstName);
        return "home";
    }
@RequestMapping(value = "/o-nas",method = RequestMethod.GET)
    public String getAbout(){
        return "about";
    }



}
