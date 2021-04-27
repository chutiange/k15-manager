package com.woniu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.woniu.dao.SubjectDao;
import com.woniu.entity.Grade;
import com.woniu.entity.Subject;
import com.woniu.service.GradeService;
import com.woniu.service.SubjectService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class SubjectServiceImpl extends ServiceImpl<SubjectDao,Subject> implements SubjectService {

}
