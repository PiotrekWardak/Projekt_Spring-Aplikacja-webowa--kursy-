package com.bootcamp.bootcamp.controllers;

        import com.bootcamp.bootcamp.model.CourseEdition;
        import com.bootcamp.bootcamp.model.User;
        import com.bootcamp.bootcamp.service.EditionService;
        import com.bootcamp.bootcamp.service.EnrolmentDetailsService;
        import com.bootcamp.bootcamp.service.UserService;
        import lombok.extern.slf4j.Slf4j;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.http.HttpStatus;
        import org.springframework.http.ResponseEntity;
        import org.springframework.web.bind.annotation.*;

        import java.util.List;

@Slf4j
@RestController
@RequestMapping("/client")
public class CourseRestController {

    @Autowired
    EditionService editionService;

    @Autowired
    UserService userService;

    @Autowired
    EnrolmentDetailsService enrolmentDetailsService;

    @GetMapping("/kursy")
    public ResponseEntity<List<CourseEdition>> getCourses() {

        log.info("Pobranie listy wszystkich kursów");
        List<CourseEdition> courseEditions = editionService.getAllCoursesByStartDate();

        return ResponseEntity.ok(courseEditions);
    }




    @GetMapping("/pobierz/{identyfikatorKursu}")
    public ResponseEntity<CourseEdition> getCourseEdition(@PathVariable(name = "identyfikatorKursu") long id) {


        log.info("Pobranie listy wszystkich kursów");
        CourseEdition edition = editionService.checkCourse(id);
        if(edition !=null)
        {
            return ResponseEntity.ok(edition);
        }

        return new ResponseEntity("Brak kursu o podanym id", HttpStatus.NOT_FOUND);
    }


    @PostMapping(value="/pobierz/{id}")
    private ResponseEntity saveUserToCourse(@RequestBody User user, @PathVariable long id){
        log.info("Save user {}, and get course by id {}",user,id);
        try{
            userService.addToDB(user);
        }catch (Exception e){
            new ResponseEntity("Błąd zapisu użytkownika", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok("Udało się huraaa");
//        CourseEdition courseEdition = editionService.getEdition(id);
//        if(courseEdition !=null){
//            return ResponseEntity.ok(courseEdition);
//        }
//        return new ResponseEntity("Brak kursu o podanym id", HttpStatus.NOT_FOUND);


    }





}
