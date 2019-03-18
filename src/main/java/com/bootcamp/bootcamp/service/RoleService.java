package com.bootcamp.bootcamp.service;

import com.bootcamp.bootcamp.model.Role;
import com.bootcamp.bootcamp.model.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;


    public Role getRole(String name) {
        return roleRepository.findByRole(name);
    }
}
