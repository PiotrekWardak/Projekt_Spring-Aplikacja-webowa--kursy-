package com.bootcamp.bootcamp.service;

import com.bootcamp.bootcamp.model.User;
import com.bootcamp.bootcamp.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleService roleService;


    public List<User> getAllUsers() {
        return userRepository.findAllByOrderBySurname();
    }


    public Optional<User> getOneMode(long id) {

        return userRepository.findById(id);
    }

    public User getUser(long id) {
        return userRepository.getOne(id);
    }

    public User getLoggedUser(String email){

        return userRepository.selectedUser(email);
    }

    public void deleteUser(long id) {

        userRepository.deleteById(id);
    }

    public void addToDB(User user) {

        user.setRole(roleService.getRole("user"));
        PasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));


            userRepository.save(user);

    }

    public void updateDB(User user) {
        userRepository.save(user);
    }

    //  kodowanie hasła użytkownika przed zapisem do bazy danych
    //  PasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    //  user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()))

}
