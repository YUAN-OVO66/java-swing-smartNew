package com.yuan.javaswingproject.entity.json;

import lombok.Data; // 如果使用 Lombok

import java.util.List;

@Data
public class WeiboHotSearchResponse {
    private Boolean success;
    private Integer code;
    private String msg;
    private List<HotSearchItem> data;
}

