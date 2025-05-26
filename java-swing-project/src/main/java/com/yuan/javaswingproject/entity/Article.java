package com.yuan.javaswingproject.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class Article implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String title;

    @TableField(value = "abstractText")
    private String abstractText;

    @TableField(value = "contentText")
    private String contentText;

    private String author;

    @TableField(value = "createdAt")
    private LocalDateTime createdAt;

    @TableField(value = "updatedAt")
    private LocalDateTime updatedAt;
}
