package com.woniu.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

@Data
@TableName("k15_course")
public class Course {
    private Integer id;
    private String name;
    private String cover;
    private String description;
    private Integer lessonNum;
    private BigDecimal price;
    private Integer viewNum;
    private Integer buyNum;
    private Integer gradeId;
    private Integer subjectId;
    private Integer teacherId;
    private String pubTime;
    private Integer expiry;
    private String isfine;
    private String isFree;
    private String status;
}