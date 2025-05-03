package com.example.bitkart.controller;
import org.springframework.web.bind.annotation.RestController;

import com.example.bitkart.dto.UserRefreshTokenDTO;
import com.example.bitkart.dto.UserLoginDTO;
import com.example.bitkart.model.User;
import com.example.bitkart.service.UserService;
import com.example.bitkart.service.JwtTokenService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/auth")
public class LoginController {

  private final UserService userService;
  private final JwtTokenService jwtTokenService;

  public LoginController(UserService userService, JwtTokenService jwtTokenService) {
    this.userService = userService;
    this.jwtTokenService = jwtTokenService;
  }

  /**
   * This method handles the login request. It takes a User object as input, validates the user, and returns a JWT token if the user is valid.
   * @param user The user object containing username and password.
   * @return A JWT token if the user is valid, otherwise an error message.
   */

  @PostMapping("/login")
  public String login(@RequestBody UserLoginDTO user) {
    System.out.println("Login request received for user: " + user.getEmail());
    if (userService.validateUser(user.getEmail(), user.getPassword())) {
      // Generate JWT token using the username
      String token = jwtTokenService.generateToken(user.getEmail());
      return token;
    } else {
      return "Invalid username or password";
    }
  }

  @PostMapping("/refresh-token")
  public String refreshToken(@RequestBody UserRefreshTokenDTO userRefreshTokenDTO) {
    String token = userRefreshTokenDTO.getToken();
    String email = jwtTokenService.extractUsername(token);
    if (email != null && !jwtTokenService.isTokenExpired(token)) {
      return jwtTokenService.generateToken(email);
    } else {
      return "Invalid or expired token";
    }
  }

  @PostMapping("/register")
  public String register(@RequestBody User user) {
    return userService.registerUser(user);
  }

  
}