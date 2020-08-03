package com.udacity.jwdnd.course1.cloudstorage.mappers;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import java.util.List;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserMapper {

    @Select("SELECT * FROM USERS")
    List<User> findAll();

    @Select("SELECT * FROM USERS WHERE userid = #{userid}")
    User findByUserid(int userid);

    @Select("SELECT * FROM USERS WHERE username = #{username}")
    User findByUsername(String username);

    @Insert(
            "INSERT INTO USERS (username, password, salt, firstname, lastname) VALUES (#{username}, #{password}, #{salt}, #{firstname}, #{lastname})")
    int insert(User user);

    @Delete("DELETE FROM USERS WHERE username = #{username}")
    int delete(String username);

    @Update(
            "UPDATE USERS SET username = #{username}, password = #{password}, salt = #{salt}, firstname = #{firstname}, lastname = #{lastname} WHERE userid = #{userid}")
    int update(User user);

}