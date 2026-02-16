package com.attendance.attendance_system.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.attendance.attendance_system.model.User;
import com.attendance.attendance_system.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // password encrypt
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    // REGISTER
    public User registerUser(User user) {
        user.setPassword(encoder.encode(user.getPassword())); // encrypt password
        return userRepository.save(user);
    }

    // LOGIN
    public String login(String email, String password) {

        Optional<User> user = userRepository.findByEmail(email);

        if(user.isPresent()) {

            // check encrypted password
            if(encoder.matches(password, user.get().getPassword())) {
                return "Login Success";
            } else {
                return "Wrong Password";
            }

        } else {
            return "User Not Found";
        }
    }
}
