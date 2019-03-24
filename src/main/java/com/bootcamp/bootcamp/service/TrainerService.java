package com.bootcamp.bootcamp.service;

import com.bootcamp.bootcamp.model.Trainer;
import com.bootcamp.bootcamp.model.User;
import com.bootcamp.bootcamp.model.repository.TrainerRepository;
import com.bootcamp.bootcamp.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrainerService {

    @Autowired
    private TrainerRepository trainerRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserRepository userRepository;


    public List<Trainer> getAllTrainers() {
        List<Trainer> trainerList = trainerRepository.findAllByOrderByLastName();

        return trainerList;
    }


    public Optional<Trainer> getOneTrainer(long id) {

        return trainerRepository.findById(id);
    }

    public Trainer getTrainer(long id) {
        return trainerRepository.getOne(id);
    }
    //getOne zwraca TRainer a nie Optional tak jak findById

    public void deleteTrainer(long id) {
        User trainerFromUserTable = userRepository.selectedUser(trainerRepository.getOne(id).getEmail());

        userRepository.deleteById(trainerFromUserTable.getId());
        trainerRepository.deleteById(id);
    }

    public void addToDB(Trainer trainer) {
        trainer.setRole(roleService.getRole("trainer"));
        PasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        trainer.setPassword(bCryptPasswordEncoder.encode(trainer.getPassword()));

        User user = null;
        try {
            if (userRepository.selectedUser(trainer.getEmail()).equals(null)) {
            }
            System.out.println("Podany login(emial) juz istnieje. Nie mozesz dodac trenera");
        }

        catch(NullPointerException e){
            user = new User();
            TrainerToUser(trainer, user);
            System.out.println("Dodałeś trenera. Gratuluję!");
        }



    }

    public void updateDB(Trainer trainer) {
        trainer.setRole(roleService.getRole("trainer"));

        Trainer oneTrainer = trainerRepository.getOne(trainer.getId());
        User trainerFromUserTable = userRepository.selectedUser(oneTrainer.getEmail());

        try {
            if (userRepository.selectedUser(trainer.getEmail()).equals(null)) {
            }
            else if(trainer.getEmail().equals(trainerFromUserTable.getEmail()))
            {
                TrainerToUser(trainer, trainerFromUserTable);
                System.out.println("Edytowales trenera w traju. Gratuluję!");
            }
            else {
                System.out.println("Podany email juz istnieje w bazie!");
            }
        }
        catch(NullPointerException e){

            TrainerToUser(trainer, trainerFromUserTable);
            System.out.println("Edytowales trenera w keczu. Gratuluję!");
        }

    }

    private void TrainerToUser(Trainer trainer, User trainerFromUserTable) {
        trainerFromUserTable.setEmail(trainer.getEmail());
        trainerFromUserTable.setName(trainer.getFirstName());
        trainerFromUserTable.setPassword(trainer.getPassword());
        trainerFromUserTable.setSurname(trainer.getLastName());
        trainerFromUserTable.setRole(trainer.getRole());
        userRepository.save(trainerFromUserTable);
        trainerRepository.save(trainer);
    }

    public Trainer getLoggedTrainer(String email) {

        return trainerRepository.selectedTrainer(email);
    }


}
