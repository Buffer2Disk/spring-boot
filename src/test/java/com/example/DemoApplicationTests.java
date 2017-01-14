package com.example;

import com.example.mapper.UserMapper;
import com.example.service.crontab.CronAccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {
    @Autowired
    UserMapper userMapper;
    @Autowired
    CronAccountService cronAccountService;

    /*@Test
    public void contextLoads() {

    }*/

    @Test
    public void  testUser(){
        /*List<User> list =  userMapper.getAllUser();
        System.out.println(list);*/
        cronAccountService.getExpireUser();

    }

}
