package com.udacity.jwdnd.course1.cloudstorage.service;

import java.util.List;
import com.udacity.jwdnd.course1.cloudstorage.mappers.NotesMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Notes;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotesService {
    @Autowired
    private NotesMapper notesMapper;

    public List<Notes> getAll(int userid) throws Exception {
        List<Notes> notes = notesMapper.findByUserId(userid);
        if (notes == null) {
            throw new Exception();
        }
        return notes;
    }

    public void add(Notes note, int userid) {
        notesMapper.insert(note, userid);
    }

    public void update(Notes note) {
        notesMapper.update(note);
    }

    public void delete(int noteid) {
        notesMapper.delete(noteid);
    }

}
