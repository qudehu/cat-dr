package com.cat.cust.dao;

import java.util.Map;

public interface CustomerDao {
    // 判断是否注册
    public long hasRegistered(Map params);

    // 用户注册
    public int registerCustomer(Map params);

    //通过openId获取用户ID
    public String getCustomerId(String openId);

}
