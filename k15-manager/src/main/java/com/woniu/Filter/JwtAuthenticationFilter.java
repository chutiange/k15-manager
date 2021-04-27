package com.woniu.Filter;

import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends AccessControlFilter {
    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {

        //请求方法
        //option 请求是一个预检请求
        HttpServletRequest request =  (HttpServletRequest)servletRequest;
        System.out.println(request.getMethod());
        if (HttpMethod.OPTIONS.toString().equals(request.getMethod())) {
            return true;
        }

        //客户端浏览器带过来的 封装到header      token
        String authorization = request.getHeader("Authorization");
        //在shiro自定义的realm  认证中去实现（校验token的合法性）
        //思路  拿到 subject 去调用login方法
        try{
            Subject subject = this.getSubject(servletRequest, servletResponse);
            //封装token
            UsernamePasswordToken token = new UsernamePasswordToken(authorization,authorization);
            subject.login(token);
        }
        catch(Exception ex){
            ex.printStackTrace();
            HttpServletResponse response =  (HttpServletResponse)servletResponse;
            response.sendRedirect("/user/unauthenticated");
            return false;
        }
        return true;
    }
}
