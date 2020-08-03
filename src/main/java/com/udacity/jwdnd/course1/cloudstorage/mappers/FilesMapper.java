package com.udacity.jwdnd.course1.cloudstorage.mappers;

import com.udacity.jwdnd.course1.cloudstorage.model.Files;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
@Mapper
public interface FilesMapper {

    @Select("SELECT * FROM FILES")
    List<Files> findAll();

    @Select("SELECT * FROM FILES WHERE fileid = #{fileid}")
    Files findByFileId(int fileid);

    @Select("SELECT * FROM FILES WHERE userid = #{userid}")
    List<Files> findAllByUserId(int userid);

    @Insert(
            "INSERT INTO FILES (filename, contenttype, filesize, filedata, userid) VALUES (#{file.filename}, #{file.contenttype}, #{file.filesize}, #{file.filedata}, #{userid})")
    int insert(Files file, int userid);

    @Delete("DELETE FROM FILES WHERE fileid = #{fileid}")
    int delete(int fileid);

}