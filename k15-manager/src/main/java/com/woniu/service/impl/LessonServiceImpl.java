package com.woniu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.woniu.dao.LessionDao;
import com.woniu.entity.Grade;
import com.woniu.entity.Lesson;
import com.woniu.service.GradeService;
import com.woniu.service.LessonService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class LessonServiceImpl extends ServiceImpl<LessionDao,Lesson> implements LessonService {
}
