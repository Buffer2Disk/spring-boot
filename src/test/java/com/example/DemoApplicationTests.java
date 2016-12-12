package com.example;

import com.example.entity.User;
import com.example.mapper.UserMapper;
import com.example.service.crontab.CronService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {
    @Autowired
    UserMapper userMapper;
    @Autowired
    CronService cronService;

    /*@Test
    public void contextLoads() {

    }*/

    @Test
    public void  testUser(){
        /*List<User> list =  userMapper.getAllUser();
        System.out.println(list);*/
        //cronService.getTransferUser();

    }

}
