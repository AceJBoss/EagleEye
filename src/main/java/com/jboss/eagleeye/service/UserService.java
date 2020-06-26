package com.jboss.eagleeye.service;

import com.jboss.eagleeye.model.User;
import com.jboss.eagleeye.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User registerUser(User user){
            String password = user.getPassword();
            String encodedPassword = new BCryptPasswordEncoder().encode(password);
            user.setPassword(encodedPassword);
            user.setRole(1L);
            return userRepository.save(user);
    }
}
