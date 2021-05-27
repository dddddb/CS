package com.ldb.controller;

import com.ldb.pojo.ResultInfo;
import com.ldb.pojo.User;
import com.ldb.service.UserService;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class UserController {

    @Resource
    private UserService userService;
    @RequestMapping("/register")
    @ResponseBody
    public ResultInfo register(HttpServletRequest request){
        ResultInfo info = new ResultInfo();
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = new User();
        user.setUsername(username);
        user.setPassword(Integer.parseInt(password));
        boolean b = userService.addUser(user);
        if (b){
            info.setFlag(true);
            info.setData(user);
        }
        else {
            info.setFlag(false);
            info.setErrorMsg("用户名已存在");
        }

        return info;
    }

    @PostMapping("/login")
    @ResponseBody
    public ResultInfo login(HttpServletRequest request, HttpServletResponse response){
        ResultInfo info = new ResultInfo();
        User user= new User();
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        user.setUsername(username);
        user.setPassword(Integer.parseInt(password));
        User u = userService.query(user);
        if (u!=null){

            info.setFlag(true);
            info.setData(u);
            Cookie cookie = new Cookie("username",username);
            cookie.setPath("/");
            cookie.setMaxAge(60*60);
            response.addCookie(cookie);
        }else{
            info.setFlag(false);
            info.setErrorMsg("用户名或密码错误");
        }
        return info;
    }
    @RequestMapping("/exit")
    @ResponseBody
    public ResultInfo exit(HttpServletRequest request,HttpServletResponse response){
        Cookie cookie = new Cookie("username","");
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
        ResultInfo info = new ResultInfo();
        info.setFlag(false);
        info.setData(null);
        return info;
    }

    @RequestMapping("/check")
    @ResponseBody
    public ResultInfo check(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        ResultInfo info = new ResultInfo();
        if (cookies!=null||cookies.length>0) {
            for (Cookie cookie : cookies) {
                String name = cookie.getName();
                if ("username".equals(name)) {

                    info.setFlag(true);
                    info.setData(name);
                }
            }
        }else {
            info.setFlag(false);
            info.setErrorMsg("未登录");
        }
       return info;
    }

}
