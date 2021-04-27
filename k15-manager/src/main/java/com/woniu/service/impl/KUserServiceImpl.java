package com.woniu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.woniu.dao.KUserDao;
import com.woniu.dao.UserDao;
import com.woniu.entity.KUser;
import com.woniu.entity.User;
import com.woniu.service.KUserService;
import com.woniu.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class KUserServiceImpl extends ServiceImpl<KUserDao, KUser> implements KUserService {
}
