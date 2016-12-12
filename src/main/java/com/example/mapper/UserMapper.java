package com.example.mapper;

import com.example.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/7 0007.
 */
@Repository
public interface UserMapper {

    @Select("select * from user")
    List<User> getAllUser();

    /*syll 剩余流量，zll 总流量*/
    @Select("SELECT round((transfer_enable-(u+d))/1073741824,2) as 'syll',round(transfer_enable/1073741824,2) as 'zll',(u + d)/transfer_enable as 'percentage' ,a.* FROM user as a WHERE (u + d)/transfer_enable > #{flag} AND DATE(CURRENT_DATE()) < expire order by percentage desc")
    List<Map> getTransferLimitUser(Double flag);
}
