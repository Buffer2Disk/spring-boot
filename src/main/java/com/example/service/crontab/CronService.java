package com.example.service.crontab;

import com.example.entity.User;
import com.example.mapper.UserMapper;
import com.example.service.emailService.EmailSender;
import com.example.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Administrator on 2016/12/11 0011.
 */
@Configuration
public class CronService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    EmailSender emailSender;

    private static final Logger logger = LoggerFactory.getLogger(CronService.class);

    private Integer tokb = 1024;
    private Integer tomb = 1024 * 1024;
    private Integer togb = tomb * 1024;


    /*获取流量超过阀值的用户*/
    @Scheduled(cron = "0 45 20 * * ?")
    public void getTransferUser() {
        System.out.println("<-----流量超过阀值定时任务执行,时间:" + DateUtils.getDate() + "----->");
        String day = (DateUtils.getDate().split("-"))[2];//今天
        Double flag = 1.0 * Integer.valueOf(day) / 30;//超出的比例指标，随着每天时间变化
        flag = flag > 0.9 ? 0.9 : flag;
        List<Map> list = userMapper.getTransferLimitUser(flag);

        List<HashMap> maps = new ArrayList<>();
        for (Map<String, Object> data : list) {
            BigDecimal percentage = ((BigDecimal) data.get("percentage")).multiply(new BigDecimal(100));
            DecimalFormat df = new DecimalFormat(".##");

            HashMap map = new HashMap();
            map.put("name", String.valueOf(data.get("user_name")));
            map.put("email", String.valueOf(data.get("email")));
            map.put("zll", String.valueOf(data.get("zll")));
            map.put("syll", String.valueOf(data.get("syll")));
            map.put("percentage", df.format(percentage) + "%");
            map.put("expire", DateUtils.formatDate((Date) data.get("expire"), "yyyy-MM-dd"));

            DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Long timestamp = Long.parseLong(data.get("t").toString()) * 1000;
            String lastUseTime = sdf.format(new Date(timestamp));
            map.put("lastUseTime", lastUseTime);

            maps.add(map);
        }
        Map model = new HashMap();
        model.put("date", DateUtils.getDate());
        model.put("list", maps);
        emailSender.sendSimpleMail(model, "/templates/todayTransferInfo.vm", "今日流量超过百分比用户数据", new String[]{"604542720@qq.com"});
    }


    /*获取明天到期的用户*/
    @Scheduled(cron = "0 55 20 * * ?")
    public void getExpireUser() {
        System.out.println("<-----获取明天到期定时任务执行，时间:" + DateUtils.getDate() + "----->");
        List<User> list = userMapper.getAllUser();
        List<HashMap> maps = new ArrayList<>();
        String tomorrow = DateUtils.getExactDate(+1);
        for (User user : list) {
            String expire = DateUtils.formatDate(user.getExpire(), "yyyy-MM-dd");

            DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Long timestamp = Long.parseLong(String.valueOf(user.getT())) * 1000;
            String lastUseTime = sdf.format(new Date(timestamp));

            if (tomorrow.equals(expire)) {
                DecimalFormat df = new DecimalFormat("#.###");
                HashMap map = new HashMap();
                map.put("name", user.getUser_name());
                map.put("email", user.getEmail());
                map.put("expire", expire);
                map.put("lastUseTime", lastUseTime);
                BigDecimal b1 = new BigDecimal(user.getD().add(user.getU()));
                BigDecimal gb1 = b1.divide(new BigDecimal(togb));
                map.put("hasUsed", df.format(gb1));
                BigDecimal b = new BigDecimal(user.getTransfer_enable());
                BigDecimal gb = b.divide(new BigDecimal(togb));
                map.put("transferEnable", df.format(gb));
                maps.add(map);
            }

        }
        Map model = new HashMap();
        model.put("date", DateUtils.getDate());
        model.put("list", maps);
        emailSender.sendSimpleMail(model, "/templates/tomorrowExpireInfo.vm", "明日到期用户", new String[]{"604542720@qq.com"});
    }

    //获取已经到期但是仍然在用的用户
    @Scheduled(cron = "0 0 21 * * ?")
    public void getExipreUsingUser() {
        List<User> list = userMapper.getExpireUsingUser();
        List<HashMap> maps = new ArrayList<>();
        for(User user : list){
            String expire = DateUtils.formatDate(user.getExpire(), "yyyy-MM-dd");

            DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Long timestamp = Long.parseLong(String.valueOf(user.getT())) * 1000;
            String lastUseTime = sdf.format(new Date(timestamp));
            HashMap map = new HashMap();
            map.put("name", user.getUser_name());
            map.put("email", user.getEmail());
            map.put("expire", expire);
            map.put("lastUseTime", lastUseTime);
            BigDecimal b = new BigDecimal(user.getTransfer_enable());
            BigDecimal gb = b.divide(new BigDecimal(togb));
            DecimalFormat df = new DecimalFormat("#.##");
            map.put("transferEnable", df.format(gb));
            maps.add(map);
        }


        Map model = new HashMap();
        model.put("date", DateUtils.getDate());
        model.put("list", maps);
        emailSender.sendSimpleMail(model, "/templates/expireUserInfo.vm", "到期仍然在使用的用户", new String[]{"604542720@qq.com"});
    }
}
