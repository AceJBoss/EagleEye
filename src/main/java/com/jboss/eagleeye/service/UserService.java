package com.jboss.eagleeye.service;

import com.jboss.eagleeye.model.Incident;
import com.jboss.eagleeye.model.User;
import com.jboss.eagleeye.payload.ResponseMessage;
import com.jboss.eagleeye.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<User>registerUser(User user){
        try{
            if(userRepository.existsByEmail(user.getEmail()) == true){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            if(userRepository.existsByPhone(user.getPhone()) == true){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            String password = user.getPassword();
            String encodedPassword = new BCryptPasswordEncoder().encode(password);
            user.setPassword(encodedPassword);
            user.setRole(1L);
            return new ResponseEntity<>(userRepository.save(user), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }
}
