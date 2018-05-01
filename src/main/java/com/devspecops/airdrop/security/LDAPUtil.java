/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devspecops.airdrop.security;

/**
 *
 * @author testep
 */
import com.devspecops.airdrop.models.AuthenticatedUser;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Iterator;
import javax.naming.Context;
import javax.naming.AuthenticationException;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import static javax.naming.directory.SearchControls.SUBTREE_SCOPE;

//import org.acegisecurity.AuthenticationException;
import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.GrantedAuthorityImpl;

public class LDAPUtil {
    private static final String domainName = "rcg.local:389";
    private static final String userDomain = "rcg.local";
    private static final String serverName = "zola";
    
    public LDAPUtil() {
        
    }
    
    public AuthenticatedUser authenticateUser(String username, String password) {
        AuthenticatedUser user = new AuthenticatedUser();
        
        Hashtable props = new Hashtable();
        String principalName = username + "@" + userDomain;
        props.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        props.put(Context.PROVIDER_URL, "ldap://10.0.128.11:389");
        props.put(Context.SECURITY_AUTHENTICATION, "simple");
        props.put(Context.SECURITY_PRINCIPAL, principalName);
        props.put(Context.SECURITY_CREDENTIALS, password);
        DirContext context;
        
        try {
            //context = LdapCtxFactory.getLdapCtxInstance("ldap://10.0.128.11:389/", props);
            context = new InitialDirContext(props);
            SearchControls searchControl = new SearchControls();
            searchControl.setSearchScope(SUBTREE_SCOPE);
            NamingEnumeration<SearchResult> renum = context.search(toDC(userDomain), 
                    "(& (userPrincipalName=" + principalName + ")(objectClass=user))", searchControl);
            
            if(!renum.hasMore()) {
                System.out.println("Cannot find user information for " + username);
            }
            
            SearchResult result = renum.next();
            
            List<GrantedAuthority> groups = new ArrayList<>();
            List<String> userGroups = new ArrayList<>();
            Attribute memberOf = result.getAttributes().get("memberOf");
            Attribute nameAtt = result.getAttributes().get("name");
            Attribute emailAtt = result.getAttributes().get("mail");
            
            if(memberOf != null) {
                for(int i = 0; i < memberOf.size(); i++) {
                    Attributes atts = context.getAttributes(memberOf.get(i).toString(), new String[] {"CN"});
                    Attribute att = atts.get("CN");
                    groups.add(new GrantedAuthorityImpl(att.get().toString()));
                    userGroups.add(att.get().toString());
                }
            }
            
            context.close();
            
            user.setUserName(nameAtt.get(0).toString());
            user.setUserEmail(emailAtt.get(0).toString());
            user.setUserGroups(userGroups);
            user.setIsUserAuthenticated(true);
            
            System.out.println("User name is: " + nameAtt.get(0).toString());
            System.out.println("User belongs to: ");
            
            Iterator ig = groups.iterator();
            
            while(ig.hasNext()) {
                System.out.println("    " + ig.next().toString());
            }
            
            System.out.println("Authentication with AD succeeded.");
        } catch(AuthenticationException ae) {
            System.out.println("There was an error authenticating with AD:");
            System.out.println(ae.getMessage());
            user.setIsUserAuthenticated(false);
        } catch(NamingException ne) {
            System.out.println("Naming Exception:");
            System.out.println(ne.getMessage());
            System.out.println(ne.getStackTrace());
            System.out.println(ne.getRootCause());
            System.out.println(ne.getCause());
            user.setIsUserAuthenticated(false);
        }
        
        return user;
    }
    
    private String toDC(String domainName) {
        StringBuilder buf = new StringBuilder();
        
        for(String token : domainName.split("\\.")) {
            if(token.length() == 0) {
                continue;
            }
            if(buf.length() > 0) {
                buf.append(",");
            }
            
            buf.append("DC=").append(token);
        }
        
        return buf.toString();
    }
}
