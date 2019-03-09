package com.bootcamp.bootcamp.controllers;


import com.bootcamp.bootcamp.model.Trainer;
import com.bootcamp.bootcamp.service.TrainerService;
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
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private TrainerService trainerService;


    @GetMapping("/trenerzy")
    public String getTrainers(Model model) {

        model.addAttribute("trainerList", trainerService.getAllTrainers());

        return "trainerAdmin";
    }

    @GetMapping("/detale/{identyfikator}")
    public String showTrainer(@PathVariable(name = "identyfikator") int id, Model model){


        Optional<Trainer> trainer = trainerService.getOneTrainer(id);
        if(trainer.isPresent()) {
            model.addAttribute("opis", trainer.get());
            return "trainer-details";
        }else{
            return "redirect:";
        }
    }
    @GetMapping("/dodaj")
    public String contact(Model model) {
        model.addAttribute("trainer", new Trainer());
        //model.addAttribute("toAdd", true);
        return "trainerForm";
    }


    @RequestMapping(value="/dodano",params="dodaj",method=RequestMethod.POST)
    public String addTrainer(@Valid @ModelAttribute Trainer trainer, BindingResult result, Model model)
    {
        if (result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            errors.forEach(err -> System.out.println(err.getDefaultMessage()));
            model.addAttribute("trainer", trainer);
            return "trainerForm";
        }

            trainerService.addToDB(trainer);
            return "redirect:/admin/trenerzy";



    }

    @GetMapping("/edytuj/{identyfikator}")
    public String editTrainer(@PathVariable(name = "identyfikator") int id, Model model){


        Optional<Trainer> trainer = trainerService.getOneTrainer(id);
        if(trainer.isPresent()) {
            model.addAttribute("trainer", trainer.get());
            //model.addAttribute("toEdit", true);
            return "trainerForm";
        }else{
            return "redirect:";
        }
    }

    @RequestMapping(value="/dodano",params="edytuj",method=RequestMethod.POST)
    public String updateTrainer(@Valid @ModelAttribute Trainer trainer, BindingResult result, Model model)
    {
        if (result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            errors.forEach(err -> System.out.println(err.getDefaultMessage()));
            return "trainerForm";
        }
        trainerService.updateDB(trainer);
        return "redirect:/admin/trenerzy";

    }






    @GetMapping("/usun/{identyfikator}")
    public String deleteTrainer(@PathVariable(name = "identyfikator") int id, Model model){


        Optional<Trainer> trainer = trainerService.getOneTrainer(id);
        if(trainer.isPresent()) {
            trainerService.deleteTrainer(id);
            model.addAttribute("isDeleted",true);

            return "redirect:/admin/trenerzy";
        }else{
            return "redirect:/";
        }
    }




}
