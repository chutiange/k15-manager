package com.woniu.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.woniu.entity.Perm;
import com.woniu.entity.Teacher;
import com.woniu.service.PermService;
import com.woniu.service.TeacherService;
import com.woniu.util.JwtUtil;
import com.woniu.util.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author Administrator
 * @Date 2021/4/19 20:34
 */
@RestController
@CrossOrigin
public class PermController {
    @Autowired
    private PermService permService;
    // 根据用户名查出菜单列表
    @RequestMapping("perm/menu")
    public ResponseResult<List<Perm>> findPermsByUsername(String token) throws JsonProcessingException {
        // 找出权限根节点列表
        String username = JwtUtil.getAudience(token);
        List<Perm> permList = permService.findPermsByUsername(username);
        List<Perm> rootList = new ArrayList<>();
        for (Perm perm:permList){
            if (perm.getParentid()==null){
                rootList.add(perm);
            }
        }
        List<Perm> menuList = new ArrayList<>();
        // 找出每个根节点的子节点
        for(Perm perm:rootList){
            List<Perm> children = new ArrayList<>();
            for (Perm item : permList){
                if(item.getParentid()!=null && item.getParentid().equals(perm.getId())){
                    children.add(item);
                }
            }
            perm.setChildren(children);
            menuList.add(perm);
        }
        // 返回根节点集合
        return new ResponseResult<>(menuList);
    }
    // 查出所有权限
    @RequestMapping("perm/all")
    public ResponseResult<List<Perm>> findAllPerms(){
        // 找出权限根节点列表
        List<Perm> permList = permService.list(); // 所有权限
        List<Perm> rootList = new ArrayList<>();
        for (Perm perm:permList){
            if (perm.getParentid()==null){
                rootList.add(perm);
            }
        }
        // 找出所有根节点的子元素节点
        for (Perm perm:rootList){
            perm.setChildren(getChildrenNode(perm.getId(),permList));
        }
        return new ResponseResult<>(200,rootList);

    }
    // 获取子孙元素
    public List<Perm> getChildrenNode(Integer id,List<Perm> list){
        List<Perm> childList = new ArrayList<>();
        for (Perm perm:list){
            if (perm.getParentid()!=null&&perm.getParentid().equals(id)){
                childList.add(perm);
            }
        }
        for (Perm item : childList){
            item.setChildren(getChildrenNode(item.getId(),list));
        }
        if (childList.size()==0){
            return null;
        }
        return childList;
    }
    // 添加权限
    @RequestMapping("perm/add")
    public ResponseResult addRights(String keys,Integer id){
        // 根据id删除原有权限
        String[] strings = keys.split(",");
        permService.deletePermsByUserId(id);
        for (int i = 0; i < strings.length; i++) {
            Map<String,Integer> map = new HashMap<>();
            map.put("userId",id);
            map.put("permId",Integer.parseInt(strings[i]));
            permService.addRight(map);
        }
        return ResponseResult.SUCCESS;
    }
    // 根据用户id查权限
    @RequestMapping("perm/userId")
    public ResponseResult getPermsByUserId(Integer userId){
        return new ResponseResult(permService.getPermsByUserId(userId));
    }
}
