package com.yuan.javaswingproject.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yuan.javaswingproject.entity.User;
import com.yuan.javaswingproject.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl {

    @Autowired
    private UserMapper userMapper;

    public Boolean login(String username, String password) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username).eq("password", password);
        return userMapper.selectOne(queryWrapper) != null;
    }


    public Boolean register(String username, String nickname,String password, String rePassword) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        if (userMapper.selectOne(queryWrapper) != null) {
            return false;
        }
        if (!password.equals(rePassword)) {
            return false;
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setNickname(nickname);
        int insert = userMapper.insert(user);
        return insert != 0;
    }

    //修改密码
    public Boolean updatePassword(String username, String password, String rePassword) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        if (userMapper.selectOne(queryWrapper) == null) {
            return false;
        }
        if (!password.equals(rePassword)) {
            return false;
        }
        User user = new User();
        user.setPassword(password);
        int update = userMapper.update(user, queryWrapper);
        return update !=0;
    }

    //根据用户名查询用户
    public User findByUsername(String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        return userMapper.selectOne(queryWrapper);
    }
}
