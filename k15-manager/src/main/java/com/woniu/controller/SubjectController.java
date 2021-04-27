package com.woniu.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.woniu.entity.Subject;
import com.woniu.entity.Teacher;
import com.woniu.service.SubjectService;
import com.woniu.service.TeacherService;
import com.woniu.util.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author Administrator
 * @Date 2021/4/19 20:34
 */
@RestController
@CrossOrigin
public class SubjectController {
    @Autowired
    private SubjectService subjectService;

    /**
     * 查询
     * @param
     * @return
     */
    @GetMapping("subject/list")
    public ResponseResult<List<Subject>> list(){
        List<Subject> subjectList = subjectService.list();
        return new ResponseResult<>(subjectList);
    }
}
