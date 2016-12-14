package com.example.service.busi;

import com.example.annotation.TimeFly;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2016/12/14 0014.
 */
@Service
public class TestService {

    @TimeFly
    public String getContent(){
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "hello";
    }
}
