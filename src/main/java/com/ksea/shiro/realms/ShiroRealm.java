package com.ksea.shiro.realms;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.realm.Realm;

/**
 * Created by ksea on 2017/6/26.
 * 如果只是认证的话只需要继承AuthenticatingRealm 并重写doGetAuthenticationInfo方法
 */
public class ShiroRealm extends  AuthenticatingRealm {


    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println(authenticationToken.getPrincipal()+"---"+authenticationToken.hashCode());
        return null;
    }
}
