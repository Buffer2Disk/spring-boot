package com.example.entity;

import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.Date;

/**
 * Created by Administrator on 2016/12/7 0007.
 */
public class User {
    private Integer uid;
    private String user_name;
    private String email;
    private Integer t;
    private BigInteger transfer_enable;
    private BigInteger u;
    private BigInteger d;
    private Integer port;
    private Date expire;

    public Date getExpire() {
        return expire;
    }

    public void setExpire(Date expire) {
        this.expire = expire;
    }

    public Integer getUid() {
        return uid;

    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getT() {
        return t;
    }

    public void setT(Integer t) {
        this.t = t;
    }

    public BigInteger getTransfer_enable() {
        return transfer_enable;
    }

    public void setTransfer_enable(BigInteger transfer_enable) {
        this.transfer_enable = transfer_enable;
    }

    public BigInteger getU() {
        return u;
    }

    public void setU(BigInteger u) {
        this.u = u;
    }

    public BigInteger getD() {
        return d;
    }

    public void setD(BigInteger d) {
        this.d = d;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

}
