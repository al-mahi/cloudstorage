package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.service.CredentialsService;
import com.udacity.jwdnd.course1.cloudstorage.service.NotesService;
import com.udacity.jwdnd.course1.cloudstorage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
public class HomeController {
    @Autowired
    private UserService userService;
    @Autowired
    private CredentialsService credentialsService;
    @Autowired
    private NotesService notesService;


    @GetMapping(value = {"/", "/home"})
    public ModelAndView getHomePage(Authentication authentication) throws Exception {
        User user = userService.getUser(authentication);
        List<Credentials> credentials = credentialsService.getAllEncryptedCredentials(user.getUserid());
        List<Credentials> plainCredentials = credentialsService.getAllDecryptedCredentials(user.getUserid());
        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("notes", notesService.getAll(user.getUserid()));
        modelAndView.addObject("credentials", credentials);
        modelAndView.addObject("plainCredentials", plainCredentials);
//        modelAndView.addObject("files", filesService.getAllFiles(appUser.getUserid()));
        return modelAndView;
    }

    @GetMapping
    public String homeView() {
        return "home";
    }
}