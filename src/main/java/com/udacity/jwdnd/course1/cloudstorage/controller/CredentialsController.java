package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.service.CredentialsService;
import com.udacity.jwdnd.course1.cloudstorage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CredentialsController {

    @Autowired
    private CredentialsService credentialsService;

    @Autowired
    private UserService userService;

    @PostMapping("/credentials")
    public String saveOrUpdateCredentials(@ModelAttribute Credentials credentials, Authentication authentication) throws IllegalAccessException {
        User user = userService.getUser(authentication);
        if (credentials.getCredentialid() > 0) {
            int rowsChanged = credentialsService.updateCredential(credentials, user.getUserid());
            if(rowsChanged <0){
                return "redirect:home?error";
            }
        } else {
            int rowsChanged = credentialsService.addCredential(credentials, user.getUserid());
            if(rowsChanged < 0) {
                return "redirect:home?error";
            }
        }
        return "redirect:home?success";
    }

    @GetMapping("/credentials/delete")
    public String deleteCredentials(@RequestParam("id") int credentialid, Authentication authentication) throws IllegalAccessException {
        User user = userService.getUser(authentication);
        if (credentialid > 0) {
            credentialsService.deleteCredential(credentialid, user.getUserid());
            return "redirect:/home?success";
        }
        return "redirect:/home?error";
    }
}
