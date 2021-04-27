package com.woniu.realm;

import com.woniu.entity.Perm;
import com.woniu.service.PermService;
import com.woniu.service.UserService;
import com.woniu.util.JwtUtil;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Author Administrator
 * @Date 2021/4/19 20:30
 */

public class JwtRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;
    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {


        String token = authenticationToken.getPrincipal().toString();

        try {
            JwtUtil.checkSign(token);
        }catch(Exception ex){
            System.out.println("jwt 解密失败。jwt 已过期，或被篡改。");
            throw new IncorrectCredentialsException(ex);
        }
        //
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(token,token,this.getName());
        return info;
    }


    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String token = principalCollection.getPrimaryPrincipal().toString();
        String username = JwtUtil.getAudience(token);
        List<String> perms = userService.findPermsByUsername(username);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermissions(perms);
        return info;
    }
}
