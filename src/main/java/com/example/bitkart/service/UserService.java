package com.example.bitkart.service;

import com.example.bitkart.model.User;
import com.example.bitkart.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public Optional<User> userById(Long id) {
        return userRepo.findById(id);
    }
}
