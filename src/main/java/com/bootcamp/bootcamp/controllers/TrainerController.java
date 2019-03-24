package com.bootcamp.bootcamp.controllers;

import com.bootcamp.bootcamp.model.CourseEdition;
import com.bootcamp.bootcamp.model.Trainer;
import com.bootcamp.bootcamp.service.EditionService;
import com.bootcamp.bootcamp.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Optional;

@Controller
public class TrainerController {

    @Autowired
    private TrainerService trainerService;

    @Autowired
    private EditionService editionService;


    @GetMapping("/trenerzy")
    public String getTrainers(Model model) {

        model.addAttribute("trainerList", trainerService.getAllTrainers());

        return "trainer";
    }
    @GetMapping("/trainer")
    public String getTrainerss(Model model) {

        model.addAttribute("trainerList", trainerService.getAllTrainers());

        return "trainer";
    }

    @GetMapping("/trenerzy/{identyfikator}")
    public String showTrainer(@PathVariable(name = "identyfikator") int id, Model model){


        Optional<Trainer> trainer = trainerService.getOneTrainer(id);
        if(trainer.isPresent()) {
            model.addAttribute("opis", trainer.get());
            return "trainer-details";
        }else{
            return "redirect:";
        }
    }


    @RequestMapping(value = "trainer/panel", method = RequestMethod.GET)
    public String trainerPanel( Model model) {
        return "trainerPanel/trainerPanel";
    }

    @GetMapping("trainer/panel/enrolments")
    public String userPanelEnrolments( Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Trainer loggedTrainer = trainerService.getLoggedTrainer(auth.getName());
        try{
        List<CourseEdition> editionList = editionService.getAllTrainerCourses(loggedTrainer.getId());
        model.addAttribute("editionList",editionList);}
        catch (NullPointerException e)
        {
            model.addAttribute("editionList",null);
            System.out.println("weszło do catch tutejszego!");
        }


        return "trainerPanel/trainerPanelEnrolments";
    }



}
