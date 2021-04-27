package com.woniu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.woniu.entity.User;

import java.util.List;

public interface UserService extends IService<User> {
    public List<String> findPermsByUsername(String username);
}
