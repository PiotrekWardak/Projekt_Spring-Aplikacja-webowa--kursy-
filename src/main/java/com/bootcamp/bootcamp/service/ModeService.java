package com.bootcamp.bootcamp.service;


import com.bootcamp.bootcamp.model.Mode;
import com.bootcamp.bootcamp.model.repository.ModeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Service
@RequestMapping
public class ModeService {

    @Autowired
    private ModeRepository modeRepository;


    public List<Mode> getAllModes() {
        return modeRepository.findAllByOrderByNazwa();
    }


    public Optional<Mode> getOneMode(long id) {

        return modeRepository.findById(id);
    }

    public Mode getMode(long id) {
        return modeRepository.getOne(id);
    }

    public void deleteMode(long id) {

        modeRepository.deleteById(id);
    }

    public void addToDB(Mode mode) {

        modeRepository.save(mode);
    }

    public void updateDB(Mode mode) {
        modeRepository.save(mode);
    }



}
