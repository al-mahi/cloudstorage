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

    @Select("SELECT * FROM NOTES WHERE noteid = #{noteid} AND userid = #{userid}")
    Notes findByNoteId(int noteid, int userid);

    @Select("SELECT * FROM NOTES WHERE userid = #{userid}")
    List<Notes> findByUserId(int userid);

    @Insert(
            "INSERT INTO NOTES (notetitle, notedescription, userid) VALUES (#{note.notetitle}, #{note.notedescription}, #{userid})")
    int insert(Notes note, int userid);

    @Delete("DELETE FROM NOTES WHERE noteid = #{noteid} AND userid = #{userid}")
    int delete(int noteid, int userid);

    @Update(
            "UPDATE NOTES SET notetitle = #{note.notetitle}, notedescription = #{note.notedescription} WHERE noteid = #{note.noteid} AND userid = #{userid}")
    int update(Notes note, int userid);

}
