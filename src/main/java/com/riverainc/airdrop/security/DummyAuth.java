/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.riverainc.airdrop.security;

import com.riverainc.airdrop.models.AuthenticatedUser;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author EstepTravis
 */
public class DummyAuth {
    public AuthenticatedUser authenticateUser(String username, String password) {
        AuthenticatedUser user = new AuthenticatedUser();
        user.setUserName(username);
        user.setUserEmail("testep02@gmail.com");
        user.setIsUserAuthenticated(true);
        
        List<String> grantedAuths = new ArrayList<>();
        grantedAuths.add("E6_BUSTER_ADMIN");
        grantedAuths.add("E6_ARTIFACT_AUDITOR_TEST");
        grantedAuths.add("E6_ARTIFACT_AUDITOR_QA");
        
        user.setUserGroups(grantedAuths);
        
        return user;
    }
}
