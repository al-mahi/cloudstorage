package com.udacity.jwdnd.course1.cloudstorage.service;

import java.io.IOException;
import java.sql.Blob;
import java.util.List;

import com.udacity.jwdnd.course1.cloudstorage.mappers.FilesMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Files;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FilesService {
    @Autowired
    private FilesMapper filesMapper;

    public List<Files> getAll(int userid) throws Exception {
        List<Files> files = filesMapper.findAllByUserId(userid);
        if (files == null) {
            throw new Exception("There is no file for the user");
        }
        return files;
    }

    public void add(MultipartFile fileUpload, int userid) throws IOException {
        Files file = new Files();
        try {
            file.setContenttype(fileUpload.getContentType());
            file.setFiledata(fileUpload.getBytes());
            file.setFilename(fileUpload.getOriginalFilename());
            file.setFilesize(Long.toString(fileUpload.getSize()));
        } catch (IOException e) {
            throw e;
        }
        filesMapper.insert(file, userid);
    }

    public void delete(int fileid, int userid) {
        filesMapper.delete(fileid, userid);
    }

    public Files download(int fileid, int userid) {
        return filesMapper.findByFileId(fileid, userid);
    }
}
