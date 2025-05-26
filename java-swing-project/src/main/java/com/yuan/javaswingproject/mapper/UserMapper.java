package com.yuan.javaswingproject.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yuan.javaswingproject.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
