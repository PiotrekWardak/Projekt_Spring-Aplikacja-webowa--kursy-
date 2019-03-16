package com.bootcamp.bootcamp.service;

import com.bootcamp.bootcamp.model.User;
import com.bootcamp.bootcamp.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {


    @Autowired
    private UserRepository userRepository;


    public List<User> getAllUsers() {
        return userRepository.findAllByOrderBySurname();
    }


    public Optional<User> getOneMode(long id) {

        return userRepository.findById(id);
    }

    public User getUser(long id) {
        return userRepository.getOne(id);
    }

    public void deleteUser(long id) {

        userRepository.deleteById(id);
    }

    public void addToDB(User user) {

        userRepository.save(user);
    }

    public void updateDB(User user) {
        userRepository.save(user);
    }


}
