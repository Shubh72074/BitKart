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

    @Autowired
    private HashService hashService;

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public Optional<User> userById(Long id) {
        return userRepo.findById(id);
    }

    public String registerUser(User user) {
        User newUser = new User();
        newUser.setPassword(hashService.passwordEncoder().encode(user.getPassword()));
        userRepo.save(newUser);
        return "User Added";
    }

    public boolean validateUser(String email, String password) {
        return userRepo.findByEmail(email) != null && hashService.passwordEncoder().matches(password, userRepo.findByEmail(email).getPassword());
    }
}
