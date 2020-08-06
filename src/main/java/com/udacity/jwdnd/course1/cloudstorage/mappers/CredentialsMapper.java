package com.udacity.jwdnd.course1.cloudstorage.mappers;

import java.util.List;

import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface CredentialsMapper {

    @Select("SELECT * FROM CREDENTIALS WHERE userid = #{userid}")
    List<Credentials> findAllByUserid(int userid);

    @Select("SELECT * FROM CREDENTIALS WHERE credentialid = #{credentialId} AND userid = #{userid}")
    Credentials findByCredentialIdAndUserid(int credentialId, int userid);

    @Insert(
            "INSERT INTO CREDENTIALS (url, username, key, password, userid) VALUES (#{credential.url}, #{credential.username}, #{credential.key}, #{credential.password}, #{userid})")
    int insert(Credentials credential, int userid);

    @Delete("DELETE FROM CREDENTIALS WHERE credentialid = #{credentialid} AND userid = #{userid}")
    int delete(int credentialid, int userid);

    @Update(
            "UPDATE CREDENTIALS SET url = #{url}, username = #{username}, key = #{key}, password = #{password} WHERE credentialid = #{credentialid} AND userid = #{userid}")
    int update(Credentials credential, int userid);
}