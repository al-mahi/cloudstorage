package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.mappers.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Notes;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.service.NotesService;
import com.udacity.jwdnd.course1.cloudstorage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class NotesController {
    @Autowired
    private NotesService notesService;
    @Autowired
    private UserService userService;

    @PostMapping("/notes")
    public String createOrUpdateNote(@AuthenticationPrincipal Authentication authentication, Notes note) throws IllegalAccessException {
        User user = userService.getUser(authentication);
        if (note.getNoteid() > 0) {
            notesService.update(note);
        } else {
            notesService.add(note, user.getUserid());
        }
        return "redirect:result?success";
    }

    @GetMapping("/notes/delete")
    public String deleteNote(@RequestParam("id") int noteid) {
        if (noteid > 0) {
            notesService.delete(noteid);
            return "redirect:/result?success";
        }
        return "redirect:/result?error";
    }

}
