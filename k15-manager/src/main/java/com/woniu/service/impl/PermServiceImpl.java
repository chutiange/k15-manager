package com.woniu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.woniu.dao.PermDao;
import com.woniu.entity.Perm;
import com.woniu.service.PermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class PermServiceImpl extends ServiceImpl<PermDao, Perm> implements PermService {
    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    @Autowired
    private  PermDao permDao;
    @Override
    public List<Perm> findPermsByUsername(String username) throws JsonProcessingException {
        // 从redis中查询是否有数据
        BoundValueOperations<String,String> bound = redisTemplate.boundValueOps("list");
        Object str = bound.get();
        List<Perm> perms = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        // 没有则到mysql数据库中查询
        if(str==null){
            perms = permDao.findPermsByUsername(username);
            // 将list转为str数据
            str = mapper.writeValueAsString(perms);
            // 将查到的数据存到redis
            bound.set(str.toString());
        }else {
            // 如果redis中有数据则直接取redis中数据
            System.out.println("===============从redis缓存中获得数据===============");
            perms = mapper.readValue(str.toString(),new TypeReference<List<Perm>>(){});
        }
        return perms;
    }

    @Override
    public void deletePermsByUserId(Integer id) {
        permDao.deletePermsByUserId(id);
    }

    @Override
    public void addRight(Map<String, Integer> map) {
        permDao.addRight(map);
    }

    @Override
    public List<Integer> getPermsByUserId(Integer userId) {
        return permDao.getPermsByUserId(userId);
    }
}
