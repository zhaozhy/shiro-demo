package com.example.shiro.shiro.Shiro;

import com.example.shiro.shiro.domain.User;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.util.HashSet;
import java.util.Set;

public class MyShiroRealm extends AuthorizingRealm {
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = (String) principalCollection.getPrimaryPrincipal();
        Set<String> roles=GetRoles(username);
        Set<String> pows=GetPows(username);
        SimpleAuthorizationInfo authorizationInfo=new SimpleAuthorizationInfo();
        authorizationInfo.addStringPermissions(pows);
        return   authorizationInfo;
      }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String  username=authenticationToken.getPrincipal().toString();
        User  u =GetUser(username);
         if(u != null){
             SimpleAuthenticationInfo  simpleAuthenticationInfo =new SimpleAuthenticationInfo(u.getUsername(),u.getPassword(),"realmName");
           return  simpleAuthenticationInfo;
         }
         else
             return  null;

    }

    public Set<String > GetRoles(String  username)
    {
        Set<String >  roles=new HashSet<>();
        roles.add("admin");
        roles.add("user");
        return  roles;
    }

    public Set<String > GetPows(String name)
    {
        Set<String >  pows=new HashSet<>();
        pows.add("Create");
        pows.add("Update");
       // pows.add("Delete");
        return   pows;
    }


    public User GetUser(String  username)
    {
        User  u =new User();
        u.setId(1);
        u.setUsername(username);
        u.setPassword("123456");
        return  u;
    }
}
