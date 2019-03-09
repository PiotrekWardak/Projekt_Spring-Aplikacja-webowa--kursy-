package com.bootcamp.bootcamp.service;

import com.bootcamp.bootcamp.model.Trainer;
import com.bootcamp.bootcamp.model.repository.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrainerService {

    @Autowired
    private TrainerRepository trainerRepository;


    public List<Trainer> getAllTrainers() {
        List<Trainer> trainerList = trainerRepository.findAllByOrderByLastName();

        return trainerList;
    }


    public Optional<Trainer> getOneTrainer(long id) {

       return trainerRepository.findById(id);
    }

    public Trainer getTrainer(long id){
        return trainerRepository.getOne(id);
    }
    //getOne zwraca TRainer a nie Optional tak jak findById

    public void deleteTrainer(long id){

        trainerRepository.deleteById(id);
    }
    public void addToDB(Trainer trainer){

        trainerRepository.save(trainer);
    }

    public void updateDB(Trainer trainer){
trainerRepository.save(trainer);
       // trainerRepository.edytuj(trainer.getId(),trainer.getFirstName(),trainer.getLastName(),trainer.getSalary(),trainer.getDescription());
    }



}
