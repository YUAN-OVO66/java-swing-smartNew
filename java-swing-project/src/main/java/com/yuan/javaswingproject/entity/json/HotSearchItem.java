package com.yuan.javaswingproject.entity.json;

import lombok.Data;

@Data
public class HotSearchItem {
    private Integer rank;
    private String keyword;
    private String url;
    private Long hotValue; // 注意：第一条数据没有这个字段
    private Boolean isHot;
    private Boolean isNew;
    private Boolean isBoil;
}
