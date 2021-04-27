package com.woniu.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.woniu.entity.Teacher;
import com.woniu.service.TeacherService;
import com.woniu.util.JwtUtil;
import com.woniu.util.ResponseResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author Administrator
 * @Date 2021/4/19 20:34
 */
@RestController
@CrossOrigin
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    /**
     * 查询
     * @param pageIndex
     * @return
     */
    @GetMapping("teacher/list")
    public ResponseResult<Page<Teacher>> list(Integer pageIndex,Integer pageSize,String searchText){
        QueryWrapper queryWrapper = new QueryWrapper();
        if (!searchText.equals("")){
            queryWrapper.like("name",searchText);
        }
        Page<Teacher> pages = new Page<>(pageIndex,pageSize);
        pages = teacherService.page(pages, queryWrapper);
        return new ResponseResult<>(200,pages);
    }

    /**
     * 修改
     * @param teacher
     * @return
     */
    @PostMapping("teacher/update")
    public ResponseResult<Void> update(@RequestBody Teacher teacher){
        if(teacherService.updateById(teacher)){
            return new ResponseResult<>(200,"修改成功");
        }else{
            return new ResponseResult<>(400,"修改失败");
        }

    }
    /**
     * 添加
     * @param teacher
     * @return
     */
    @RequestMapping("teacher/add")
    public ResponseResult<Void> add(@RequestBody Teacher teacher){
        teacher.setStatus("y");
        if(teacherService.save(teacher)){
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
    @RequestMapping("teacher/delete")
    @RequiresPermissions("teacher:delete")
    public ResponseResult<Void> delete(Integer id){
        if(teacherService.removeById(id)){
            return new ResponseResult<>(200,"删除成功");
        }else{
            return new ResponseResult<>(400,"删除失败");
        }

    }
}
