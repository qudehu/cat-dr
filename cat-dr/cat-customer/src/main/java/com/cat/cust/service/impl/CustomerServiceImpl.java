package com.cat.cust.service.impl;

import cn.hutool.core.map.MapUtil;
import com.cat.cust.dao.CustomerDao;
import com.cat.cust.service.CustomerService;
import com.cat.util.WeixinUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Resource
    private CustomerDao customerDao;

    @Resource
    private WeixinUtil weixinUtil;


    /**
     * 通过微信唯一标识openId，判断用户是否存在，存在则返回报错，不存在则注册
     *
     * @param params
     * @return
     */
    @Override
    public String registerCustomer(Map params) {
        //通过临时code获取用户唯一凭证openId
        String code = MapUtil.getStr(params, "code");
        String openId = weixinUtil.getOpenId(code);
        params.put("openId", openId);
        //判断用户是否注册
        if (customerDao.hasRegistered(params) != 0) {
            throw new RuntimeException("该用户已注册");
        }

        //注册新用户
        customerDao.registerCustomer(params);
        //返回新用户id
        return customerDao.getCustomerId(openId);
    }

}
