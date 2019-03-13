package com.example.shiro.shiro.controller;

import com.example.shiro.shiro.domain.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("authc")
public class AuthController extends BaseController {

    @GetMapping("index")
    public  String   index()
    {
        Subject  subject= SecurityUtils.getSubject();
        User u=(User) subject.getSession().getAttribute("user");
        return   u.getPassword();
    }

    @GetMapping("admin")
    public  String   admin()
    {
        Subject  subject= SecurityUtils.getSubject();
        User u=(User) subject.getSession().getAttribute("user");
        return   u.getId().toString();
    }

    @GetMapping("cau")
    public  String   cau()
    {
        Subject  subject= SecurityUtils.getSubject();
        User u=(User) subject.getSession().getAttribute("user");
        return   u.getId().toString();
    }

    @GetMapping("buhuizijizuoa")
    @RequiresPermissions("Delete")
    public  String   buhuizijizuoa()
    {

        Subject  subject= SecurityUtils.getSubject();
        User u=(User) subject.getSession().getAttribute("user");
        return   u.getId().toString();
    }



}
