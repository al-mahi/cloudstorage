package com.udacity.jwdnd.course1.cloudstorage.mappers;

import java.util.List;

import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface CredentialsMapper {

    @Select("SELECT * FROM CREDENTIALS")
    List<Credentials> findAll();

    @Select("SELECT * FROM CREDENTIALS WHERE credentialid = #{credentialid}")
    Credentials findByCredentialId(int credentialid);

    @Select("SELECT * FROM CREDENTIALS WHERE userid = #{userid}")
    List<Credentials> findAllByUserid(int userid);

    @Insert(
            "INSERT INTO CREDENTIALS (url, username, key, password, userid) VALUES (#{credential.url}, #{credential.username}, #{credential.key}, #{credential.password}, #{userid})")
    int insert(Credentials credential, int userid);

    @Delete("DELETE FROM CREDENTIALS WHERE credentialid = #{credentialid}")
    int delete(int credentialid);

    @Update(
            "UPDATE CREDENTIALS SET url = #{url}, username = #{username}, key = #{key}, password = #{password} WHERE credentialid = #{credentialid}")
    int update(Credentials credential);

}