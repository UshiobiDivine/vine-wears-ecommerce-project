package com.divine.project.controller;

import com.divine.project.model.User;
import com.divine.project.exception.ResourceNotFoundException;
import com.divine.project.repository.UserRepository;
import com.divine.project.security.CurrentUser;
import com.divine.project.security.UserPrincipal;
import com.divine.project.util.mail.MailServiceImplementation;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;



    @GetMapping("/user/me")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public User getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {

        return userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
    }


}
