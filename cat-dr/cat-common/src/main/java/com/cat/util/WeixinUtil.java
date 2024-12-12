package com.cat.util;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class WeixinUtil {

    @Value("${wx.app-id}")
    private String appid;

    @Value("${wx.app-secret}")
    private String secret;

    @Value("${wx.session-url}")
    private String sessionUrl;


    /**
     * 通古临时code获取登录凭证和openId
     *
     * @param code
     * @return
     */
    public String getOpenId(String code) {
        //String url = "https://api.weixin.qq.com/sns/jscode2session";
        Map<String, Object> params = new HashMap<>();
        params.put("appid", appid);//小程序appid
        params.put("secret", secret);//小程序密钥
        params.put("js_code", code);//临时code
        params.put("grant_type", "authorization_code");

        String reponse = HttpUtil.post(sessionUrl, params);
        JSONObject json = JSONUtil.parseObj(reponse);
        String openId = json.getStr("openid");
        if (openId == null || openId.isEmpty()) {
            throw new RuntimeException("获取登录凭证失败！");
        }
        return openId;
    }
}
