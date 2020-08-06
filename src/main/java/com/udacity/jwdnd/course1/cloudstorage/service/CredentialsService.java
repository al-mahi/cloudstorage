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

    public Credentials getEncryptedCredential(int credentialId, int userid) {
        return credentialsMapper.findByCredentialIdAndUserid(credentialId, userid);
    }

    public Credentials getDecryptedCredential(int credentialId, int userid) {
        return decryptPassword(credentialsMapper.findByCredentialIdAndUserid(credentialId, userid));
    }

    private Credentials decryptPassword(Credentials credential) {
        credential.setPassword(encryptionService.decryptValue(credential.getPassword(),
                credential.getKey()));
        return credential;
    }

    public int addCredential(Credentials credential, int userid) {
        return credentialsMapper.insert(encryptPassword(credential), userid);
    }

    private Credentials encryptPassword(Credentials credential) {
        String key = RandomStringUtils.random(16, true, true);
        credential.setKey(key);
        credential.setPassword(encryptionService.encryptValue(credential.getPassword(), key));
        return credential;
    }

    public int updateCredential(Credentials credential, int userid) {
        return credentialsMapper.update(encryptPassword(credential), userid);
    }

    public void deleteCredential(int credentialid, int userid) {
        credentialsMapper.delete(credentialid, userid);
    }

}
