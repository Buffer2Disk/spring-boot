package com.example.controller;

import com.example.mapper.UserMapper;
import com.example.service.busi.TestService;
import com.example.service.crontab.CronAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2016/12/7 0007.
 */

@Controller
public class MailController {
    @Autowired
    UserMapper userMapper;
    @Autowired
    CronAccountService cronAccountService;

    @RequestMapping(value = "/mail/send",method = RequestMethod.GET)
    @ResponseBody
    public void view(@RequestParam(required = false) String id) {
        cronAccountService.getTransferUser();
        cronAccountService.getExpireUser();
        cronAccountService.getExipreUsingUser();


    }

}
