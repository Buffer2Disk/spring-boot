package com.example.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.entity.User;
import com.example.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by Administrator on 2016/12/7 0007.
 */

@Controller
@RequestMapping("/user")
public class TestController {
    @Autowired
    UserMapper userMapper;

    @RequestMapping("/test")
    public String view() {
        List<User> users = userMapper.getAllUser();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("users",users);
        return "hello";
    }

    //public static void main(String[] args) {
    //    SpringApplication.run(UserController.class);
    //}

}
