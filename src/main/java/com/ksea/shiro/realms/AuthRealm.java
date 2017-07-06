package com.ksea.shiro.realms;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.sql.Connection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by ksea on 2017/6/26.
 * 授权realm
 * -
 */
public class AuthRealm extends AuthorizingRealm {


    @Override //用于认证
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        System.out.println("--》》AuthenticationInfo:doGetAuthenticationInfo");
        //1:把AuthenticationToken 转换成UsernamePasswordToken
        UsernamePasswordToken upToken = (UsernamePasswordToken) authenticationToken;

        //2:从UsernamePasswordToken中获取username
        String username = upToken.getUsername();
        //username如果为空的话是无效的
        if (StringUtils.isBlank(username)) {
            throw new AccountException("Null usernames are not allowed by this realm.");
        }

        //3:调用数据库的方法，从数据库中查询username对应的用户记录
        //  Connection connection;
        String passsword;
        //    connection = dataSource.getConnection();
        //  passsword = getPasswordByUsername(connection, username);


        //这里需要从数据库中查询信息，这里就模拟就补查询了
        System.out.println("从数据库中获取到username:[" + username + "]所对应的信息。");

        //4:若用户不存在 则可以抛出UnknowAccountException异常
        if ("unknown".equals(username)) {
            //这里仅限于模拟
            throw new UnknownAccountException("用户不存在!");
        }

        //5:根据用户信息的情况，决定是否需要抛出其它异常 AuthenticationException异常
        if ("locked".equals(username)) {
            throw new LockedAccountException("用户被锁定!");
        }
        //6:根据用户的情况，来构建AuthenticationInfo对象并返回,通常使用的是SimpleAuthenticationInfo
        //以下信息是从数据库查询中获取
        //1):principal认证的实体信息，可以是username，也可以是数据库表对应的用户的实体类对象
        Object principal = username;
        //2):credentials 密码
        Object credentials = "123456";
        Object md5_credentials = md5(credentials);
        //3):realmName当前realm对象的name,调用父类的getName()方法即可
        String realmName = getName();
        //  SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(principal, md5_credentials, realmName);


        //此处通过盐值进行加密,指定盐值
        ByteSource credentialsSalt = ByteSource.Util.bytes(username);
        //盐值一般都是唯一的字符串，我们通常用用户名作为盐值

        //根据盐值加密后的密码
        credentials = md5(credentials, credentialsSalt);


        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(principal, credentials, credentialsSalt, realmName);

        //shiro中的密码比对主要通过 AuthenticatingRealm 类中的 CredentialsMatcher属性来进行比对的
        return simpleAuthenticationInfo;
    }

    //根据
    private String  getPasswordByUsername(Connection connection, String username) {
        return null;
    }

    //shiro SHA-256加密
    public Object md5(Object pwd) {
        //指定加密算法
        String  hashAlgorithmName  = "SHA-256";
        //指定要加密的密码
        Object credentials = pwd;
        //指定加密盐值
        Object salt = null;
        //指定加密的次数
        int hashIterations = 1024;
        SimpleHash simpleHash = new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations);
        System.out.println(simpleHash);

        return simpleHash;
    }


    //shiro SHA-256盐值加密
    public Object md5(Object pwd, Object salt_) {
        //指定加密算法
        String hashAlgorithmName = "SHA-256";
        //指定要加密的密码
        Object credentials = pwd;
        //指定加密盐值
        Object salt = salt_;
        //指定加密的次数
        int hashIterations = 1024;
        SimpleHash simpleHash = new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations);
        System.out.println(simpleHash);

        return simpleHash;
    }

    @Override //用于授权
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        Object principal = principalCollection.getPrimaryPrincipal();
        Set<String> roles= new HashSet<>();
        //添加user权限
        roles.add("user");

        if ("admin".equals(principal)){
            roles.add("admin");//如果登录用户是admin就添加admin权限
        }

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roles);

        return info;
    }
}
