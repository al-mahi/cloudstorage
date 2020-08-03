package com.udacity.jwdnd.course1.cloudstorage.service;

import com.udacity.jwdnd.course1.cloudstorage.mappers.CredentialsMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CredentialsService {

    @Autowired
    private CredentialsMapper credentialsMapper;

    @Autowired
    private EncryptionService encryptionService;

    public List<Credentials> getAllCredentials(int userid) throws Exception {
        List<Credentials> credentials = credentialsMapper.findAllByUserid(userid);
        if (credentials == null) {
            throw new Exception();
        }
        return credentials.stream().map(this::decryptPassword).collect(Collectors.toList());
    }

    private Credentials decryptPassword(Credentials credential) {
        credential.setPassword(encryptionService.decryptValue(credential.getPassword(),
                credential.getKey()));
        return credential;
    }

    public void addCredential(Credentials credential, int userid) {
        credentialsMapper.insert(encryptPassword(credential), userid);
    }

    private Credentials encryptPassword(Credentials credential) {
        String key = RandomStringUtils.random(16, true, true);
        credential.setKey(key);
        credential.setPassword(encryptionService.encryptValue(credential.getPassword(), key));
        return credential;
    }

    public void updateCredential(Credentials credential) {
        credentialsMapper.update(encryptPassword(credential));
    }

    public void deleteCredential(int credentialid) {
        credentialsMapper.delete(credentialid);
    }

}
