package com.woniu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.woniu.dao.GradeDao;
import com.woniu.entity.Course;
import com.woniu.entity.Grade;
import com.woniu.service.CourseService;
import com.woniu.service.GradeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class GradeServiceImpl extends ServiceImpl<GradeDao,Grade> implements GradeService {
}
