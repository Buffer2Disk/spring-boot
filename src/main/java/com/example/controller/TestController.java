package com.example.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.entity.User;
import com.example.mapper.UserMapper;
import com.example.service.busi.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Administrator on 2016/12/7 0007.
 */

@Controller
public class TestController {
    @Autowired
    UserMapper userMapper;
    @Autowired
    TestService testService;

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    @ResponseBody
    public String view(@RequestParam(required = false) String id) {
        /*List<User> users = userMapper.getAllUser();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("users",users);*/
        String result = testService.getContent();
        return result;
    }

    //public static void main(String[] args) {
    //    SpringApplication.run(UserController.class);
    //}

}
