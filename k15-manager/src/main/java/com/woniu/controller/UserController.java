package com.woniu.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.woniu.entity.Teacher;
import com.woniu.entity.User;
import com.woniu.service.UserService;
import com.woniu.util.JwtUtil;
import com.woniu.util.ResponseResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @Author Administrator
 * @Date 2021/4/19 20:34
 */
@RestController
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("user/login")
    public ResponseResult<Object> login(@RequestBody User user) throws Exception {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",user.getUsername());
        User userDb = userService.getOne(queryWrapper);
        if (userDb!=null){
            if(userDb.getPassword().equals(user.getPassword())){
                return  new ResponseResult(200,JwtUtil.createSign(userDb.getUsername()));
            }else {
                return  new ResponseResult(400,"密码错误");
            }
        }
        else{
            return new ResponseResult(400,"用户不存在");
        }
    }
    @PostMapping("user/unauthenticated")
    public ResponseResult unauthenticated(){
        return ResponseResult.FORBIDDEN;
    }

    /**
     * 用户列表
     * @param pageIndex
     * @param pageSize
     * @param searchText
     * @return
     */
    @GetMapping("user/list")
    public ResponseResult<Page<User>> list(Integer pageIndex, Integer pageSize, String searchText){
        QueryWrapper queryWrapper = new QueryWrapper();
        if (!searchText.equals("")){
            queryWrapper.like("username",searchText);
        }
        Page<User> pages = new Page<>(pageIndex,pageSize);
        pages = userService.page(pages, queryWrapper);
        return new ResponseResult<>(200,pages);
    }
    /**
     * 修改
     * @param user
     * @return
     */
    @PostMapping("user/update")
    public ResponseResult<Void> update(@RequestBody User user){
        if(userService.updateById(user)){
            return new ResponseResult<>(200,"修改成功");
        }else{
            return new ResponseResult<>(400,"修改失败");
        }

    }
    /**
     * 添加
     * @param user
     * @return
     */
    @RequestMapping("user/add")
    public ResponseResult<Void> add(@RequestBody User user){
        if(userService.save(user)){
            return new ResponseResult<>(200,"添加成功");
        }else{
            return new ResponseResult<>(400,"添加失败");
        }

    }
    /**
     * 删除
     * @param
     * @return
     */
    @RequestMapping("user/delete")
    public ResponseResult<Void> delete(Integer id){
        if(userService.removeById(id)){
            return new ResponseResult<>(200,"删除成功");
        }else{
            return new ResponseResult<>(400,"删除失败");
        }

    }
}
