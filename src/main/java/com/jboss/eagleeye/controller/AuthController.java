package com.jboss.eagleeye.controller;

import com.jboss.eagleeye.model.JwtRequest;
import com.jboss.eagleeye.model.JwtResponse;
import com.jboss.eagleeye.security.JwtToken;
import com.jboss.eagleeye.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtToken jwtToken;
    @Autowired
    private MyUserDetailsService myUserDetailsService;
    
    // login new user
    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());

        final UserDetails userDetails = myUserDetailsService
                .loadUserByUsername(authenticationRequest.getEmail());

        final String token = jwtToken.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));

    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}

