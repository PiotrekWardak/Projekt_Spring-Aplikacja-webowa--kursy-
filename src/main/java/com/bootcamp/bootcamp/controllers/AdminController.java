package com.bootcamp.bootcamp.controllers;


import com.bootcamp.bootcamp.model.Course;
import com.bootcamp.bootcamp.model.CourseEdition;
import com.bootcamp.bootcamp.model.Trainer;
import com.bootcamp.bootcamp.service.*;
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

    @Autowired
    private CourseService courseService;

    @Autowired
    private EditionService editionService;

    @Autowired
    private ModeService modeService;

    @Autowired
    private UserService userService;

    @GetMapping("")
    public String menuAdmin(Model model) {
        return "homeAdmin";
    }

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

    @GetMapping("/kursy")
    public String getCourses(Model model) {

        model.addAttribute("courseList", courseService.getAllCourses());

        return "courseAdmin";
    }


    @GetMapping("/kursy/detale/{identyfikator}")
    public String showCourse(@PathVariable(name = "identyfikator") int id, Model model){


        Optional<Course> course = courseService.getOneCourse(id);
        if(course.isPresent()) {
            model.addAttribute("opis", course.get());
            return "course-details";
        }else{
            return "redirect:";
        }
    }

    @GetMapping("/kursy/dodaj")
    public String course(Model model) {
        Course course = Course.builder().build();
        model.addAttribute("course", course);
        //model.addAttribute("toAdd", true);
        return "courseForm";
    }
    @RequestMapping(value="/kursy/dodano",params="dodaj",method=RequestMethod.POST)
    public String addCourse(@Valid @ModelAttribute Course course, BindingResult result, Model model)
    {
        if (result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            errors.forEach(err -> System.out.println(err.getDefaultMessage()));
            model.addAttribute("course", course);
            return "courseForm";
        }

        courseService.addToDB(course);
        return "redirect:/admin/kursy";
    }

    @GetMapping("/kursy/edytuj/{identyfikator}")
    public String editCourse(@PathVariable(name = "identyfikator") int id, Model model){


        Optional<Course> course = courseService.getOneCourse(id);
        if(course.isPresent()) {
            model.addAttribute("course", course.get());
            //model.addAttribute("toEdit", true);
            return "courseForm";
        }else{
            return "redirect:";
        }
    }

    @RequestMapping(value="/kursy/dodano",params="edytuj",method=RequestMethod.POST)
    public String updateCourse(@Valid @ModelAttribute Course course, BindingResult result, Model model)
    {
        if (result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            errors.forEach(err -> System.out.println(err.getDefaultMessage()));
            return "courseForm";
        }
        courseService.updateDB(course);
        return "redirect:/admin/kursy";
    }

//////////////////////////////////////////////////////////////

    @GetMapping("/edycje")
    public String getEditions(Model model) {

        model.addAttribute("editionList", editionService.getAllCourses());

        return "editionAdmin";
    }

    @GetMapping("/edycje/dodaj")
    public String edition(Model model) {
        CourseEdition courseEdition = CourseEdition.builder().build();
        model.addAttribute("courseEdition", courseEdition);
        model.addAttribute("courses", courseService.getAllCourses());
        model.addAttribute("mode", modeService.getAllModes());
        model.addAttribute("trainer", trainerService.getAllTrainers());
        return "editionForm";
    }
    @RequestMapping(value="/edycje/dodano",params="dodaj",method=RequestMethod.POST)
    public String addEdition(@Valid @ModelAttribute CourseEdition courseEdition, BindingResult result, Model model)
    {
        if (result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            errors.forEach(err -> System.out.println(err.getDefaultMessage()));
            model.addAttribute("courses", courseService.getAllCourses());
            model.addAttribute("mode", modeService.getAllModes());
            model.addAttribute("courseEdition", courseEdition);
            model.addAttribute("trainer", trainerService.getAllTrainers());

            return "editionForm";
        }

        editionService.addToDB(courseEdition);
        return "redirect:/admin/edycje";
    }

    @GetMapping("/edycje/edytuj/{identyfikator}")
    public String editEdition(@PathVariable(name = "identyfikator") int id, Model model){


        Optional<CourseEdition> courseEdition = editionService.getOneCourse(id);
        if(courseEdition.isPresent()) {
            model.addAttribute("courseEdition", courseEdition.get());
            model.addAttribute("courses", courseService.getAllCourses());
            model.addAttribute("mode", modeService.getAllModes());
            model.addAttribute("trainer", trainerService.getAllTrainers());
            //model.addAttribute("toEdit", true);
            return "editionForm";
        }else{
            return "redirect:";
        }
    }

    @RequestMapping(value="/edycje/dodano",params="edytuj",method=RequestMethod.POST)
    public String updateEdition(@Valid @ModelAttribute CourseEdition courseEdition, BindingResult result, Model model)
    {
        if (result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            errors.forEach(err -> System.out.println(err.getDefaultMessage()));
            model.addAttribute("courseEdition", courseEdition);
            model.addAttribute("courses", courseService.getAllCourses());
            model.addAttribute("mode", modeService.getAllModes());
            model.addAttribute("trainer", trainerService.getAllTrainers());
            return "editionForm";
        }
        editionService.updateDB(courseEdition);
        return "redirect:/admin/edycje";

    }



}
