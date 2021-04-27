package com.woniu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.woniu.dao.UserDao;
import com.woniu.entity.User;
import com.woniu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {
    @Autowired
    private  UserDao userDao;
    @Override
    public List<String> findPermsByUsername(String username) {
        return userDao.findPermsByUsername(username);
    }
}
