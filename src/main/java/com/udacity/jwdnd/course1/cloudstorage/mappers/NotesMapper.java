package com.udacity.jwdnd.course1.cloudstorage.mappers;

import com.udacity.jwdnd.course1.cloudstorage.model.Notes;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface NotesMapper {

    @Select("SELECT * FROM NOTES")
    List<Notes> findAll();

    @Select("SELECT * FROM NOTES WHERE noteid = #{noteid}")
    Notes findByNoteId(int noteid);

    @Select("SELECT * FROM NOTES WHERE userid = #{userid}")
    List<Notes> findByUserId(int userid);

    @Insert(
            "INSERT INTO NOTES (notetitle, notedescription, userid) VALUES (#{note.notetitle}, #{note.notedescription}, #{userid})")
    int insert(Notes note, int userid);

    @Delete("DELETE FROM NOTES WHERE noteid = #{noteid}")
    int delete(int noteid);

    @Update(
            "UPDATE NOTES SET notetitle = #{notetitle}, notedescription = #{notedescription} WHERE noteid = #{noteid}")
    int update(Notes note);

}
