package com.udacity.jwdnd.course1.cloudstorage.service;

import com.udacity.jwdnd.course1.cloudstorage.mappers.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;

@Service
public class UserService {

    private final UserMapper userMapper;
    private final HashService hashService;

    public UserService(UserMapper userMapper, HashService hashService) {
        this.userMapper = userMapper;
        this.hashService = hashService;
    }

    public boolean isUsernameAvailable(String username) {
        return userMapper.findByUsername(username) == null;
    }

    public int create(User user) {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        String encodedSalt = Base64.getEncoder().encodeToString(salt);
        String hashedPassword = hashService.getHashedValue(user.getPassword(), encodedSalt);
        user.setSalt(encodedSalt);
        user.setPassword(hashedPassword);
        user.setUserid(null);
        return userMapper.insert(user);
    }

    public User getUser(String username) {
        return userMapper.findByUsername(username);
    }

    public User getUser(Authentication authentication) throws IllegalAccessException {
        User user = null;
        if (authentication.isAuthenticated()) {
            user = userMapper.findByUsername(authentication.getName());
        }
        else {
            throw new IllegalAccessException("User is not authenticated");
        }

        if (user == null) {
            throw new UsernameNotFoundException("User name not");
        }
        return user;
    }
}
