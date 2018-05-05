package com.a10835.easywol.bean;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobUser;

/**
 * Created by 10835 on 2018/5/5.
 */

public class Devices extends BmobObject {
    private String ip;
    private String port;
    private String mac;
    private String password;
    private String subnetmaster;
    private BmobUser user;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BmobUser getUser() {
        return user;
    }

    public void setUser(BmobUser user) {
        this.user = user;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSubnetmaster() {
        return subnetmaster;
    }

    public void setSubnetmaster(String subnetmaster) {
        this.subnetmaster = subnetmaster;
    }
}
