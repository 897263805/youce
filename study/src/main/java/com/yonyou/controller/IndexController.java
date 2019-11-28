package com.yonyou.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.yonyou.mapping.UserMapper;
import com.yonyou.pojo.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by chenjie on 2019/11/9 17:13
 **/ 
@Controller
public class IndexController {
    @Autowired
    private UserMapper userMapper;
    @GetMapping("/不能vb")
    public String index(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if(cookies != null) {
        	for (Cookie cookie : cookies) {//快�?�创建for循环 cookie.for会自动补�??
                if(cookie.getName().equals("token")){
                    String token=cookie.getValue();
                    User user=userMapper.findByToken(token);
                    if(user!=null){
                        request.getSession().setAttribute("user",user);
                    }
                    break;
                }
            }
        }
        
        return "upload";
    }
}
