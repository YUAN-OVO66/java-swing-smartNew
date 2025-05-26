package com.yuan.javaswingproject.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yuan.javaswingproject.entity.Article;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ArticleMapper extends BaseMapper<Article> {
}
