package com.woniu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.woniu.entity.Perm;
import com.woniu.entity.User;

import java.util.List;
import java.util.Map;

public interface PermService extends IService<Perm> {
    public List<Perm> findPermsByUsername(String username) throws JsonProcessingException;

    public void deletePermsByUserId(Integer id);

    public void addRight(Map<String,Integer> map);
    public List<Integer> getPermsByUserId(Integer userId);
}
