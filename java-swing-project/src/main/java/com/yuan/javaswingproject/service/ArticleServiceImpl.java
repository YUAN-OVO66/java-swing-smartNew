package com.yuan.javaswingproject.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yuan.javaswingproject.entity.Article;
import com.yuan.javaswingproject.mapper.ArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl {

    @Autowired
    private ArticleMapper articleMapper;



    public Boolean addArticle(Article article) {
        int insert = articleMapper.insert(article);
        return insert != 0;
    }


    public Boolean updateArticle(Article article) {
        int update = articleMapper.updateById(article);
        return update != 0;
    }


    public Boolean deleteArticle(Integer id) {
        int delete = articleMapper.deleteById(id);
        return delete != 0;
    }


    public Article getArticleById(Integer id) {
        return articleMapper.selectById(id);
    }


    public List<Article> getArticleList(String keyWord, Integer pageNum, Integer pageSize) {
        Page<Article> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        if (keyWord != null && !keyWord.isEmpty()) {
            queryWrapper.like("title", keyWord);
        }
        IPage<Article> resultPage = articleMapper.selectPage(page, queryWrapper);
        return resultPage.getRecords();
    }

}
