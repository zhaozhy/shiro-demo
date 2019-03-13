package com.example.shiro.shiro.controller;

import com.sun.net.httpserver.Authenticator;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.shiro.shiro.domain.User;

import javax.jws.soap.SOAPBinding;

@RestController
public class TestController extends BaseController {

      @GetMapping("login")
      public  String  login()
      {
          UsernamePasswordToken  token=new UsernamePasswordToken("admin","123456");
          Subject subject= SecurityUtils.getSubject();
          subject.login(token);
          User u= GetUser("admin");
          subject.getSession().setAttribute("user",u);
          return "success";
      }

      @GetMapping("index")
      public String  index()
      {
          Subject  subject= SecurityUtils.getSubject();
         User u=(User) subject.getSession().getAttribute("user");
          return   "index";

      }

      public  User  GetUser(String username)
      {
          User  u =new User();
          u.setUsername(username);
          u.setId(1);
          u.setPassword("123456");
          return u;
      }
}
