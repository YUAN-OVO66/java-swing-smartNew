package com.yuan.javaswingproject.service;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson2.JSON;
import com.yuan.javaswingproject.entity.json.HotSearchItem;
import com.yuan.javaswingproject.entity.json.WeiboHotSearchResponse;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class HotServiceImpl{

    public List<HotSearchItem> getHotSearch(String type) {
        //type:weibo,baidu,zhihu,csdn,toutiao
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("access-key", "");
        paramMap.put("secret-key", "");
        String url = "https://www.coderutil.com/api/resou/v1/" + type;
        String result= HttpUtil.get(url, paramMap);
        WeiboHotSearchResponse response = JSON.parseObject(String.valueOf(result), WeiboHotSearchResponse.class);
        return response.getData();
    }
}
