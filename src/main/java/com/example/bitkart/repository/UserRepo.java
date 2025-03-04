package com.example.bitkart.repository;

import com.example.bitkart.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
}
