package com.udacity.jwdnd.course1.cloudstorage.controller;

import java.io.IOException;
import java.sql.Blob;

import com.udacity.jwdnd.course1.cloudstorage.model.Files;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.service.FilesService;
import com.udacity.jwdnd.course1.cloudstorage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FilesController {

    @Autowired
    private FilesService filesService;

    @Autowired
    private UserService userService;

    @PostMapping("/files")
    public String saveFile(@RequestParam(name = "fileUpload") MultipartFile fileUpload, Authentication authentication)
            throws IOException, IllegalAccessException {
        User user = userService.getUser(authentication);
        if (fileUpload.isEmpty()) {
            return "redirect:/home?error";
        }
        filesService.add(fileUpload, user.getUserid());
        return "redirect:/home?success";
    }

    @GetMapping("/files/download")
    public ResponseEntity<Resource> downloadNote(@RequestParam("id") int fileid, Authentication authentication) throws IllegalAccessException {
        User user = userService.getUser(authentication);
        Files files = filesService.download(fileid, user.getUserid());
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(files.getContenttype()))
                .body(new ByteArrayResource(files.getFiledata()));
    }

    @GetMapping("/files/delete")
    public String deleteNote(@RequestParam("id") int fileid, Authentication authentication) throws IllegalAccessException {
        User user = userService.getUser(authentication);
        if (fileid > 0) {
            filesService.delete(fileid, user.getUserid());
            return "redirect:/home?success";
        }
        return "redirect:/home?error";
    }

}
