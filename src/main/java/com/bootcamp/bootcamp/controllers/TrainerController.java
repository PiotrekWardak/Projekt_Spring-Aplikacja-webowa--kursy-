package com.bootcamp.bootcamp.controllers;

import com.bootcamp.bootcamp.model.Trainer;
import com.bootcamp.bootcamp.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/trenerzy")
public class TrainerController {

    @Autowired
    private TrainerService trainerService;


    @GetMapping("")
    public String getTrainers(Model model) {

        model.addAttribute("trainerList", trainerService.getAllTrainers());

        return "trainer";
    }

    @GetMapping("/{identyfikator}")
    public String showTrainer(@PathVariable(name = "identyfikator") int id, Model model){


        Optional<Trainer> trainer = trainerService.getOneTrainer(id);
        if(trainer.isPresent()) {
            model.addAttribute("opis", trainer.get());
            return "trainer-details";
        }else{
            return "redirect:";
        }
    }
}
