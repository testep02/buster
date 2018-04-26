/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.riverainc.airdrop.security;

import com.riverainc.airdrop.models.AuthenticatedUser;
import java.util.ArrayList;
import java.util.List;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

/**
 *
 * @author testep
 */

@Component
public class RiveraADAuthenticationProvider implements AuthenticationProvider {
    public RiveraADAuthenticationProvider() {
        
    }
    
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        
        List<GrantedAuthority> grantedAuths = new ArrayList<>();
        //LDAPUtil ldap = new LDAPUtil();
        DummyAuth ldap = new DummyAuth();
        AuthenticatedUser user = ldap.authenticateUser(authentication.getName(), authentication.getCredentials().toString());
        
        if(user.getIsUserAuthenticated() && null != user.getUserGroups() && user.getUserGroups().size() > 0) {
            user.getUserGroups().stream().forEach((i) -> {
                grantedAuths.add(new SimpleGrantedAuthority(i));
            });
            
            UsernamePasswordAuthenticationToken token = 
                    new UsernamePasswordAuthenticationToken(user.getUserEmail(), 
                                                            authentication.getCredentials().toString(), 
                                                            grantedAuths);
            token.setDetails(user);
            
            return new UsernamePasswordAuthenticationToken(authentication.getName(), authentication.getCredentials(), grantedAuths);
        }        
        
        return null;
    }
    
    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
